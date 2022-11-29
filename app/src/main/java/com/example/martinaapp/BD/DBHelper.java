package com.example.martinaapp.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String CREAR_TABLA_PERSONA = "CREATE TABLE " + Constantes.TABLA_PERSONA +
            " (ID_PERSONA INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " NOMBRES TEXT NOT NULL, " +
            " TELEFONO TEXT NOT NULL," +
            " CORREO TEXT NOT NULL," +
            " CLAVE TEXT NOT NULL," +
            " DIRECCION TEXT NOT NULL)" ;

    private static final String CREAR_TABLA_PRODUCTO = "CREATE TABLE " + Constantes.TABLA_PRODUCTO +
            " (ID_PRODUCTO INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " NOMBRE TEXT NOT NULL, " +
            " DESCRIPCION TEXT NOT NULL," +
            " VLR_UNITARIO DOUBLE NOT NULL," +
            " IMAGEN TEXT NOT NULL," +
            " CANTIDAD INT)" ;


    private static final String CREAR_TABLA_PEDIDO = "CREATE TABLE " + Constantes.TABLA_PEDIDO +
            " (ID_PEDIDO INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " ID_PERSONA INT NOT NULL," +
            " FECHA TEXT NOT NULL, " +
            " HORA TEXT NOT NULL," +
            " COSTO_TOTAL REAL," +
            " FOREIGN KEY (ID_PERSONA) REFERENCES "+ Constantes.TABLA_PERSONA+ "(ID_PERSONA))";

    private static final String CREAR_TABLA_DETALLE_PEDIDO = "CREATE TABLE " + Constantes.TABLA_DETALLE_PEDIDO +
            " (ID_DETALLE_PEDIDO INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " ID_PRODUCTO INT NOT NULL," +
            " ID_PEDIDO INT NOT NULL," +
            " CANT_PRODUCTO INT NOT NULL," +
            " FOREIGN KEY (ID_PEDIDO) REFERENCES "+ Constantes.TABLA_PEDIDO+ "(ID_PEDIDO)," +
            " FOREIGN KEY (ID_PRODUCTO) REFERENCES "+ Constantes.TABLA_PRODUCTO+ "(ID_PRODUCTO))";

    public DBHelper(@Nullable Context context) {
        super(context, Constantes.NOMBRE_BD, null, Constantes.VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Creación tabla usuarios
        sqLiteDatabase.execSQL(CREAR_TABLA_PERSONA);
        //Creación tabla PRODUCTOS
        sqLiteDatabase.execSQL(CREAR_TABLA_PRODUCTO);
        //Creación tabla PEDIDO
        sqLiteDatabase.execSQL(CREAR_TABLA_PEDIDO);
        //Creación tabla DETALLE PEDIDO
        sqLiteDatabase.execSQL(CREAR_TABLA_DETALLE_PEDIDO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Actualiza de la BD por cambio de versión
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constantes.TABLA_PERSONA);//Elimina si existe
        sqLiteDatabase.execSQL(CREAR_TABLA_PERSONA);//Creación de la tabla PERSONA después de eliminar
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constantes.TABLA_PRODUCTO);//Elimina si existe
        sqLiteDatabase.execSQL(CREAR_TABLA_PRODUCTO);//Creación de la tabla PRODUCTO después de eliminar
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constantes.TABLA_PEDIDO);//Elimina si existe
        sqLiteDatabase.execSQL(CREAR_TABLA_PEDIDO);//Creación de la tabla PEDIDO después de eliminar
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constantes.TABLA_DETALLE_PEDIDO);//Elimina si existe
        sqLiteDatabase.execSQL(CREAR_TABLA_DETALLE_PEDIDO);//Creación de la tabla TABLA PEDIDO después de eliminar

    }
}
