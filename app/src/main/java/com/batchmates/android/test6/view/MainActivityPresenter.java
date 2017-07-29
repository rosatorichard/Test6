package com.batchmates.android.test6.view;

import android.util.Log;

import com.batchmates.android.test6.BaseView;
import com.batchmates.android.test6.injection.MainActivityComponent;
import com.batchmates.android.test6.model.MovieListPojo;
import com.batchmates.android.test6.model.MoviesPojo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Android on 7/28/2017.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private static final String TAG = "MainActivityPresenter";
    MainActivityContract.View view;
    List<MovieListPojo> movieListPojo=new ArrayList<>();
    private String title;
    private int counter=1;
    private int maxPages=10;


    @Override
    public void addView(MainActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {

    }

    @Override
    public void parseJson(String s) {
        int sum=0;
        try {
            JSONObject json = new JSONObject(s);
            JSONObject menu = json.getJSONObject("menu");
            JSONArray items = menu.getJSONArray("items");
            //1,7
            JSONObject currentItems = items.getJSONObject(1);
            String Label=currentItems.getString("label");
            String subString= Label.substring(6);
            int first=Integer.parseInt(subString);
            sum+=first;
            Log.d(TAG, "parseJson: "+first);
            currentItems = items.getJSONObject(7);
            Label=currentItems.getString("label");
            subString= Label.substring(6);
            first=Integer.parseInt(subString);
            Log.d(TAG, "parseJson: "+first);
            sum+=first;

            System.out.println(sum);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void callMovies(String s) {

        counter=1;
        movieListPojo.clear();
        retrofit2.Call<MoviesPojo> makecall =RetroFitHelper.getmovies(s,counter);
        makecall.enqueue(new Callback<MoviesPojo>() {
            @Override
            public void onResponse(Call<MoviesPojo> call, Response<MoviesPojo> response) {
                Log.d(TAG, "onResponse: "+response.body());
                for (int i = 0; i < response.body().getResults().size(); i++) {

                    if (response.body().getResults().get(i).getOriginalTitle()==null)
                    {
                        title=response.body().getResults().get(i).getTitle();
                    }
                    else
                    {
                        title=response.body().getResults().get(i).getOriginalTitle();
                    }
                    movieListPojo.add(new MovieListPojo(title,
                            response.body().getResults().get(i).getPosterPath(),
                            response.body().getResults().get(i).getOverview(),
                            response.body().getResults().get(i).getVoteAverage()));
                    Log.d(TAG, "onResponse: "+response.body().getResults().get(i).getVoteAverage());
//                    Log.d(TAG, "onResponse: "+response.body().getResults().get(i).getPosterPath());
                }
                Log.d(TAG, "onResponse: "+response.body().getTotalPages());
                maxPages=response.body().getTotalPages();
                view.RecieveList(movieListPojo);
            }

            @Override
            public void onFailure(Call<MoviesPojo> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.toString());

            }
        });
    }

    @Override
    public void nextPAge(String s) {

        if(counter==maxPages)
        {
            Log.d(TAG, "nextPAge: At the max page");
        }
        else
        {
            counter++;
            retrofit2.Call<MoviesPojo> makecall = RetroFitHelper.getmovies(s, counter);
            makecall.enqueue(new Callback<MoviesPojo>() {
                @Override
                public void onResponse(Call<MoviesPojo> call, Response<MoviesPojo> response) {
                    Log.d(TAG, "onResponse: " + response.body());
                    for (int i = 0; i < response.body().getResults().size(); i++) {

                        if (response.body().getResults().get(i).getOriginalTitle() == null) {
                            title = response.body().getResults().get(i).getTitle();
                        } else {
                            title = response.body().getResults().get(i).getOriginalTitle();
                        }
                        movieListPojo.add(new MovieListPojo(title,
                                response.body().getResults().get(i).getPosterPath(),
                                response.body().getResults().get(i).getOverview(),
                                response.body().getResults().get(i).getVoteAverage()));
                        Log.d(TAG, "onResponse: " + response.body().getResults().get(i).getVoteAverage());
//                    Log.d(TAG, "onResponse: "+response.body().getResults().get(i).getPosterPath());
                    }
                    Log.d(TAG, "onResponse: " + movieListPojo);
                    view.updateList(movieListPojo);
                }

                @Override
                public void onFailure(Call<MoviesPojo> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.toString());

                }
            });
        }
    }
}
