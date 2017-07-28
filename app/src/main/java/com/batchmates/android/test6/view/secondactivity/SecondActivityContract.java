package com.batchmates.android.test6.view.secondactivity;

import com.batchmates.android.test6.BasePresenter;
import com.batchmates.android.test6.BaseView;

/**
 * Created by Android on 7/28/2017.
 */

public interface SecondActivityContract {

    interface View extends BaseView
    {

    }

    interface Presenter extends BasePresenter<View>
    {

    }
}
