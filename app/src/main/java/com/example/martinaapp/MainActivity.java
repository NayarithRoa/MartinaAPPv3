package com.example.martinaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.martinaapp.BD.DBPersonas;
import com.example.martinaapp.BD.DBProductos;
import com.example.martinaapp.BD.PersonasDefault;
import com.example.martinaapp.BD.ProductosDefault;

public class MainActivity extends AppCompatActivity {
    Button btnIniciarSesion;
    EditText txtCorreo, txtClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Relación de objetos con Layout
        txtCorreo = findViewById(R.id.txtCorreo);
        txtClave = findViewById(R.id.txtClave);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        //Registro datos en bd
        datosDefault();

        //Iniciar sesión
        btnIniciarSesion.setOnClickListener(v->{
            try{
                //Verificar existencia persona
                DBPersonas dbPersonas = new DBPersonas(this);
                if (txtCorreo.getText().toString().equals("")||txtClave.getText().toString().equals("")){
                    Toast.makeText(this, "Debe ingresar los datos solicitados", Toast.LENGTH_LONG).show();
                }
                else{
                    if(dbPersonas.autenticarPersonas(txtCorreo.getText().toString(),txtClave.getText().toString())){
                        Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, Inicio.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(this, "Usuario no registrado en el sistema", Toast.LENGTH_LONG).show();
                        txtCorreo.setText("");
                        txtClave.setText("");
                    }
                }

            }
            catch  (Exception e){
                Toast.makeText(this, "Error, no fue posible iniciar sesión", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void datosDefault(){
        //Creacion de registros para productos
        DBProductos dbProductos = new DBProductos(this);
        //Verifica creacion de productos por defecto
        if(!dbProductos.existeProductos()){
            ProductosDefault productosDefault = new ProductosDefault(this);
            productosDefault.insertaProductos();
        }

        //Creacion de personas
        DBPersonas dbPersonas = new DBPersonas(this);
        //Verifica existencia de usuarios
        if(!dbPersonas.existePersonas()){
            //Crea usuarios por defecto
            PersonasDefault personasDefault = new PersonasDefault(this);
            personasDefault.insertaPersona();
        }
    }
}