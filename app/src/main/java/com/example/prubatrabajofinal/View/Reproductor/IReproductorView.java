package com.example.prubatrabajofinal.View.Reproductor;

import android.view.View;

public interface IReproductorView {
    public void changeColorButtom(View v);
    public void playPause(View v);
    public void stop(View v);
    public void llenarArrayMusic(View v);
    public void next(View v);
    public void before(View v);
    public void checkPermission(View v);
}
