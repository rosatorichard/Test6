package com.batchmates.android.test6.injection;

import com.batchmates.android.test6.view.MainActivity;

import dagger.Component;

/**
 * Created by Android on 7/28/2017.
 */
@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
