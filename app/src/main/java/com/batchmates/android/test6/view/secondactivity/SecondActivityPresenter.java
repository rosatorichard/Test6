package com.batchmates.android.test6.view.secondactivity;

import com.batchmates.android.test6.view.MainActivityContract;

/**
 * Created by Android on 7/28/2017.
 */

public class SecondActivityPresenter implements SecondActivityContract.Presenter{

    SecondActivityContract.View view;

    @Override
    public void addView(SecondActivityContract.View view) {
        this.view=view;
    }

    @Override
    public void removeView() {

    }
}
