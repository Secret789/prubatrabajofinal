package com.example.prubatrabajofinal.View.Autenticacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.prubatrabajofinal.MainActivity;
import com.example.prubatrabajofinal.R;
import com.example.prubatrabajofinal.View.Reproductor.Reproductor;

public class Ingresar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);
        Button btn1 = (Button) findViewById(R.id.button4);
        Button btn2 = (Button) findViewById(R.id.button5);

        TextView txtclick = findViewById(R.id.textView11);

        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setPreferences();
            }

        });

        btn2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setPreferences();
            }

        });

        txtclick.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), Registrarse.class);
                startActivityForResult(intent, 0);
            }

        });

    }
    private void setPreferences(){
        SharedPreferences preferences=getSharedPreferences("preferencesfile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =preferences.edit();
        editor.putBoolean("FirstTime",false);
        editor.commit();
        Intent intent = new Intent (this, MainActivity.class);
        startActivityForResult(intent, 0);
    }
}