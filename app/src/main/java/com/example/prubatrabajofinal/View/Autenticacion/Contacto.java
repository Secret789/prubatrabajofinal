package com.example.prubatrabajofinal.View.Autenticacion;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prubatrabajofinal.MainActivity;
import com.example.prubatrabajofinal.R;

public class Contacto extends AppCompatActivity {

    private TextView textView9;
    private Button btn_main_ingresar;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setTheme(R.style.);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        init();


        int usuario_id =preferences.getInt("usuario_id",0);
        String usuario_nombre =  preferences.getString("usuario_nombre",null);

        if(usuario_id >0  && usuario_nombre != null) {
            textView9.setText(usuario_nombre);
        }

        btn_main_ingresar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences = getSharedPreferences("preferencesfile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("FirstTime", false);
                editor.commit();

                Intent intent = new Intent( Contacto.this, MainActivity.class);
                startActivityForResult(intent,0);
            }

        });


    }

    private  void init(){
        textView9=findViewById(R.id.textView9);
        btn_main_ingresar=findViewById(R.id.button4);
        preferences=getSharedPreferences("Preferences",MODE_PRIVATE);

    }
    //startActivityForResult(intent, 0);


}