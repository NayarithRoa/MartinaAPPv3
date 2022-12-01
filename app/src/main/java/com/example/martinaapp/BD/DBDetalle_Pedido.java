package com.example.martinaapp.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBDetalle_Pedido extends DBHelper {
    private Context context;

    public DBDetalle_Pedido(@Nullable Context context) {
        super(context);
        this.context = context;
    }
    //INSERTA EN TABLA PEDIDOS
    public long insertarDetallePedido(Detalle_Pedido detalle_pedido){

        try{
            //Conexión
            DBHelper dbHelper = new DBHelper(this.context);
            //Objeto para la lectura en la base de datos
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put("ID_PRODUCTO", detalle_pedido.getId_Producto());
            valores.put("ID_PEDIDO", detalle_pedido.getId_Pedido());
            valores.put("CANT_PRODUCTO", detalle_pedido.getCantidad_Producto());

            long id= db.insert(Constantes.TABLA_DETALLE_PEDIDO,null,valores);

            return id;
        }catch (Exception ex){
            System.err.println(ex);
            return 0;
        }
    }

    public ArrayList<detalleProducto> verDetallePedido(int id) {

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<detalleProducto> listaDetallePed = new ArrayList<>();
        detalleProducto detalleProducto = null;
        Cursor cursorDetallePedidos;

        cursorDetallePedidos = db.rawQuery("SELECT PROD.NOMBRE, PROD.DESCRIPCION, PROD.VLR_UNITARIO, PROD.IMAGEN, DET.CANT_PRODUCTO FROM " + Constantes.TABLA_DETALLE_PEDIDO + " DET, "+ Constantes.TABLA_PRODUCTO + " PROD WHERE DET.ID_PRODUCTO=PROD.ID_PRODUCTO AND DET.ID_PEDIDO=" + id , null);
        if (cursorDetallePedidos.moveToFirst()) {
            do {
                detalleProducto = new detalleProducto();
                detalleProducto.setNombre(cursorDetallePedidos.getString(0));
                detalleProducto.setDescripcion(cursorDetallePedidos.getString(1));
                detalleProducto.setVlr_unitario(cursorDetallePedidos.getDouble(2));
                detalleProducto.setImagen(cursorDetallePedidos.getString(3));
                detalleProducto.setCantidad_Producto(cursorDetallePedidos.getInt(4));

                listaDetallePed.add(detalleProducto);
            } while (cursorDetallePedidos.moveToNext());
        }
        cursorDetallePedidos.close();
        return listaDetallePed;
    }
}
