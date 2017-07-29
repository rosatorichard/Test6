package com.batchmates.android.test6.view;

import com.batchmates.android.test6.BasePresenter;
import com.batchmates.android.test6.BaseView;
import com.batchmates.android.test6.model.MovieListPojo;

import java.util.List;

/**
 * Created by Android on 7/28/2017.
 */

public interface MainActivityContract{

    interface View extends BaseView
    {
        void RecieveList(List<MovieListPojo> movieListPojoList);
        void updateList(List<MovieListPojo> movieListPojoList);
    }

    interface Presenter extends BasePresenter<View>
    {
        void parseJson(String s);

        void callMovies(String s);
        void nextPAge(String s);
    }
}
