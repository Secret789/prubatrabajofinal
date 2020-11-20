package com.example.prubatrabajofinal.Model.Reproductor;

public class MusicaModel {
    public String direccion;
    public String nombre;
    public String autor;
    public String duracion;
    public MusicaModel(){

    }
    public MusicaModel(String direccion,String nombre, String autor, String duracion){
        this.nombre=nombre;
        this.autor=autor;
        this.duracion=duracion;
        this.direccion=direccion;
    }

}
