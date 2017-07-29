package com.batchmates.android.test6.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.batchmates.android.test6.R;
import com.batchmates.android.test6.injection.DaggerMainActivityComponent;
import com.batchmates.android.test6.model.MovieListPojo;
import com.batchmates.android.test6.model.MoviesPojo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View{


    private List<MovieListPojo> recyclerList=new ArrayList<>();
    @Inject MainActivityPresenter presenter;
    private LinearLayoutManager layoutManager;
    private DefaultItemAnimator itemAnimator=new DefaultItemAnimator();
    private RecyclerAdapter recyclerAdapter;

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;


    String toParse="{\"menu\": {\"header\": \"menu\", \"items\": [{\"id\": 27}, {\"id\": 0, \"label\": \"Label 0\"}, null, {\"id\": 93}, {\"id\": 85}, {\"id\": 54}, null, {\"id\": 46, \"label\": \"Label 46\"}]}}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);
        setUpDagger();
        presenter.addView(this);
        presenter.parseJson(toParse);
    }


    @BindView(R.id.etTypeOfMovie)
    EditText movieType;

    @BindView(R.id.rvRecycler)
    RecyclerView recyclerView;

    private void setUpDagger() {
        DaggerMainActivityComponent.create().inject(this);
    }


    @Override
    public void error() {

    }

    public void lookForMovie(View view) {
        presenter.callMovies(movieType.getText().toString());
        recyclerList.clear();
    }

    @Override
    public void RecieveList(List<MovieListPojo> movieListPojoList) {
        recyclerList=movieListPojoList;
        recyclerAdapter=new RecyclerAdapter(recyclerList,movieType.getText().toString());
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);
        recyclerView.setAdapter(recyclerAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if(dy > 0) //check for scroll down
                {
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                    if (loading)
                    {
                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount-4)
                        {
//                            loading = false;
                            Log.v("...", "Last Item Wow !");
                            presenter.nextPAge(movieType.getText().toString());
                            //Do pagination.. i.e. fetch new data
                        }
                    }
                }

//                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    public void updateList(List<MovieListPojo> movieListPojoList) {
        recyclerList=movieListPojoList;
        recyclerAdapter.notifyDataSetChanged();
    }
}
