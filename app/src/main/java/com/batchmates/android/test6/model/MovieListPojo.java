package com.batchmates.android.test6.model;

/**
 * Created by Android on 7/28/2017.
 */

public class MovieListPojo {

    String title;
    String imageurl;

    public MovieListPojo(String title, String imageurl) {
        this.title = title;
        this.imageurl = imageurl;
    }


    public String getTitle() {
        return title;
    }

    public String getImageurl() {
        return imageurl;
    }
}
