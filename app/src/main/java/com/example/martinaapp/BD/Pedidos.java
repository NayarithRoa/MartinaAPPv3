package com.example.martinaapp.BD;

import java.sql.Date;
import java.sql.Time;

public class Pedidos {
    private long id_Pedido;
    private long id_Persona;
    private String Fecha;
    private String Hora;
    private double Costo_total;


    public Pedidos(long id_Pedido, long id_Persona, String fecha, String hora, double costo_total) {
        this.id_Pedido = id_Pedido;
        this.id_Persona = id_Persona;
       Fecha = fecha;
        Hora = hora;
        Costo_total = costo_total;
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

    public double getCosto_total() {
        return Costo_total;
    }

    public void setCosto_total(double costo_total) {
        Costo_total = costo_total;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    }

