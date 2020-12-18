package com.example.prubatrabajofinal.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.prubatrabajofinal.DataBase.SQLiteHelper;
import com.example.prubatrabajofinal.Model.Autenticacion.UsuarioModel;

public class UsuarioAdapter {
    private  final String NOMBRE_DB= "healythe.db";
    private final int VERSION =1;
    public static final String NOMBRE_TABLA ="usuarios";

    public static final String SCRIPT_NUEVA_TABLA = "CREATE TABLE " + NOMBRE_TABLA +
            "(" +
            "id integer primary key autoincrement, " +
            "nombre text, " +
            "usuario text, " +
            "contrasena text, " +
            "apellido text " +
            ");"
            ;
    private static SQLiteDatabase database;
    private static SQLiteHelper helper;
    private final Context context;
    private UsuarioModel model;

    public UsuarioAdapter(Context context){
        this.context= context;
        helper= new SQLiteHelper(context,NOMBRE_DB,null,VERSION);
    }

    public void openRead() {
        database =helper.getReadableDatabase();
    }
    public void openWrite(){
        database =helper.getWritableDatabase();
    }
    public void close(){

        database.close();
    }

    public UsuarioModel login(String usuario, String contraseña){
        model = new UsuarioModel();
        String where = "usuario = ? AND contrasena = ?";
        Cursor cursor = database.query(NOMBRE_TABLA,null,where,new String[]{usuario,contraseña},null,null,null);
        if(cursor.getCount()< 1){
            return model;
        }else{
            cursor.moveToFirst();
            model.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
            model.set_nombre(cursor.getString(cursor.getColumnIndex("nombre")));
            model.set_usuario(usuario);
            model.set_contrasena(contraseña);
            return model;
        }
    }
    public long insert(UsuarioModel model){
        ContentValues values= new ContentValues();
        values.put("nombre",model.get_nombre());
        values.put("usuario",model.get_usuario());
        values.put("contrasena",model.get_contrasena());
        values.put("apellido",model.get_apellido());
        return database.insert(NOMBRE_TABLA,null,values);
    }

}