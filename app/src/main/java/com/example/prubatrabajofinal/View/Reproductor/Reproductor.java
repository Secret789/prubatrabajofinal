package com.example.prubatrabajofinal.View.Reproductor;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prubatrabajofinal.Model.Reproductor.MusicaModel;
import com.example.prubatrabajofinal.Presenter.Reproductor.IReproductorPresenter;
import com.example.prubatrabajofinal.Presenter.Reproductor.ReproductorPresenterCompl;
import com.example.prubatrabajofinal.R;

import java.io.IOException;
import java.util.List;

import static androidx.core.app.ActivityCompat.requestPermissions;
import static androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale;
import static androidx.core.content.ContextCompat.checkSelfPermission;

public class Reproductor extends Fragment implements IReproductorView{
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    public static int position = 0;
    private MediaPlayer[] myDataSet;
    private static MediaPlayer currentMusic;


    private IReproductorPresenter reproductorPresenter;
    Button btplay, btnext, btbefore;
    ImageView ivPortada;
    private static TextView tvNombre, tvDuracion;
    private static List<MusicaModel> listMusic;
    private static final int REQUEST_CODE_ASK_PERMISSIONS=123;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_reproductor, container, false);

        //init
        reproductorPresenter=new ReproductorPresenterCompl(this);

        //activity elements
        btplay=(Button) v.findViewById(R.id.button);
        btnext=(Button) v.findViewById(R.id.button3);
        btbefore=(Button) v.findViewById(R.id.button2);
        ivPortada=(ImageView) v.findViewById(R.id.imageView);
        tvNombre=v.findViewById(R.id.textView17);
        tvDuracion=v.findViewById(R.id.textView18);
        currentMusic=new MediaPlayer();

        checkPermission(v);

        changeColorButtom(v);

        btplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playPause(v);
            }
        });
        btnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next(v);
            }
        });
        btbefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                before(v);
            }
        });
        // Inflate the layout for this fragment
        return v;
    }
    public void changeColorButtom(View v){
        Drawable mIcon= ContextCompat.getDrawable(getActivity(), R.drawable.iniciar);
        mIcon.mutate().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        Button bt1 = v.findViewById(R.id.button);
        bt1.setBackground(mIcon);
        Drawable mIcon2= ContextCompat.getDrawable(getActivity(), R.drawable.anterior);
        mIcon2.mutate().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        Button bt2 = v.findViewById(R.id.button2);
        bt2.setBackground(mIcon2);
        Drawable mIcon3= ContextCompat.getDrawable(getActivity(), R.drawable.proximo);
        mIcon3.mutate().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        Button bt3 = v.findViewById(R.id.button3);
        bt3.setBackground(mIcon3);
    }
    public void playPause(View v){
        if(currentMusic.isPlaying()){
            currentMusic.pause();
            Drawable mIcon= ContextCompat.getDrawable(getActivity(), R.drawable.iniciar);
            mIcon.mutate().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
            btplay.setBackground(mIcon);
            Toast.makeText(v.getContext(),"Pausa",Toast.LENGTH_SHORT).show();

        }else{
            currentMusic.start();
            Drawable mIcon= ContextCompat.getDrawable(getActivity(), R.drawable.pausa);
            mIcon.mutate().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
            btplay.setBackground(mIcon);
            Toast.makeText(v.getContext(),"Empieza",Toast.LENGTH_SHORT).show();
        }

    }

    public void stop(View v){
        if(myDataSet[position]!=null){
            myDataSet[position].stop();
            llenarArrayMusic(v);
            position=0;
            Drawable mIcon= ContextCompat.getDrawable(getActivity(), R.drawable.iniciar);
            ivPortada.setBackgroundResource(R.drawable.music_no_found);
            mIcon.mutate().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
            btplay.setBackground(mIcon);

        }
    }
    public void llenarArrayMusic(View v) {
        mRecyclerView = v.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(listMusic,this.getContext());
        mRecyclerView.setAdapter(mAdapter);

        updateReproductor();
        changeCurrentMusic();
    }
    public static void changeCurrentMusic(){
        currentMusic.reset();
        currentMusic=new MediaPlayer();
        try {
            currentMusic.setDataSource(listMusic.get(position).direccion);
            currentMusic.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void updateReproductor(){
        tvNombre.setText(listMusic.get(position).nombre);
        tvDuracion.setText(listMusic.get(position).duracion);
    }
    public void next(View v){
        if(position<listMusic.size()-1){
            if(currentMusic.isPlaying()){
                currentMusic.stop();
                position++;
                updateReproductor();
                changeCurrentMusic();
                currentMusic.start();
            }else{
                position++;
                updateReproductor();
                changeCurrentMusic();
            }
        }else {
            Toast.makeText(v.getContext(),"No existen mas canciones",Toast.LENGTH_SHORT).show();
        }
    }

    public static void selected(){

        if(currentMusic.isPlaying()){
            currentMusic.stop();
            updateReproductor();
            changeCurrentMusic();
            currentMusic.start();
        }else{
            updateReproductor();
            changeCurrentMusic();
        }

    }
    public void before(View v){
        if(position>=1){
            if(currentMusic.isPlaying()){
                currentMusic.stop();
                position--;
                updateReproductor();
                changeCurrentMusic();
                currentMusic.start();
            }else{
                position--;
                updateReproductor();
                changeCurrentMusic();
            }
        }else {
            Toast.makeText(v.getContext(),"No existen mas canciones",Toast.LENGTH_SHORT).show();
        }
    }
    
    public void checkPermission(View v){
        int hasWriteContactsPermission = checkSelfPermission(this.getContext(),Manifest.permission.READ_EXTERNAL_STORAGE);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_PERMISSIONS);
        }else if (hasWriteContactsPermission == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(v.getContext(), "Ya se han concedido permisos", Toast.LENGTH_LONG).show();
            listMusic = reproductorPresenter.obtenerArchivosmp3(v);
            llenarArrayMusic(v);
        }
        return;
    }
}