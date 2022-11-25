package com.example.martinaapp.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.martinaapp.BD.Productos;

import java.util.ArrayList;

public class AdministrarCarrito {
    private Context context;
    private tempPedido tempPedido;

    public AdministrarCarrito(Context context) {
        this.context = context;
        this.tempPedido = new tempPedido(context);
    }
    //agregarArticulo
    public void insertFood(Productos item){
        ArrayList<Productos> listaProductos=getListCart();
        boolean existeProd=false;
        int n=0;
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getNombre().equals(item.getNombre())){
                existeProd=true;
                n=i;
                break;
            }
        }

        if (existeProd){
            listaProductos.get(n).setCantidad(item.getCantidad());
        }else{
            listaProductos.add(item);
        }
        tempPedido.putListObject("CardList",listaProductos);
        Toast.makeText(context, "Agregado al carro", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<Productos> getListCart() {
        return tempPedido.getListObject("CardList");
    }
    public void minusNumberFood(ArrayList<Productos> listaProductos, int position, CambioNumeroArticulos cambioNumeroArticulos){
        if (listaProductos.get(position).getCantidad()==1){
            listaProductos.remove(position);
        }else{
            listaProductos.get(position).setCantidad(listaProductos.get(position).getCantidad() - 1);
        }
        tempPedido.putListObject("CardList",listaProductos);
        cambioNumeroArticulos.changed();
    }
    public void plusNumberFood(ArrayList<Productos> listaProductos,int position,CambioNumeroArticulos cambioNumeroArticulos){
        listaProductos.get(position).setCantidad(listaProductos.get(position).getCantidad()+1);
        tempPedido.putListObject("CardList",listaProductos);
        cambioNumeroArticulos.changed();
    }
    public Double getTotalPrecio() {
        ArrayList<Productos>listaProductos2=getListCart();
        double fee = 0;
        for (int i = 0; i < listaProductos2.size();i++){
            fee=fee+(listaProductos2.get(i).getVlr_unitario()*listaProductos2.get(i).getCantidad());
        }
        return fee;


    }
}
