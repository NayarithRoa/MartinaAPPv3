package com.example.martinaapp.BD;

import android.content.Context;

import androidx.annotation.Nullable;

public class PersonasDefault extends DBHelper{
    private Context context;

    public PersonasDefault(@Nullable Context context) {
        super(context);
        this.context = context;
    }
    public void insertaPersona(){
        DBPersonas dbPersonas = new DBPersonas(context);
        Personas personas= new Personas();
        //Primer registro
        personas.setNombres("Nayarith Roa");
        personas.setTelefono("3023691520");
        personas.setDireccion("Barrio José Antonio Galan");
        personas.setCorreo("nroa@uniboyaca.edu.co");
        personas.setClave("12345");
        dbPersonas.insertarPersonas(personas);
        //Segundo registro
        personas.setNombres("Admin");
        personas.setTelefono("3023691520");
        personas.setDireccion("Barrio Muiscas");
        personas.setCorreo("admin@gmail.com");
        personas.setClave("12345");
        dbPersonas.insertarPersonas(personas);
    }
}
