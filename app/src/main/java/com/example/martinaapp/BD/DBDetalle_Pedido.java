package com.example.martinaapp.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

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
}
