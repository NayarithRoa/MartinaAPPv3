package com.example.martinaapp.BD;

public class Detalle_Pedido {
    private long id_Detalle_Pedido;
    private long id_Producto;
    private long id_Pedido;
    private int Cantidad_Producto;

    public Detalle_Pedido(long id_Detalle_Pedido, long id_Producto, long id_Pedido, int cantidad_Producto) {
        this.id_Detalle_Pedido = id_Detalle_Pedido;
        this.id_Producto = id_Producto;
        this.id_Pedido = id_Pedido;
        Cantidad_Producto = cantidad_Producto;
    }
    public Detalle_Pedido() {

    }


    public long getId_Detalle_Pedido() {
        return id_Detalle_Pedido;
    }

    public void setId_Detalle_Pedido(long id_Detalle_Pedido) {
        this.id_Detalle_Pedido = id_Detalle_Pedido;
    }

    public long getId_Producto() {
        return id_Producto;
    }

    public void setId_Producto(long id_Producto) {
        this.id_Producto = id_Producto;
    }

    public long getId_Pedido() {
        return id_Pedido;
    }

    public void setId_Pedido(long id_Pedido) {
        this.id_Pedido = id_Pedido;
    }

    public int getCantidad_Producto() {
        return Cantidad_Producto;
    }

    public void setCantidad_Producto(int cantidad_Producto) {
        Cantidad_Producto = cantidad_Producto;
    }
}
