package com.example.prubatrabajofinal.Presenter.Usuario;

import com.example.prubatrabajofinal.Model.Historial.TrayectoriaModel;

import java.util.List;

public interface IUsuarioPresenter {
    public List<TrayectoriaModel> ObtenerHistorial(int id, String URL);
}

