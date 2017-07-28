package com.batchmates.android.test6;

import com.batchmates.android.test6.view.MainActivityContract;

/**
 * Created by Android on 7/28/2017.
 */

public interface BasePresenter <V extends MainActivityContract.View>{

    void addView(V view);
    void removeView();
}
