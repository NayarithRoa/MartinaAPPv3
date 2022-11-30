package com.example.martinaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.martinaapp.Adapters.ListaProductosAdapter;
import com.example.martinaapp.BD.DBProductos;
import com.example.martinaapp.BD.Productos;

import java.util.ArrayList;

public class Inicio extends AppCompatActivity {
    RecyclerView listaProductos;
    ListaProductosAdapter adapter;
    ArrayList<Productos> listaArrayProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        recyclerVistaProductos();
        menuNavegacion();
    }

    private void menuNavegacion() {
        LinearLayout homeBtn=findViewById(R.id.homeBtn);
        LinearLayout cartBtn=findViewById(R.id.cartBtn);
        LinearLayout btnPedidos=findViewById(R.id.btnPedidos);


        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Inicio.this,Inicio.class));
            }
        });
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Inicio.this,DetalleCarroCompras.class));
            }
        });
        btnPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Inicio.this,HistorialPedidos.class));
            }
        });
    }

    private void recyclerVistaProductos() {
        listaProductos = findViewById(R.id.view2);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        listaProductos.setLayoutManager(linearLayoutManager);

        DBProductos dbProductos = new DBProductos(Inicio.this);

        listaArrayProductos= new ArrayList<>();
        adapter= new ListaProductosAdapter(dbProductos.mostrarProductos());
        listaProductos.setAdapter(adapter);
    }
}