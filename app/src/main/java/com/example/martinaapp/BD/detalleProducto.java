package com.example.martinaapp.BD;

public class detalleProducto {
    private long id_Producto;
    private String nombre;
    private String descripcion;
    private double vlr_unitario;
    private String imagen;
    private int Cantidad_Producto;

    public detalleProducto(long id_Producto, String nombre, String descripcion, double vlr_unitario, String imagen, int cantidad_Producto) {
        this.id_Producto = id_Producto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.vlr_unitario = vlr_unitario;
        this.imagen = imagen;
        Cantidad_Producto = cantidad_Producto;
    }

    public detalleProducto() {

    }

    public long getId_Producto() {
        return id_Producto;
    }

    public void setId_Producto(long id_Producto) {
        this.id_Producto = id_Producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getVlr_unitario() {
        return vlr_unitario;
    }

    public void setVlr_unitario(double vlr_unitario) {
        this.vlr_unitario = vlr_unitario;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getCantidad_Producto() {
        return Cantidad_Producto;
    }

    public void setCantidad_Producto(int cantidad_Producto) {
        Cantidad_Producto = cantidad_Producto;
    }
}
