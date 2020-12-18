package com.example.prubatrabajofinal.Model.Autenticacion;

import java.io.Serializable;

public class ContactoModel implements Serializable {
    private int _id;
     private String _nombre;
     private String _apellido;
     private String _celular;
     private String _fijo;

    public ContactoModel() {
    }

    public ContactoModel(String _nombre, String _apellido, String _celular, String _fijo) {
        this._nombre = _nombre;
        this._apellido = _apellido;
        this._celular = _celular;
        this._fijo = _fijo;
    }

    public ContactoModel(int _id, String _nombre, String _apellido, String _celular, String _fijo) {
        this._id = _id;
        this._nombre = _nombre;
        this._apellido = _apellido;
        this._celular = _celular;
        this._fijo = _fijo;
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

    public String get_apellido() {
        return _apellido;
    }

    public void set_apellido(String _apellido) {
        this._apellido = _apellido;
    }

    public String get_celular() {
        return _celular;
    }

    public void set_celular(String _celular) {
        this._celular = _celular;
    }

    public String get_fijo() {
        return _fijo;
    }

    public void set_fijo(String _fijo) {
        this._fijo = _fijo;
    }
}