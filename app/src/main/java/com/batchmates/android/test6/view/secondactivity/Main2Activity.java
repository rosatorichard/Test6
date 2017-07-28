package com.batchmates.android.test6.view.secondactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.batchmates.android.test6.R;
import com.batchmates.android.test6.injection.secondactivity.DaggerSecondActivityComponent;
import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {

    @Inject SecondActivityPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ButterKnife.bind(this);
        title.setText(getIntent().getStringExtra("TITLE"));
        Glide.with(this).load("https://image.tmdb.org/t/p/w185_and_h278_bestv2/"+getIntent().getStringExtra("IMAGE")).into(picture);

        setUpDagger();
    }

    private void setUpDagger() {
        DaggerSecondActivityComponent.create().inject(this);
    }

    @BindView(R.id.tvTitle)
    TextView title;


    @BindView(R.id.ivPicture)
    ImageView picture;


}
