package com.example.prubatrabajofinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.prubatrabajofinal.View.Autenticacion.Ingresar;
import com.example.prubatrabajofinal.View.Entrenamiento.Entrenamiento;
import com.example.prubatrabajofinal.View.Reproductor.Reproductor;
import com.example.prubatrabajofinal.View.Usuario.Usuario;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    Button btn_start;
    Chronometer chronometro;
    Boolean correr=false;
    long detenerse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences=getSharedPreferences("preferencesfile", Context.MODE_PRIVATE);
        Boolean FirsTime=preferences.getBoolean("FirstTime", true);

        if(FirsTime) {
            Intent intent = new Intent(this, Ingresar.class);
            startActivityForResult(intent, 0);
        }

        BottomNavigationView bnv=findViewById(R.id.bottom_navigation_view);
        bnv.setOnNavigationItemSelectedListener(navListener);
        bnv.setSelectedItemId(R.id.action_training);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.constraint_fragment,
                    new Entrenamiento()).commit();
        }

        btn_start=findViewById(R.id.btn_start);
        chronometro=findViewById(R.id.chronometro);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChronometro();
            }
        });

    }

    private void startChronometro() {
        if(!correr){
            chronometro.setBase(SystemClock.elapsedRealtime() - detenerse);
            chronometro.start();
            correr=true;
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment=null;
                    switch(menuItem.getItemId()){
                        case R.id.action_music:
                            selectedFragment=new Reproductor();
                            break;
                        case R.id.action_training:
                            selectedFragment=new Entrenamiento();
                            break;
                        case R.id.action_user:
                            selectedFragment=new Usuario();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.constraint_fragment,selectedFragment).commit();
                    return true;
                }
            };
}