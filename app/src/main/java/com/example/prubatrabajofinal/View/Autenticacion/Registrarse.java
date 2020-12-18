package com.example.prubatrabajofinal.View.Autenticacion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prubatrabajofinal.Model.Autenticacion.UsuarioModel;
import com.example.prubatrabajofinal.R;
import com.example.prubatrabajofinal.adapters.UsuarioAdapter;

public class Registrarse extends AppCompatActivity {
    // private Spinner spinner;
    //                 usuario          ccontraseña      nonmbres               apeliido
    private EditText t_usuario, editTextTextPassword,editTextTextPassword3,editTextTextPassword2;

    private Button button5;
    private UsuarioAdapter adapter;
    private UsuarioModel model;


    private static final String[]paths = {"No especificado", "Masculino", "Femenino"};





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
/*
        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Registrarse.this, R.layout.spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
*/
        init();


        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = t_usuario.getText().toString();
                String contrasena = editTextTextPassword.getText().toString();
                String nombre = editTextTextPassword3.getText().toString();
                String apellido = editTextTextPassword2.getText().toString();
                //   String genero=spinner.getText().toString();
                boolean validarInterfaz=validarCampos(usuario,contrasena,apellido,nombre);
                if(validarInterfaz){
                    model=new UsuarioModel(nombre,usuario,contrasena,apellido);
                    adapter.openWrite();
                    long valorInsert=adapter.insert(model);
                    adapter.close();
                    if (valorInsert>0){
                        Toast.makeText(Registrarse.this, " Registro Exitoso", Toast.LENGTH_LONG).show();
                        Intent login= new Intent(Registrarse.this,Ingresar.class);
                        login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(login);
                    }else{
                        Toast.makeText(Registrarse.this, " Registro no Exitoso", Toast.LENGTH_LONG).show();

                    }

                }
            }
        });
    }

    public void init() {
        button5 = findViewById(R.id.button5);


        t_usuario = findViewById(R.id.t_usuario);

        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        editTextTextPassword3 = findViewById(R.id.editTextTextPassword3);
        editTextTextPassword2 = findViewById(R.id.editTextTextPassword2);
        adapter = new UsuarioAdapter(getApplicationContext());
        model = new UsuarioModel();

    }




    public boolean validarCampos(String usuario, String contraseña,String apellido,String nombre) {
        if (usuario.isEmpty() || contraseña.isEmpty()|| nombre.isEmpty()|| apellido.isEmpty() ){
            Toast.makeText(this, "Por favor ingrese todos los datos", Toast.LENGTH_LONG).show();
            return false;
        } else if (usuario.length() < 8 || contraseña.length() < 8) {
            Toast.makeText(this, "Por favor ingrese Usuario y contraseña(min 8 caracteres)", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}