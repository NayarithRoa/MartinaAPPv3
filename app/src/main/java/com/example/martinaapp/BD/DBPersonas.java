package com.example.martinaapp.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.martinaapp.DatosUsuario.Globales;

public class DBPersonas extends DBHelper{
    private Context context;

    public DBPersonas(@Nullable Context context) {
        super(context);
        this.context = context;
    }
        //INSERTA EN TABLA PERSONAS
    public long insertarPersonas(Personas personas){

        try{
            //Conexión
            DBHelper dbHelper = new DBHelper(this.context);
            //Objeto para la lectura en la base de datos
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put("NOMBRES", personas.getNombres());
            valores.put("TELEFONO", personas.getTelefono());
            valores.put("CORREO", personas.getCorreo());
            valores.put("DIRECCION", personas.getDireccion());
            valores.put("CLAVE", personas.getClave());

            long id= db.insert(Constantes.TABLA_PERSONA,null,valores);
            if (id > 0) {
                Toast.makeText(context, "Usuarios almacenados", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Error al crear los usuarios", Toast.LENGTH_SHORT).show();
            }
            return id;
        }catch (Exception ex){
            System.err.println(ex);
            return 0;
        }
    }
    //VERIFICA EXISTENCIA DE PERSONA
    public boolean autenticarPersonas(String correo, String clave){
        try {
            //Conexión
            DBHelper helper = new DBHelper(context);
            //Objeto para la lectura en la base de datos
            SQLiteDatabase base_datos = helper.getReadableDatabase();
            //Arreglo con las condiciones de búsqueda, WHERE
            String[] parametrosConsulta = {correo, clave};
            //Arreglo con los campos a consultar, SELECT
            String[] camposConsulta = {"ID_PERSONA","CORREO","CLAVE"};
            //Se define cursor para almacenar el resultado de la búsqueda
            Cursor cursor = base_datos.query(Constantes.TABLA_PERSONA, camposConsulta, "CORREO" + "=?" + " AND "+ "CLAVE" + "=?",
                    parametrosConsulta, null, null, null);
            cursor.moveToFirst();
            if (cursor.getCount()>0) {
                Globales.setId_usuario(cursor.getInt(0));
                cursor.close();
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception ex){
            System.err.println(ex);
            return false;}
    }
    //Método para Verificar existencia de Personas en BD
    public boolean existePersonas(){

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor listaPersonas = null;
        listaPersonas = db.rawQuery("SELECT * FROM " + Constantes.TABLA_PERSONA, null);

        if (listaPersonas.getCount()>0) {
            return true;
        }
        else{
            return false;
        }

    }
}
