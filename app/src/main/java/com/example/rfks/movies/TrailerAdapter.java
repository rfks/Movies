package com.example.rfks.movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {

    private List<Trailer> trailers;
    private int rowLayout;
    private Context context;


    public static class TrailerViewHolder extends RecyclerView.ViewHolder {
        LinearLayout trailersLayout;
        TextView trailer;



        public TrailerViewHolder(View v) {
            super(v);
            trailersLayout = (LinearLayout) v.findViewById(R.id.trailers_layout);
            trailer = (TextView) v.findViewById(R.id.trailer_name);
        }
    }

    public TrailerAdapter(List<Trailer> trailers, int rowLayout, Context context) {
        this.trailers = trailers;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public TrailerAdapter.TrailerViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new TrailerViewHolder(view);
    }


    @Override
    public void onBindViewHolder(TrailerViewHolder holder, final int position) {
        holder.trailer.setText(trailers.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

}
