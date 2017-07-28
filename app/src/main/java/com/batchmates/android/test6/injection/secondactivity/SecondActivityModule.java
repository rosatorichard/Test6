package com.batchmates.android.test6.injection.secondactivity;

import com.batchmates.android.test6.view.secondactivity.SecondActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android on 7/28/2017.
 */
@Module
public class SecondActivityModule {
    @Provides
    public SecondActivityPresenter secondActivityPresenter()
    {
        return new SecondActivityPresenter();
    }
}
