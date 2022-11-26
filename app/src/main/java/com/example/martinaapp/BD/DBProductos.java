package com.example.martinaapp.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class DBProductos extends DBHelper {
    Context context;

    public DBProductos(@Nullable Context context) {
        super(context);
        this.context  = context;
    }

    //INSERTA EN TABLA PRODUCTOS
    public long insertarProductos(Productos productos){

        try{
            //Conexión
            DBHelper dbHelper = new DBHelper(this.context);
            //Objeto para la lectura en la base de datos
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put("NOMBRE", productos.getNombre());
            valores.put("DESCRIPCION", productos.getDescripcion());
            valores.put("VLR_UNITARIO", productos.getVlr_unitario());
            valores.put("IMAGEN", productos.getImagen());

            long id=db.insert(Constantes.TABLA_PRODUCTO,null,valores);
            if (id > 0) {
                Toast.makeText(context, "PRODUCTOS ALMACENADOS", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "ERROR AL CREAR LOS PRODUCTOS", Toast.LENGTH_SHORT).show();
            }
            return id;
        }catch (Exception ex){
            System.err.println(ex);
            return 0;
        }
    }

    //Método para Verificar existencia de Productos en BD
    public boolean existeProductos(){

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor listaProductos = null;
        listaProductos = db.rawQuery("SELECT * FROM " + Constantes.TABLA_PRODUCTO, null);

        if (listaProductos.getCount()>0) {
            return true;
        }
        else{
            return false;
        }

    }

    public ArrayList<Productos> mostrarProductos() {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<Productos> listaProducto = new ArrayList<>();
        Productos producto;
        Cursor cursorProductos = null;
        cursorProductos = db.rawQuery("SELECT * FROM " + Constantes.TABLA_PRODUCTO, null);

        if (cursorProductos.moveToFirst()) {
            do {
                producto = new Productos();
                producto.setId_Producto(cursorProductos.getInt(0));
                producto.setNombre(cursorProductos.getString(1));
                producto.setDescripcion(cursorProductos.getString(2));
                producto.setVlr_unitario(cursorProductos.getDouble(3));
                producto.setImagen(cursorProductos.getString(4));
                /*//IMAGEN
                byte[] bytesImage=cursorProductos.getBlob(4);
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytesImage,0,bytesImage.length);
                producto.setImagen(bytesImage);
                //IMAGEN*/

                listaProducto.add(producto);
            } while (cursorProductos.moveToNext());
        }
        cursorProductos.close();

        return listaProducto;
    }

    public Productos verDetalleProducto(int id) {

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase(); //Escribe

        Productos producto = null;
        Cursor cursorProductos;

        cursorProductos = db.rawQuery("SELECT * FROM " + Constantes.TABLA_PRODUCTO + " WHERE ID_PRODUCTO=" + id + " LIMIT 1", null);

        if (cursorProductos.moveToFirst()) {

            producto = new Productos();
            producto.setId_Producto(cursorProductos.getInt(0));
            producto.setNombre(cursorProductos.getString(1));
            producto.setDescripcion(cursorProductos.getString(2));
            producto.setVlr_unitario(cursorProductos.getDouble(3));
            producto.setImagen(cursorProductos.getString(4));
            /*//IMAGEN
            byte[] bytesImage=cursorProductos.getBlob(4);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytesImage,0,bytesImage.length);
            producto.setImagen(bytesImage);
            //IMAGEN*/
        }

        cursorProductos.close();

        return producto;
    }
}
