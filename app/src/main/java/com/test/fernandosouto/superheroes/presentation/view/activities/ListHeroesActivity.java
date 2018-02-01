package com.test.fernandosouto.superheroes.presentation.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.ybq.android.spinkit.style.FoldingCube;
import com.test.fernandosouto.superheroes.R;
import com.test.fernandosouto.superheroes.domain.data.Result;
import com.test.fernandosouto.superheroes.model.MarvelHero;
import com.test.fernandosouto.superheroes.presentation.presenter.ListHeroesPresenter;
import com.test.fernandosouto.superheroes.presentation.view.ViewTranslator;
import com.test.fernandosouto.superheroes.presentation.view.adapter.HeroesAdapter;

import java.util.List;

/**
 * Created by fernando souto on 30/01/2018.
 */

public class ListHeroesActivity extends AppCompatActivity implements ViewTranslator<List<MarvelHero>> {

    private ListHeroesPresenter presenter;
    private RecyclerView mRecyclerView;
    private HeroesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView mEmptyText;
    private ProgressBar mLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_heroes);

        mRecyclerView = findViewById(R.id.rv_heroes_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mEmptyText = findViewById(R.id.tv_empty_list);
        mLoader = (ProgressBar) findViewById(R.id.pb_loader);
        mLoader.setIndeterminate(true);

        presenter = new ListHeroesPresenter(this);

        mAdapter = new HeroesAdapter(presenter);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onViewReady();

    }

    @Override
    public void showProgress() {
        FoldingCube foldingCube = new FoldingCube();
        mLoader.setIndeterminateDrawable(foldingCube);
    }

    @Override
    public void hideProgress() {
        mLoader.setVisibility(View.GONE);
    }

    @Override
    public void loadData(Result<List<MarvelHero>> data) {
        //show data
        mAdapter.addAll(data.data());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        mRecyclerView.setVisibility(View.GONE);
        mEmptyText.setVisibility(View.VISIBLE);
        mEmptyText.setText(message);
    }
}
