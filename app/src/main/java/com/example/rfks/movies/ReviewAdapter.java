package com.example.rfks.movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private List<Review> reviews;
    private int rowLayout;
    private Context context;


    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        LinearLayout reviewsLayout;
        TextView review;



        public ReviewViewHolder(View v) {
            super(v);
            reviewsLayout = (LinearLayout) v.findViewById(R.id.reviews_layout);
            review = (TextView) v.findViewById(R.id.review);
        }
    }

    public ReviewAdapter(List<Review> reviews, int rowLayout, Context context) {
        this.reviews = reviews;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public ReviewAdapter.ReviewViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ReviewViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ReviewViewHolder holder, final int position) {
        holder.review.setText(reviews.get(position).getContent()+" - "+reviews.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

}
