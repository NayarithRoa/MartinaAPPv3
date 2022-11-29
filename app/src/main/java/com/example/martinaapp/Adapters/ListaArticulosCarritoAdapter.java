package com.example.martinaapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.martinaapp.BD.Productos;
import com.example.martinaapp.Helper.AdministrarCarrito;
import com.example.martinaapp.Helper.CambioNumeroArticulos;
import com.example.martinaapp.R;

import java.util.ArrayList;

public class ListaArticulosCarritoAdapter  extends RecyclerView.Adapter<ListaArticulosCarritoAdapter.ViewHolder>{
    ArrayList<Productos> listaArticulosSeleccionados;
    private AdministrarCarrito administrarCarrito;
    CambioNumeroArticulos cambioNumeroArticulos;


    public ListaArticulosCarritoAdapter(ArrayList<Productos> listaArticulosSeleccionados, Context context,CambioNumeroArticulos cambioNumeroArticulos) {
        this.listaArticulosSeleccionados = listaArticulosSeleccionados;
        administrarCarrito=new AdministrarCarrito(context);
        this.cambioNumeroArticulos = cambioNumeroArticulos;

    }
    @NonNull
    @Override
    public ListaArticulosCarritoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.vistaarticulocarrito, parent, false);
        return new ViewHolder(inflate);
    }


    @Override
    public void onBindViewHolder(@NonNull ListaArticulosCarritoAdapter.ViewHolder holder, int position) {
        holder.nombre.setText(listaArticulosSeleccionados.get(position).getNombre());
        holder.vlrArticulo.setText("$"+listaArticulosSeleccionados.get(position).getVlr_unitario());
        holder.txttotalProdUnit.setText("$"+Math.round((listaArticulosSeleccionados.get(position).getCantidad()*listaArticulosSeleccionados.get(position).getVlr_unitario())));
        holder.txtCantArt.setText(String.valueOf(listaArticulosSeleccionados.get(position).getCantidad()));

        //int drawableReourceId=holder.itemView.getContext().getResources().getIdentifier(listaArticulosSeleccionados.get(position).getImagen(),"drawable",holder.itemView.getContext().getPackageName());
        //Glide.with(holder.itemView.getContext()).load(drawableReourceId).into(holder.imgProd);

        holder.plusItem.setOnClickListener(v -> administrarCarrito.plusNumberFood(listaArticulosSeleccionados, position, () -> {
            notifyDataSetChanged();
            cambioNumeroArticulos.changed();
        }));

        holder.minusItem.setOnClickListener(view -> administrarCarrito.minusNumberFood(listaArticulosSeleccionados, position, () -> {
            notifyDataSetChanged();
            cambioNumeroArticulos.changed();
        }));
        holder.bntdeleteItem.setOnClickListener(view -> administrarCarrito.deleteArticulo(listaArticulosSeleccionados, position, () -> {
            notifyDataSetChanged();
            cambioNumeroArticulos.changed();
        }));

    }

    @Override
    public int getItemCount() {
        return listaArticulosSeleccionados.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre,vlrArticulo;
        ImageView imgProd,plusItem,minusItem,bntdeleteItem;
        TextView txttotalProdUnit,txtCantArt;

        public ViewHolder(@NonNull View itemview) {
            super(itemview);
            nombre=itemview.findViewById(R.id.txtNombre);
            imgProd=itemview.findViewById(R.id.imgprod);
            vlrArticulo=itemview.findViewById(R.id.txtvlrUnitario);
            txttotalProdUnit=itemview.findViewById(R.id.txttotalProdUnit);
            plusItem=itemview.findViewById(R.id.plusCardBtn);
            minusItem=itemview.findViewById(R.id.minusCardBtn);
            bntdeleteItem=itemview.findViewById(R.id.bntdeleteItem);
            txtCantArt=itemview.findViewById(R.id.txtCantArt);

        }
    }
}
