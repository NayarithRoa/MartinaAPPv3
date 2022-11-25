package com.example.martinaapp.BD;

public class Productos {
    private long id_Producto;
    private String nombre;
    private String descripcion;
    private double vlr_unitario;
    private byte[] imagen;
    private int cantidad;


    public Productos(long id_Producto, String nombre, String descripcion, double vlr_unitario, byte[] imagen, int cantidad) {
        this.id_Producto = id_Producto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.vlr_unitario = vlr_unitario;
        this.imagen = imagen;
        this.cantidad = cantidad;
    }
    public Productos() {
        this.id_Producto = id_Producto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.vlr_unitario = vlr_unitario;
        this.imagen = imagen;
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

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

