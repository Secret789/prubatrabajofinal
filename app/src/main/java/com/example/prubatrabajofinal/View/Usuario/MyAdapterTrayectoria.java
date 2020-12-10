package com.example.prubatrabajofinal.View.Usuario;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.prubatrabajofinal.Model.Historial.TrayectoriaModel;
import com.example.prubatrabajofinal.Model.Reproductor.MusicaModel;
import com.example.prubatrabajofinal.R;
import com.example.prubatrabajofinal.View.Autenticacion.Registrarse;
import com.example.prubatrabajofinal.View.Historial.Historial;
import com.example.prubatrabajofinal.View.Reproductor.Reproductor;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import static androidx.core.app.ActivityCompat.startActivityForResult;
import static androidx.core.content.ContextCompat.startActivity;

public class MyAdapterTrayectoria extends RecyclerView.Adapter<MyAdapterTrayectoria.ViewHolder> {
    //private MusicaModel[] mDataSet;
    List<TrayectoriaModel> mDataSet;
    Context context;
    public int selected_position = -1;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public RelativeLayout textView;
        public ViewHolder(RelativeLayout tv) {
            super(tv);
            textView = tv;
            textView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (getAdapterPosition() == RecyclerView.NO_POSITION) return;

            // Updating old as well as new positions
            notifyItemChanged(selected_position);
            selected_position = getAdapterPosition();
            notifyItemChanged(selected_position);

            /*Reproductor.position=selected_position;
            Reproductor.selected();*/
            Intent intent = new Intent(view.getContext(), Historial.class);
            Bundle mBundle = new Bundle();
            intent.putExtra("id_trayectoria", mDataSet.get(selected_position).traId);
            startActivity(context,intent,mBundle);


        }
    }
    public MyAdapterTrayectoria(List<TrayectoriaModel> myDataSet, Context context) {
        mDataSet = myDataSet;
        this.context=context;
    }
    @Override
    public MyAdapterTrayectoria.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_text_view_trayectoria, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(selected_position!=-1) {
            holder.itemView.setSelected(selected_position == position);
            holder.itemView.setBackgroundColor(selected_position == position ? context.getResources().getColor(R.color.colorPrimaryLight) : Color.TRANSPARENT);
        }
        TextView n= holder.textView.findViewById(R.id.tv_fecha);
        n.setText("Fecha: "+mDataSet.get(position).traFec);
        TextView n1= holder.textView.findViewById(R.id.tv_dura);
        n1.setText("Duracion: "+mDataSet.get(position).traDur);
        TextView n2= holder.textView.findViewById(R.id.tv_fin);
        n2.setText("Fin: "+mDataSet.get(position).traFin);
        TextView n3= holder.textView.findViewById(R.id.tv_ini);
        n3.setText("Inicio: "+mDataSet.get(position).traIni);

    }
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}