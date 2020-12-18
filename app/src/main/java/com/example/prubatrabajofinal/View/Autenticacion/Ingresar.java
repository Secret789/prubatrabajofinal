package com.example.prubatrabajofinal.View.Autenticacion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prubatrabajofinal.adapters.UsuarioAdapter;
import com.example.prubatrabajofinal.MainActivity;
import com.example.prubatrabajofinal.Model.Autenticacion.UsuarioModel;
import com.example.prubatrabajofinal.R;
public class Ingresar extends AppCompatActivity {
    private EditText et_main_usuario, et_main_contrasena;
    private TextView tx_main_registrarse;
    private Button btn_main_ingresar, btn_main_ahorano;

    private UsuarioAdapter adapter;
    private UsuarioModel model;
    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setTheme(R.style.);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);
        init();
        tx_main_registrarse = findViewById(R.id.textView11);

        btn_main_ingresar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                String usuario = et_main_usuario.getText().toString();
                String contrasena = et_main_contrasena.getText().toString();
                boolean validacionInterfaz = validarCampos(usuario, contrasena);
                if (validacionInterfaz) {
                    //inicio secion con BD
                    adapter.openRead();
                    model = adapter.login(usuario, contrasena);
                    adapter.close();
                    if (model != null) {
                        Toast.makeText(Ingresar.this, " Usuario  encontrado,iniciando sesion", Toast.LENGTH_LONG).show();

                        SharedPreferences.Editor editor= preferences.edit();
                        editor.putInt("usuario_id",model.get_id());
                        editor.putString("usuario_nombre",model.get_nombre());
                        editor.commit();

                        Intent contactos = new Intent(Ingresar.this, Contacto.class);
                        contactos.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(contactos);
                        // startActivity(contactos);
                    } else {
                        Toast.makeText(Ingresar.this, " Usuario  no encontrado ", Toast.LENGTH_LONG).show();
                    }

                } }

        });

        btn_main_ahorano.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("preferencesfile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("FirstTime", false);
                editor.commit();

                Intent intent = new Intent( v.getContext(), MainActivity.class);
                startActivity(intent);
            }

        });

        tx_main_registrarse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("preferencesfile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("FirstTime", false);
                editor.commit();

                Intent intent = new Intent(v.getContext(), Registrarse.class);
                startActivityForResult(intent, 0);
            }

        });

    }

    public void init() {

        btn_main_ingresar = (Button) findViewById(R.id.button4);
        btn_main_ahorano = (Button) findViewById(R.id.button5);
        tx_main_registrarse = findViewById(R.id.textView11);
        et_main_contrasena = findViewById(R.id.t_contraseña);
        et_main_usuario = findViewById(R.id.t_usuario);
        adapter = new UsuarioAdapter(getApplicationContext());
        model = new UsuarioModel();
        preferences=getSharedPreferences("Preferences",MODE_PRIVATE);
    }

    public boolean validarCampos(String usuario, String contraseña) {
        if (usuario.isEmpty() || contraseña.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese Usuario y contraseña", Toast.LENGTH_LONG).show();
            return false;
        } else if (usuario.length() < 8 || contraseña.length() < 8) {
            Toast.makeText(this, "Por favor ingrese Usuario y contraseña(min 8 caracteres)", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    private void setPreferences() {
        SharedPreferences preferences = getSharedPreferences("preferencesfile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("FirstTime", false);
        editor.commit();

        Intent intent = new Intent( this, MainActivity.class);
        startActivityForResult(intent,0);

    }
    private void validarSesion() {
        String usuario_id = preferences.getString("usuario_id", null);
        String usuario_nombre = preferences.getString("usuario_nombre", null);

        Intent contactos = new Intent(Ingresar.this, Contacto.class);
        startActivity(contactos);

    }
    //startActivityForResult(intent, 0);


}