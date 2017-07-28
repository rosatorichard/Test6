package com.batchmates.android.test6.view;

import android.graphics.pdf.PdfDocument;

import com.batchmates.android.test6.model.MoviesPojo;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Android on 7/28/2017.
 */

public class RetroFitHelper {


    private static final String BASE_URL = "https://api.themoviedb.org/";
    private static final String API_KEY="8ef776a387dafe64e2aec4fe5203c787";
    private static final String ACCESS_TOKEN="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4ZWY3NzZhMzg3ZGFmZTY0ZTJhZWM0ZmU1MjAzYzc4NyIsInN1YiI6IjU5N2I4YTIzOTI1MTQxMzY0ZTAxNmJjMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.6KkI84Xm-rvoZHHf_oN1Lo-phWjSBekIMq2mQPgcMM4";
    private static final String LANGUAGE="en-US";
    private static final String ADULT="true";
    private static final String PAGE="1";

    //https://api.themoviedb.org/3/movie/550?api_key=8ef776a387dafe64e2aec4fe5203c787
    public static Retrofit Create()
    {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retro=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retro;
    }

    public static Call<MoviesPojo> getmovies(String s,int counter)
    {
        Retrofit retro=Create();
        moviecall movie=retro.create(RetroFitHelper.moviecall.class);
        return movie.getCall(API_KEY,LANGUAGE,s, String.valueOf(counter),ADULT);


    }




    interface moviecall
    {
        @GET("3/search/movie")
        Call<MoviesPojo> getCall(@Query("api_key")String apikey,@Query("language")String string,@Query("query")String query,@Query("page")String one,@Query("include_adult")String bool);

    }

}
