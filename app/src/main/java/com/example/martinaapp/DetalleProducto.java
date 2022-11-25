package com.example.martinaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.martinaapp.BD.DBProductos;
import com.example.martinaapp.BD.Pedidos;
import com.example.martinaapp.BD.Personas;
import com.example.martinaapp.BD.Productos;

public class DetalleProducto extends AppCompatActivity {

    private TextView addToCartBtn;
    private TextView txtTitulo, txtprecio, descriptionTxt, txtCantidad, txtPrecioTotal;
    private ImageView btnMas, btnMenos, imgProducto;
    private Productos object;
    private int numberOder = 1;
    long id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        addToCartBtn=findViewById(R.id.addToCratBtn);
        txtTitulo=findViewById(R.id.txtTitulo);
        txtprecio=findViewById(R.id.txtprecio);
        descriptionTxt=findViewById(R.id.descriptionTxt);
        txtCantidad=findViewById(R.id.txtCantidad);
        btnMas=findViewById(R.id.btnMas);
        btnMenos=findViewById(R.id.btnMenos);
        imgProducto=findViewById(R.id.imgProducto);
        txtPrecioTotal=findViewById(R.id.totalPriceTxt);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Long.parseLong(null);
            } else {
                id = extras.getLong("ID");
            }
        } else {
            id = (long) savedInstanceState.getSerializable("ID");
        }

        //int drawableResourceId=this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());
        //Glide.with(this)
          //      .load(drawableResourceId)
            //    .into(picFood);

        final DBProductos dbProductos= new DBProductos(DetalleProducto.this);
        Productos productos = dbProductos.verDetalleProducto((int) id);

        if(productos != null){
            txtTitulo.setText(productos.getNombre());
            descriptionTxt.setText(productos.getDescripcion());
            txtprecio.setText(String.valueOf(productos.getVlr_unitario()));
            txtPrecioTotal.setText(String.valueOf(productos.getVlr_unitario()));
            //Glide.with(this).load( productos.getImagen().into(imgProducto);

            txtTitulo.setInputType(InputType.TYPE_NULL); //No permita que se habilite el teclado para escribir
            descriptionTxt.setInputType(InputType.TYPE_NULL);
            txtprecio.setInputType(InputType.TYPE_NULL);

        }

        btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOder = numberOder + 1;
                txtCantidad.setText(String.valueOf(numberOder));
                txtPrecioTotal.setText(String.valueOf(numberOder * productos.getVlr_unitario()));
            }
        });

        btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOder > 1){
                    numberOder = numberOder - 1;
                }
                txtCantidad.setText(String.valueOf(numberOder));
                txtPrecioTotal.setText(String.valueOf(numberOder * productos.getVlr_unitario()));
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pedidos pedidos= new Pedidos();
                pedidos.setCantidad_Producto(numberOder);
          //      managementCart.insertFood(object);
            }
        });
    }
}