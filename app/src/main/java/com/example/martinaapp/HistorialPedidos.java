package com.example.martinaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.martinaapp.Adapters.ListaPedidosXUsuario;
import com.example.martinaapp.BD.DBPedidos;
import com.example.martinaapp.BD.Pedidos;
import com.example.martinaapp.BD.Productos;
import com.example.martinaapp.DatosUsuario.Globales;

import java.util.ArrayList;

public class HistorialPedidos extends AppCompatActivity {
    RecyclerView listadoPedidos;
    ArrayList<Pedidos> listaArrayEstudiantes;
    ListaPedidosXUsuario adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_pedidos);

        listadoPedidos = findViewById(R.id.listaPedidos);
        listadoPedidos.setLayoutManager(new LinearLayoutManager(this));

        DBPedidos dbPedidos = new DBPedidos(HistorialPedidos.this);

        listaArrayEstudiantes= new ArrayList<>();
        adapter= new ListaPedidosXUsuario(dbPedidos.verListadoPedidos(1));
        listaEstudiantes.setAdapter(adapter);
    }
}