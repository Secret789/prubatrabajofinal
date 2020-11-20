package com.example.prubatrabajofinal.Presenter.Reproductor;

import android.view.View;

import com.example.prubatrabajofinal.Model.Reproductor.MusicaModel;

import java.util.List;

public interface IReproductorPresenter {
    public List<MusicaModel> obtenerArchivosmp3(View v);
}
