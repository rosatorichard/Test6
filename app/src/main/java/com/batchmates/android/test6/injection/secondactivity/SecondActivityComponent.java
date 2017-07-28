package com.batchmates.android.test6.injection.secondactivity;

import com.batchmates.android.test6.view.secondactivity.Main2Activity;

import dagger.Component;

/**
 * Created by Android on 7/28/2017.
 */
@Component(modules = SecondActivityModule.class)
public interface SecondActivityComponent {
    void inject(Main2Activity main2Activity);
}
