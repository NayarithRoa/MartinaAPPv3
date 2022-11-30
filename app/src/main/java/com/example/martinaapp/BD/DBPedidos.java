package com.example.martinaapp.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.martinaapp.DatosUsuario.Globales;

import java.util.ArrayList;

public class DBPedidos extends DBHelper {
    private Context context;

    public DBPedidos(@Nullable Context context) {
        super(context);
        this.context = context;
    }
    //INSERTA EN TABLA PEDIDOS
    public long insertarPedido(Pedidos pedidos){

        try{
            //Conexión
            DBHelper dbHelper = new DBHelper(this.context);
            //Objeto para la lectura en la base de datos
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put("ID_PERSONA", pedidos.getId_Persona());
            valores.put("FECHA", pedidos.getFecha());
            valores.put("HORA", pedidos.getHora());
            valores.put("COSTO_TOTAL", pedidos.getCosto_total());

            return db.insert(Constantes.TABLA_PEDIDO,null,valores);

        }catch (Exception ex){
            System.err.println(ex);
            return 0;

        }
    }
    //Método para Verificar el ultimo pedido echo por el usuario
    public int existePedido(int id){

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor historicoPedidos = null;
        historicoPedidos = db.rawQuery("SELECT COD_COMPRA FROM " + Constantes.TABLA_PEDIDO + " WHERE ID_PERSONA= "+ id+ " order by COD_COMPRA desc limit 1", null);

        historicoPedidos.moveToFirst();
        if (historicoPedidos.getCount()>0) {
            int codCompra=historicoPedidos.getInt(0);
            historicoPedidos.close();
            return codCompra;
        }
        else{
            return 0;
        }

    }

    public boolean actualizarPedido(int id, Double costoTotal) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(this.context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + Constantes.TABLA_PEDIDO + " SET COSTO_TOTAL = '" + costoTotal + "' WHERE ID_PEDIDO='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public ArrayList<Pedidos> verListadoPedidos(int id) {

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<Pedidos> listaPedido = new ArrayList<>();
        Pedidos pedidos = null;
        Cursor cursorPedidos;

        cursorPedidos = db.rawQuery("SELECT * FROM " + Constantes.TABLA_PEDIDO + " WHERE ID_PERSONA=" + id , null);

        if (cursorPedidos.moveToFirst()) {
            do {
            pedidos = new Pedidos();
            pedidos.setId_Pedido(cursorPedidos.getInt(0));
            pedidos.setFecha(cursorPedidos.getString(2));

                listaPedido.add(pedidos);
            } while (cursorPedidos.moveToNext());
        }
        cursorPedidos.close();
        return listaPedido;
    }

}
