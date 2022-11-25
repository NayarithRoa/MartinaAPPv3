package com.example.martinaapp.BD;

import java.sql.Date;
import java.sql.Time;

public class Pedidos {
    private long id_Pedido;
    private long id_Persona;
    private long id_Producto;
    private Date Fecha;
    private Time Hora;
    private double Costo_total;
    private int Cantidad_Producto;

    public Pedidos(long id_Pedido, long id_Persona, long id_Producto, Date fecha, Time hora, double costo_total, int cantidad_Producto) {
        this.id_Pedido = id_Pedido;
        this.id_Persona = id_Persona;
        this.id_Producto = id_Producto;
        Fecha = fecha;
        Hora = hora;
        Costo_total = costo_total;
        Cantidad_Producto = cantidad_Producto;
    }
    public Pedidos() {

    }

    public long getId_Pedido() {
        return id_Pedido;
    }

    public void setId_Pedido(long id_Pedido) {
        this.id_Pedido = id_Pedido;
    }

    public long getId_Persona() {
        return id_Persona;
    }

    public void setId_Persona(long id_Persona) {
        this.id_Persona = id_Persona;
    }

    public long getId_Producto() {
        return id_Producto;
    }

    public void setId_Producto(long id_Producto) {
        this.id_Producto = id_Producto;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public Time getHora() {
        return Hora;
    }

    public void setHora(Time hora) {
        Hora = hora;
    }

    public double getCosto_total() {
        return Costo_total;
    }

    public void setCosto_total(int costo_total) {
        Costo_total = costo_total;
    }

    public int getCantidad_Producto() {
        return Cantidad_Producto;
    }

    public void setCantidad_Producto(int cantidad_Producto) {
        Cantidad_Producto = cantidad_Producto;
    }
}
