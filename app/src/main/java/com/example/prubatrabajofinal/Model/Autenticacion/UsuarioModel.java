package com.example.prubatrabajofinal.Model.Autenticacion;

import java.io.Serializable;

public class UsuarioModel  implements Serializable {

    private int _id;
    private String _nombre;
    private String _usuario;
    private String _contrasena;
    private String _apellido;


    public UsuarioModel() {

    }

    public UsuarioModel(int _id, String _nombre, String _usuario, String _contrasena,String _apellido){
        this._id = _id;
        this._nombre = _nombre;
        this._usuario = _usuario;
        this._contrasena = _contrasena;
        this._apellido=_apellido;
    }



    public UsuarioModel(String _nombre, String _usuario,String contrasena,String _apellido) {
        this._nombre = _nombre;
        this._usuario = _usuario;
        this._contrasena = _contrasena;
        this._apellido=_apellido;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String get_usuario() {
        return _usuario;
    }

    public void set_usuario(String _apellido) {
        this._usuario = _apellido;
    }

    public String get_contrasena() {
        return _contrasena;
    }

    public void set_contrasena(String _contraseña) {
        this._contrasena = _contrasena;
    }

    public String get_apellido() {
        return _apellido;
    }

    public void setapellido(String _contraseña) {
        this._apellido = _apellido;
    }

}
