package com.batchmates.android.test6.view;

import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.batchmates.android.test6.R;
import com.batchmates.android.test6.model.MovieListPojo;
import com.batchmates.android.test6.view.secondactivity.Main2Activity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 7/28/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    List<MovieListPojo> movieListPojos=new ArrayList<>();
    String currentMovieType;
    private double ratingDouble;

    public RecyclerAdapter(List<MovieListPojo> movieListPojos,String currentMovieType) {
        this.movieListPojos = movieListPojos;
        this.currentMovieType=currentMovieType;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_recycle_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final MovieListPojo pojo=movieListPojos.get(position);

        holder.movieTitle.setText(pojo.getTitle());
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w185_and_h278_bestv2/"+pojo.getImageurl()).into(holder.movieImage);
        ratingDouble=pojo.getUserRating();
        holder.userRating.setText(String.valueOf(ratingDouble)+"/10.0");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),holder.movieTitle.getText(),Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(view.getContext(), Main2Activity.class);
                intent.putExtra("TITLE",holder.movieTitle.getText());
                intent.putExtra("IMAGE",pojo.getImageurl());
                intent.putExtra("DISC",pojo.getOverview());
                intent.putExtra("USER",pojo.getUserRating());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieListPojos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView movieImage;
        TextView movieTitle;
        TextView userRating;
        public ViewHolder(View itemView) {
            super(itemView);
            movieImage=itemView.findViewById(R.id.ivMovieImage);
            movieTitle=itemView.findViewById(R.id.tvMovieTitle);
            userRating=itemView.findViewById(R.id.tvUserRating);
        }
    }
}
