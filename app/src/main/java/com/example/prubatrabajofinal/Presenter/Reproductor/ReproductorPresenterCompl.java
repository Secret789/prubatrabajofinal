package com.example.prubatrabajofinal.Presenter.Reproductor;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;

import com.example.prubatrabajofinal.MainActivity;
import com.example.prubatrabajofinal.Model.Reproductor.MusicaModel;
import com.example.prubatrabajofinal.View.Reproductor.IReproductorView;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.Duration;


public class ReproductorPresenterCompl implements IReproductorPresenter{
    IReproductorView iReproductorView;

    public ReproductorPresenterCompl(IReproductorView iReproductorView){
        this.iReproductorView=iReproductorView;

    }
    public List<MusicaModel> obtenerArchivosmp3(View v){
        List<MusicaModel> musicList=new ArrayList<>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Audio.AudioColumns.DATA, MediaStore.Audio.AudioColumns.TITLE, MediaStore.Audio.AudioColumns.DURATION, MediaStore.Audio.ArtistColumns.ARTIST,};
        Cursor c = v.getContext().getContentResolver().query(uri, projection, null, null, null);
        //Cursor c = v.getContext().getContentResolver().query(uri, projection, MediaStore.Audio.Media.DATA + " like ? ", new String[]{"%Samsung/Music%"}, null);
            if (c != null) {
            while (c.moveToNext()) {
                MusicaModel musicaModel = new MusicaModel();
                String path = c.getString(0);
                String name = c.getString(1);
                String duration = c.getString(2);
                String artist = c.getString(3);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    java.time.Duration durationn = java.time.Duration.ofMillis(Long.parseLong(duration));
                    long hours=durationn.toHours();
                    long mins = durationn.minusHours(hours).toMinutes();
                    long sec=durationn.minusHours(hours).minusMinutes(mins).getSeconds();
                    String formatted = String.format("%02d:%02d:%02d", hours, mins, sec);
                    duration=formatted;
                }
                musicaModel.nombre=name;
                musicaModel.duracion=duration;
                musicaModel.autor=artist;
                musicaModel.direccion=path;

                musicList.add(musicaModel);
            }
            c.close();

        }

        return musicList;
    }
}
