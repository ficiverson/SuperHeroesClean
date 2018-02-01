package com.test.fernandosouto.superheroes.presentation.view;

import com.test.fernandosouto.superheroes.domain.data.Result;

/**
 * Created by fernando souto on 30/01/2018.
 */

public interface ViewTranslator<P> {

    void showProgress();

    void hideProgress();

    void loadData(Result<P> data);

    void showError(String message);
}
