package com.example.prubatrabajofinal.View.Historial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prubatrabajofinal.Model.Historial.TrayectoriaModel;
import com.example.prubatrabajofinal.Presenter.Historial.HistorialPresenterCompl;
import com.example.prubatrabajofinal.Presenter.Historial.IHistorialPresenter;
import com.example.prubatrabajofinal.R;
import com.example.prubatrabajofinal.View.Autenticacion.Registrarse;
import com.example.prubatrabajofinal.View.Usuario.Usuario;

import androidx.appcompat.app.AppCompatActivity;

public class Historial extends AppCompatActivity implements IHistorialView{
    IHistorialPresenter iHistorialPresenter;
    TextView tvFec,tvDur,tvIni,tvFin,tvDur2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvFec=findViewById(R.id.textView2);
        tvDur=findViewById(R.id.textView4);
        tvIni=findViewById(R.id.textView5);
        tvFin=findViewById(R.id.textView6);
        tvDur2=findViewById(R.id.textView22);

        Bundle bundle=getIntent().getExtras();
        int traId=-1;
        if(bundle!=null)
            traId=bundle.getInt("id_trayectoria");
        //Toast.makeText(this,getString(R.string.cloud_data)+"obtener_trayectoria_id.php?"+traId,Toast.LENGTH_LONG).show();
        iHistorialPresenter =new HistorialPresenterCompl(this,findViewById(android.R.id.content));
        iHistorialPresenter.ObtenerTrayectoria(traId,getString(R.string.cloud_data)+"obtener_trayectoria_id.php");



    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
    public void updateData(TrayectoriaModel tm){
        tvFec.setText("Fecha: "+tm.traFec);
        tvDur.setText("Duracion: "+tm.traDur+" min");
        tvIni.setText("Inicio: "+tm.traIni);
        tvFin.setText("Fin: "+tm.traFin);
        tvDur2.setText(tm.traDur+" min");

    }
}
