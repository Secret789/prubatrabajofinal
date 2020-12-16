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
import com.example.prubatrabajofinal.Presenter.Usuario.IUsuarioPresenter;
import com.example.prubatrabajofinal.Presenter.Usuario.UsuarioPresenterCompl;
import com.example.prubatrabajofinal.R;
import com.example.prubatrabajofinal.View.Reproductor.MyAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class Usuario extends Fragment implements IUsuarioView{
    IUsuarioPresenter iUsuarioPresenter;

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

        iUsuarioPresenter=new UsuarioPresenterCompl(this,v);
        mRecyclerView = v.findViewById(R.id.recyclerView2);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        llenarArray(v);
        mAdapter = new MyAdapterTrayectoria(listTrayectoria,this.getContext());
        mRecyclerView.setAdapter(mAdapter);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mRecyclerView.setClipToOutline(true);
        }

        //dando valores al array de la grafica
        //String[]week={"lun","mar","mie","jue","vie","sab","dom"};
        String[]week=getArrayWeek();

        float []weekStats={0,0,0,0,0,0,0};
        graficaResumen.initArray(weekStats,week);

        button1=(Button)v.findViewById(R.id.button6);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertTrayectoria(getString(R.string.cloud_data)+"insertar_trayectoria.php");
            }
        });

        return v;
    }

    public void llenarArray(View v){
        listTrayectoria=new ArrayList<>();
        /*for (int i = 0; i < 20; i++) {
            listTrayectoria.add(new TrayectoriaModel(10,"10/10/10","01:20:30","01:40:30","00:12:30"));
        }*/
        iUsuarioPresenter.ObtenerHistorial(11,getString(R.string.cloud_data)+"obtener_trayectoria.php");
    }
    public String sumarDias(Date fecha, int dias){
        SimpleDateFormat formatFec = new SimpleDateFormat("dd/MM/yy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return formatFec.format(calendar.getTime());
    }
    public float []getArrayStatsWeek(List<TrayectoriaModel> arr){
        float[] arrayStats=new float[7];
        Date now =new Date();
        for(int i=0;i<arr.size();i++){
            if(arr.get(i).traFec.equals(sumarDias(now,0))){
                arrayStats[6]+=Integer.parseInt(arr.get(i).traDur);
            } else if(arr.get(i).traFec.equals(sumarDias(now,-1))){
                arrayStats[5]+=Integer.parseInt(arr.get(i).traDur);
            } else if(arr.get(i).traFec.equals(sumarDias(now,-2))){
                arrayStats[4]+=Integer.parseInt(arr.get(i).traDur);
            } else if(arr.get(i).traFec.equals(sumarDias(now,-3))){
                arrayStats[3]+=Integer.parseInt(arr.get(i).traDur);
            } else if(arr.get(i).traFec.equals(sumarDias(now,-4))){
                arrayStats[3]+=Integer.parseInt(arr.get(i).traDur);
            } else if(arr.get(i).traFec.equals(sumarDias(now,-5))){
                arrayStats[1]+=Integer.parseInt(arr.get(i).traDur);
            } else if(arr.get(i).traFec.equals(sumarDias(now,-6))) {
                arrayStats[0] += Integer.parseInt(arr.get(i).traDur);
            }
        }
        return arrayStats;
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
    public void updateRecicle(List<TrayectoriaModel> lista){
        SimpleDateFormat formatFec = new SimpleDateFormat("dd/MM/yy");
        Date now=new Date();


        mAdapter.mDataSet=lista;
        mAdapter.notifyDataSetChanged();
        String[]week=getArrayWeek();
        float[]weekStats=getArrayStatsWeek(lista);
        graficaResumen.initArray(weekStats,week);
        graficaResumen.invalidate();
        graficaResumen.refreshDrawableState();


        Toast.makeText(getContext(), "Se actualizo los datos ", Toast.LENGTH_SHORT).show();
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
                SimpleDateFormat formatOri = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date now=new Date();
                parametros.put("fecha",formatOri.format(now));
                parametros.put("horaini",formatOri.format(now));
                parametros.put("horafin",formatOri.format(now));
                parametros.put("usuario","11");
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        requestQueue.add(stringRequest);

    }
}