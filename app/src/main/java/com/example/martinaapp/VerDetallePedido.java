package com.example.martinaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.martinaapp.Adapters.ListaPedidosXUsuario;
import com.example.martinaapp.Adapters.ListaPedidosXUsuarioDet;
import com.example.martinaapp.BD.DBDetalle_Pedido;
import com.example.martinaapp.BD.DBPedidos;
import com.example.martinaapp.BD.Detalle_Pedido;
import com.example.martinaapp.BD.Pedidos;
import com.example.martinaapp.DatosUsuario.Globales;

import java.util.ArrayList;

public class VerDetallePedido extends AppCompatActivity {
    long id=0;
    RecyclerView listadoDetallePedidos;
    ArrayList<Detalle_Pedido> listaArrayDetallePedidos;
    ListaPedidosXUsuarioDet adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_detalle_pedido);

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

        listadoDetallePedidos = findViewById(R.id.listaDetallePedidos);
        listadoDetallePedidos.setLayoutManager(new LinearLayoutManager(this));

        DBDetalle_Pedido dbDetalle_pedido = new DBDetalle_Pedido(VerDetallePedido.this);

        listaArrayDetallePedidos= new ArrayList<>();
        Integer pedidoId = (int) (long) id;
        adapter= new ListaPedidosXUsuarioDet(dbDetalle_pedido.verDetallePedido(pedidoId));
        listadoDetallePedidos.setAdapter(adapter);



    }
}