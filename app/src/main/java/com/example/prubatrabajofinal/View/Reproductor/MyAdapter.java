package com.example.prubatrabajofinal.View.Reproductor;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prubatrabajofinal.Model.Reproductor.MusicaModel;
import com.example.prubatrabajofinal.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    //private MusicaModel[] mDataSet;
    List<MusicaModel> mDataSet;
    Context context;
    public int selected_position = 0;

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

            Reproductor.position=selected_position;
            Reproductor.selected();


        }
    }
    public MyAdapter(List<MusicaModel> myDataSet, Context context) {
        mDataSet = myDataSet;
        this.context=context;
    }
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_text_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.itemView.setSelected(selected_position == position);
        holder.itemView.setBackgroundColor(selected_position == position ? context.getResources().getColor(R.color.colorPrimaryLight) : Color.TRANSPARENT);

        TextView n= holder.textView.findViewById(R.id.tv_name);
        n.setText(mDataSet.get(position).nombre);
        TextView n1= holder.textView.findViewById(R.id.tv_title);
        n1.setText(mDataSet.get(position).autor);
        TextView n2= holder.textView.findViewById(R.id.tv_company);
        n2.setText(mDataSet.get(position).duracion);
        ImageView image = holder.textView.findViewById(R.id.iv_avatar);
        image.setImageResource(R.drawable.music_no_found);
    }
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}