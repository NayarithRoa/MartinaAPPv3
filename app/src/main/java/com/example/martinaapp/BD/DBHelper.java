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
            " IMAGEN BLOB NOT NULL," +
            " CANTIDAD INT)" ;


    private static final String CREAR_TABLA_PEDIDO = "CREATE TABLE " + Constantes.TABLA_PEDIDO +
            " (ID_PEDIDO INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " ID_PERSONA INT NOT NULL," +
            " ID_PRODUCTO INT NOT NULL," +
            " FECHA DATE NOT NULL, " +
            " HORA TIME NOT NULL," +
            " COSTO_TOTAL DOUBLE NOT NULL," +
            " CANT_PRODUCTO INT NOT NULL," +
            " FOREIGN KEY (ID_PERSONA) REFERENCES "+ Constantes.TABLA_PERSONA+ "(ID_PERSONA),"+
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

    }
}
