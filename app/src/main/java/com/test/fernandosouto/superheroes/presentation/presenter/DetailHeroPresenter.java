package com.test.fernandosouto.superheroes.presentation.presenter;

import android.widget.ProgressBar;

import com.test.fernandosouto.superheroes.model.MarvelHero;
import com.test.fernandosouto.superheroes.presentation.view.ViewTranslator;

import java.util.List;

/**
 * Created by fernando souto on 30/01/2018.
 */

public class DetailHeroPresenter extends Presenter<ViewTranslator> {

    private ViewTranslator<MarvelHero> mView;

    public DetailHeroPresenter(ViewTranslator view) {
        mView = view;
        mView.showProgress();

        initialize();
    }

    public void initialize() {
        mView.hideProgress();
    }
}
