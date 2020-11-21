package com.example.prubatrabajofinal.View.Usuario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.prubatrabajofinal.R;

import java.util.Calendar;
import java.util.Date;

public class Usuario extends Fragment {
    public GraficaResumen graficaResumen;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_usuario, container, false);
        // Inflate the layout for this fragment
        //activity objects
        graficaResumen=(GraficaResumen) v.findViewById(R.id.ResumenView);

        //dando valores al array de la grafica
        //String[]week={"lun","mar","mie","jue","vie","sab","dom"};
        String[]week=getArrayWeek();

        float []weekStats={100,200,300,80,60,94,80};
        graficaResumen.initArray(weekStats,week);

        return v;
    }
    public String [] getArrayWeek(){
        String[]week=new String[7];
        Date fecha =new Date();
        Calendar cal =Calendar.getInstance();
        cal.setTime(fecha);
        int dayWeek=cal.get(Calendar.DAY_OF_WEEK);
        switch (dayWeek){
            case 1:
                week=new String[]{"Lun", "Mar", "Mie", "Jue", "Vie", "Sab", "Hoy"};
                break;
            case 2:
                week=new String[]{"Mar", "Mie", "Jue", "Vie", "Sab", "Dom", "Hoy"};
                break;
            case 3:
                week=new String[]{"Mie", "Jue", "Vie", "Sab", "Dom", "Lun", "Hoy"};
                break;
            case 4:
                week=new String[]{"Jue", "Vie", "Sab", "Dom", "Lun", "Mar", "Hoy"};
                break;
            case 5:
                week=new String[]{"Vie", "Sab", "Dom", "Lun", "Mar", "Mie", "Hoy"};
                break;
            case 6:
                week=new String[]{"Sab", "Dom", "Lun", "Mar", "Mie", "Jue", "Hoy"};
                break;
            case 7:
                week=new String[]{"Dom", "Lun", "Mar", "Mie", "Jue", "Vie", "Hoy"};
                break;
        }
        return week;
    }
}