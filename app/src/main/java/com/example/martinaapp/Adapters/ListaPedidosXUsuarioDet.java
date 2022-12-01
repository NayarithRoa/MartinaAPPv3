package com.example.martinaapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.martinaapp.BD.detalleProducto;
import com.example.martinaapp.R;

import java.util.ArrayList;

public class ListaPedidosXUsuarioDet extends RecyclerView.Adapter<ListaPedidosXUsuarioDet.ViewHolder>{
    private ArrayList<detalleProducto> listaPedidosDetalle;

    public ListaPedidosXUsuarioDet(ArrayList<detalleProducto> listaPedidosDetalle) {
        this.listaPedidosDetalle = listaPedidosDetalle;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detallepedido, null, false);
        return new ListaPedidosXUsuarioDet.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.viewProducto.setText(listaPedidosDetalle.get(position).getNombre());
        holder.viewDescripcion.setText(listaPedidosDetalle.get(position).getDescripcion());
        holder.viewPrecio.setText(String.valueOf(listaPedidosDetalle.get(position).getVlr_unitario()));
        holder.viewCantidad.setText(String.valueOf(listaPedidosDetalle.get(position).getCantidad_Producto()));
        int drawableReourceId=holder.itemView.getContext().getResources().getIdentifier(listaPedidosDetalle.get(position).getImagen(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableReourceId).into(holder.imgProd);
    }

    @Override
    public int getItemCount() {return listaPedidosDetalle.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView viewProducto, viewDescripcion,viewPrecio,viewCantidad;
        ImageView imgProd;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            this.viewProducto = itemView.findViewById(R.id.viewProducto);
            this.viewDescripcion = itemView.findViewById(R.id.viewDescripcion);
            this.viewPrecio = itemView.findViewById(R.id.viewPrecio);
            this.viewCantidad = itemView.findViewById(R.id.viewCantidad);
            imgProd=itemView.findViewById(R.id.imgprod);
        }
    }
}
