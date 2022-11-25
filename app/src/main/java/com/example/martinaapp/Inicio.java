package com.example.martinaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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