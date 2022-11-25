package com.example.martinaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.martinaapp.Adapters.ListaArticulosCarritoAdapter;
import com.example.martinaapp.Helper.AdministrarCarrito;
import com.example.martinaapp.Helper.CambioNumeroArticulos;

public class DetalleCarroCompras extends AppCompatActivity {
    RecyclerView.Adapter adapter;
    RecyclerView recyclerViewList;
    private AdministrarCarrito administrarCarrito;
    TextView txtTotalArt, txtvlrdomicilio, totalTxt, emptyTxt;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_carro_compras);

        txtTotalArt=findViewById(R.id.txtTotalArt);
        txtvlrdomicilio=findViewById(R.id.txtvlrdomicilio);
        totalTxt=findViewById(R.id.totalTxt);
        recyclerViewList=findViewById(R.id.view);
        scrollView=findViewById(R.id.scrollView);
        emptyTxt=findViewById(R.id.emptyTxt);

        listaInicial();
    }
    private void listaInicial() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter=new ListaArticulosCarritoAdapter(administrarCarrito.getListCart(), this, new CambioNumeroArticulos() {
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
        double total=Math.round((administrarCarrito.getTotalPrecio()+domicilio)*100.0)/100.0;
        double itemTotal=Math.round(administrarCarrito.getTotalPrecio() * 100.0 ) / 100.0;

        txtTotalArt.setText("$"+itemTotal);
        txtvlrdomicilio.setText("$"+domicilio);
        totalTxt.setText("$"+total);

    }
}