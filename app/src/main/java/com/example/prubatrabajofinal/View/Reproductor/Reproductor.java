package com.example.prubatrabajofinal.View.Reproductor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.prubatrabajofinal.Model.Reproductor.Musica;
import com.example.prubatrabajofinal.R;

public class Reproductor extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;

    private static final Musica[] myDataSet = {
            new Musica("Nombre de cancion 1","Autor 1","00:05:12"),
            new Musica("Nombre de cancion 2","Autor 2","00:03:62"),
            new Musica("Nombre de cancion 3","Autor 3","00:05:45"),
            new Musica("Nombre de cancion 4","Autor 4","00:04:14"),
            new Musica("Nombre de cancion 5","Autor 4","00:05:34"),
            new Musica("Nombre de cancion 6","Autor 6","00:01:26"),
            new Musica("Nombre de cancion 7","Autor 7","00:04:30"),
            new Musica("Nombre de cancion 8","Autor 8","00:06:45"),
            new Musica("Nombre de cancion 9","Autor 9","00:05:12"),
            new Musica("Nombre de cancion 10","Autor 10","00:03:62"),
            new Musica("Nombre de cancion 11","Autor 11","00:05:45"),
            new Musica("Nombre de cancion 12","Autor 12","00:04:14"),
            new Musica("Nombre de cancion 13","Autor 13","00:05:34"),
            new Musica("Nombre de cancion 14","Autor 14","00:01:26"),
            new Musica("Nombre de cancion 15","Autor 15","00:04:30"),
            new Musica("Nombre de cancion 16","Autor 16","00:06:45"),
            new Musica("Nombre de cancion 17","Autor 17","00:05:23")
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(myDataSet);
        mRecyclerView.setAdapter(mAdapter);
    }
}