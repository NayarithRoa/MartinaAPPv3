package com.example.martinaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.martinaapp.Adapters.ListaArticulosCarritoAdapter;
import com.example.martinaapp.BD.DBDetalle_Pedido;
import com.example.martinaapp.BD.DBPedidos;
import com.example.martinaapp.BD.Detalle_Pedido;
import com.example.martinaapp.BD.Pedidos;
import com.example.martinaapp.BD.Productos;
import com.example.martinaapp.DatosUsuario.Globales;
import com.example.martinaapp.Helper.AdministrarCarrito;
import com.example.martinaapp.Helper.CambioNumeroArticulos;
import com.example.martinaapp.Helper.tempPedido;

import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DetalleCarroCompras extends AppCompatActivity {
    RecyclerView.Adapter adapter;
    RecyclerView recyclerViewList;
    private AdministrarCarrito administrarCarrito;
    TextView txtTotalArt, txtvlrdomicilio, totalTxt, emptyTxt;
    ConstraintLayout btnConfirmar;
    ScrollView scrollView;
    DateFormat df= new SimpleDateFormat("dd/MM/yy");
    Date fecha=new Date();
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
    int ultCompraxUser=0;
    Double Costo_total=0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_carro_compras);

        administrarCarrito= new AdministrarCarrito(this);
        txtTotalArt=findViewById(R.id.txtTotalArt);
        txtvlrdomicilio=findViewById(R.id.txtvlrdomicilio);
        totalTxt=findViewById(R.id.totalTxt);
        recyclerViewList=findViewById(R.id.view);
        scrollView=findViewById(R.id.scrollView);
        emptyTxt=findViewById(R.id.emptyTxt);
        btnConfirmar=findViewById(R.id.btnConfirmar);

        listaInicial();
        menuNavegacion();
        calcularTarjeta();

        btnConfirmar.setOnClickListener(v->{
            insertarPedido();
        });

    }

    private void insertarPedido() {
        DBPedidos dbPedidos = new DBPedidos(this);
        Pedidos pedidos = new Pedidos();
        DBDetalle_Pedido dbDetalle_pedido = new DBDetalle_Pedido(this);
        Detalle_Pedido detalle_pedido = new Detalle_Pedido();
        ArrayList<Productos> listaProductos=administrarCarrito.getListCart();
        if (!administrarCarrito.getListCart().isEmpty()){
                //encabezado del pedido
                pedidos.setId_Persona(Globales.id_usuario);
                pedidos.setFecha(df.format(fecha));
                pedidos.setHora((mdformat.format(calendar.getTime())));
                pedidos.setCosto_total(0);
                Long idPedidoCreado =dbPedidos.insertarPedido(pedidos);
            if(idPedidoCreado>0){
                for (int i = 0; i < listaProductos.size(); i++) {
                    //detalle del pedido
                    detalle_pedido.setId_Pedido(idPedidoCreado);
                    detalle_pedido.setId_Producto(listaProductos.get(i).getId_Producto());
                    detalle_pedido.setCantidad_Producto(listaProductos.get(i).getCantidad());
                    Costo_total=+listaProductos.get(i).getVlr_unitario()*listaProductos.get(i).getCantidad();
                    dbDetalle_pedido.insertarDetallePedido(detalle_pedido);
                }
                int pedidoId=idPedidoCreado.intValue();
                boolean correcto = dbPedidos.actualizarPedido(pedidoId,Costo_total);
                if(correcto){
                  Toast.makeText(this, "Pedido almacenado", Toast.LENGTH_SHORT).show();
                    listaProductos.clear();

                } else {
                  Toast.makeText(this, "Error, no se guardó el pedido", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "Error, no se guardó el pedido", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void existePedidoxUsuario() {
        DBPedidos dbPedidos = new DBPedidos(this);
        int resultado = dbPedidos.existePedido(Globales.getId_usuario());
        if (resultado>0){
            ultCompraxUser=resultado+1;
        }
        else{
            ultCompraxUser=1;
        }
    }

    private void listaInicial() {

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);

        ArrayList<Productos> listaProductos=administrarCarrito.getListCart();
        adapter=new ListaArticulosCarritoAdapter(listaProductos, this,new CambioNumeroArticulos() {
            @Override
            public void changed() {
                calcularTarjeta();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if (administrarCarrito.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else{
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calcularTarjeta() {
        double domicilio=5000;
        double total=Math.round((administrarCarrito.getTotalPrecio()+domicilio)*100)/100;
        double itemTotal=Math.round(administrarCarrito.getTotalPrecio() * 100 ) / 100;

        txtTotalArt.setText("$"+itemTotal);
        txtvlrdomicilio.setText("$"+domicilio);
        totalTxt.setText("$"+total);

    }

    private void menuNavegacion() {
        LinearLayout homeBtn=findViewById(R.id.homeBtn);
        LinearLayout cartBtn=findViewById(R.id.cartBtn);
        LinearLayout btnPedidos=findViewById(R.id.btnPedidos);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetalleCarroCompras.this,Inicio.class));
            }
        });
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetalleCarroCompras.this,DetalleCarroCompras.class));
            }
        });

        btnPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetalleCarroCompras.this,HistorialPedidos.class));
            }
        });
    }

}