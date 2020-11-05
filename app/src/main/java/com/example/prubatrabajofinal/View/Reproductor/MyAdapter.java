package com.example.prubatrabajofinal.View.Reproductor;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.prubatrabajofinal.Model.Reproductor.Musica;
import com.example.prubatrabajofinal.R;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Musica[] mDataSet;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout textView;
        public ViewHolder(RelativeLayout tv) {
            super(tv);
            textView = tv;
        }
    }
    public MyAdapter(Musica[] myDataSet) {
        mDataSet = myDataSet;
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
        TextView n= holder.textView.findViewById(R.id.tv_name);
        n.setText(mDataSet[position].nombre);
        TextView n1= holder.textView.findViewById(R.id.tv_title);
        n1.setText(mDataSet[position].autor);
        TextView n2= holder.textView.findViewById(R.id.tv_company);
        n2.setText(mDataSet[position].duracion);
        ImageView image = holder.textView.findViewById(R.id.iv_avatar);
        image.setImageResource(R.drawable.music_no_found);
    }
    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}