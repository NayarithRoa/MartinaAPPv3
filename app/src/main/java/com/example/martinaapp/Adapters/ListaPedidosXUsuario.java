package com.example.martinaapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.martinaapp.BD.Pedidos;
import com.example.martinaapp.R;
import com.example.martinaapp.VerDetallePedido;

import java.util.ArrayList;

public class ListaPedidosXUsuario extends RecyclerView.Adapter<ListaPedidosXUsuario.ViewHolder>{

    private ArrayList<Pedidos> listaPedidosxUsuario;

    public ListaPedidosXUsuario(ArrayList<Pedidos> listaPedidosxUsuario) {
        this.listaPedidosxUsuario = listaPedidosxUsuario;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listadopedido, null, false);
        return new ListaPedidosXUsuario.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        long id=listaPedidosxUsuario.get(position).getId_Pedido();
        holder.viewProducto.setText("Pedido No. "+id);
        holder.viewfecha.setText(listaPedidosxUsuario.get(position).getFecha());

    }

    @Override
    public int getItemCount() {
        return listaPedidosxUsuario.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView viewProducto, viewfecha;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.viewProducto = itemView.findViewById(R.id.viewProducto);
            this.viewfecha = itemView.findViewById(R.id.viewfecha);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerDetallePedido.class);
                    intent.putExtra("ID", listaPedidosxUsuario.get(getAdapterPosition()).getId_Pedido());
                    context.startActivity(intent);
                }
            });

        }
    }

}
