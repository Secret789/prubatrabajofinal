package com.example.prubatrabajofinal.View.Usuario;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.prubatrabajofinal.Model.Historial.TrayectoriaModel;
import com.example.prubatrabajofinal.Model.Reproductor.MusicaModel;
import com.example.prubatrabajofinal.R;
import com.example.prubatrabajofinal.View.Reproductor.MyAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class Usuario extends Fragment {
    public GraficaResumen graficaResumen;
    Button button1;
    private RecyclerView mRecyclerView;
    private MyAdapterTrayectoria mAdapter;
    private static List<TrayectoriaModel> listTrayectoria;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_usuario, container, false);
        // Inflate the layout for this fragment
        //activity objects
        graficaResumen=(GraficaResumen) v.findViewById(R.id.ResumenView);

        mRecyclerView = v.findViewById(R.id.recyclerView2);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        llenarArray();
        mAdapter = new MyAdapterTrayectoria(listTrayectoria,this.getContext());
        mRecyclerView.setAdapter(mAdapter);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mRecyclerView.setClipToOutline(true);
        }

        //dando valores al array de la grafica
        //String[]week={"lun","mar","mie","jue","vie","sab","dom"};
        String[]week=getArrayWeek();

        float []weekStats={100,200,300,80,0,94,80};
        graficaResumen.initArray(weekStats,week);

        button1=(Button)v.findViewById(R.id.button6);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertTrayectoria("https://vacillatory-grids.000webhostapp.com/runhealthy/insertar_trayectoria.php");
            }
        });

        return v;
    }
    public void llenarArray(){
        listTrayectoria=new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            listTrayectoria.add(new TrayectoriaModel(10,"10/10/10","01:20:30","01:40:30","00:12:30"));
        }
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


    public void InsertTrayectoria(String URL){
        StringRequest stringRequest =new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros =new HashMap<String, String>();
                parametros.put("fecha","prueba");
                parametros.put("horaini","prueba1");
                parametros.put("horafin","prueba2");
                parametros.put("usuario","11");
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        requestQueue.add(stringRequest);

    }
}