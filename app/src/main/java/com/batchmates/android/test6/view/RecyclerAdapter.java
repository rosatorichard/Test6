package com.batchmates.android.test6.view;

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
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 7/28/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    List<MovieListPojo> movieListPojos=new ArrayList<>();
    String currentMovieType;

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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        MovieListPojo pojo=movieListPojos.get(position);

        holder.movieTitle.setText(pojo.getTitle());
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w185_and_h278_bestv2/"+pojo.getImageurl()).into(holder.movieImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"Clicked: "+holder.movieTitle.getText(),Toast.LENGTH_SHORT).show();
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
        public ViewHolder(View itemView) {
            super(itemView);
            movieImage=itemView.findViewById(R.id.ivMovieImage);
            movieTitle=itemView.findViewById(R.id.tvMovieTitle);
        }
    }
}
