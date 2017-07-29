package com.batchmates.android.test6.model;

/**
 * Created by Android on 7/28/2017.
 */

public class MovieListPojo {

    String title;
    String imageurl;
    String overview;
    double userRating;

    public MovieListPojo(String title, String imageurl,String overview,double userRating) {
        this.title = title;
        this.imageurl = imageurl;
        this.overview=overview;
        this.userRating=userRating;
    }

    public double getUserRating() {
        return userRating;
    }

    public String getOverview() {
        return overview;
    }

    public String getTitle() {
        return title;
    }

    public String getImageurl() {
        return imageurl;
    }
}
