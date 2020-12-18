package com.example.prubatrabajofinal.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.prubatrabajofinal.adapters.UsuarioAdapter;


public class SQLiteHelper extends SQLiteOpenHelper {


    public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(UsuarioAdapter.SCRIPT_NUEVA_TABLA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ UsuarioAdapter.NOMBRE_TABLA);
        onCreate(sqLiteDatabase);
    }
}
