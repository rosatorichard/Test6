package com.batchmates.android.test6.injection;

import com.batchmates.android.test6.view.MainActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android on 7/28/2017.
 */
@Module
public class MainActivityModule {

    @Provides
    public MainActivityPresenter mainActivityPresenter()
    {
        return new MainActivityPresenter();
    }
}
