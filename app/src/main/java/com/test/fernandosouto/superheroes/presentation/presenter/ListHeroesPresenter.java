package com.test.fernandosouto.superheroes.presentation.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.test.fernandosouto.superheroes.domain.data.Callback;
import com.test.fernandosouto.superheroes.domain.data.Result;
import com.test.fernandosouto.superheroes.domain.datasource.MarvelHereoRemoteDataSource;
import com.test.fernandosouto.superheroes.domain.executor.InteractorExecutor;
import com.test.fernandosouto.superheroes.domain.interactor.MarvelApiInteractor;
import com.test.fernandosouto.superheroes.domain.repository.MarvelHeroeRepository;
import com.test.fernandosouto.superheroes.model.MarvelHero;
import com.test.fernandosouto.superheroes.presentation.view.ViewTranslator;
import com.test.fernandosouto.superheroes.presentation.view.activities.DetailHeroActivity;

import java.util.List;

/**
 * Created by fernando souto on 30/01/2018.
 */

public class ListHeroesPresenter extends Presenter<ViewTranslator> {

    private ViewTranslator<List<MarvelHero>> mView;

    public ListHeroesPresenter(ViewTranslator view) {
        mView = view;
        mView.showProgress();

        initialize();
    }

    public void initialize() {
        InteractorExecutor<List<MarvelHero>> interactorExecutor = new InteractorExecutor<>(new MarvelApiInteractor(new MarvelHeroeRepository(new MarvelHereoRemoteDataSource())), null);
        interactorExecutor.execute(new Callback<List<MarvelHero>>() {
            @Override
            public void onFinish(@NonNull Result<List<MarvelHero>> result) {
                mView.hideProgress();
                if (result.status().isOk()) {
                    mView.loadData(result);
                } else {
                    mView.showError("Error loading :(");
                }
            }
        });
    }

    public void onMarvelHeroClick(Context context, MarvelHero marvelHero) {
        DetailHeroActivity.start(context, marvelHero);
    }

    public void onViewReady() {
        Log.v("presenter", "view ready");
    }
}
