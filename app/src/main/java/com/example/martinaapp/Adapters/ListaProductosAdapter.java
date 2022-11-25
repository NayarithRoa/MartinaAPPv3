package com.example.martinaapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.martinaapp.BD.Productos;
import com.example.martinaapp.DetalleProducto;
import com.example.martinaapp.R;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class ListaProductosAdapter extends RecyclerView.Adapter<ListaProductosAdapter.ViewHolder>{
    private ArrayList<Productos> listaProductos;

    public ListaProductosAdapter(ArrayList<Productos> listaProductos) {
        this.listaProductos = listaProductos;
        //this.layoutInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public ListaProductosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_producto, null, false);
        return new ListaProductosAdapter.ViewHolder(view);
    }

    @Override
    //Asignar los elementos a cada una de las opciones
    public void onBindViewHolder(@NonNull ListaProductosAdapter.ViewHolder viewHolder, int position) {
        viewHolder.nombre.setText(listaProductos.get(position).getNombre());
        viewHolder.precio.setText(String.valueOf(listaProductos.get(position).getVlr_unitario()));
        Glide.with(viewHolder.itemView.getContext()).load(listaProductos.get(position).getImagen()).into(viewHolder.pic);
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre,precio;
        ImageView pic;
        ImageView addBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.title);
            pic=itemView.findViewById(R.id.pic);
            precio=itemView.findViewById(R.id.precio);
            addBtn=itemView.findViewById(R.id.addBtn);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, DetalleProducto.class);
                    intent.putExtra("ID", listaProductos.get(getAdapterPosition()).getId_Producto());
                    context.startActivity(intent);
                }
            });
        }

    }
}
