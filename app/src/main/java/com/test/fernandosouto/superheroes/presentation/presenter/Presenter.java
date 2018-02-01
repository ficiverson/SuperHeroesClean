package com.test.fernandosouto.superheroes.presentation.presenter;

/**
 * Created by fernando souto on 30/01/2018.
 */

public class Presenter<T>{

    private T view;

    public void setView(T view) {
        this.view = view;
    }

    public T getView() {
        return view;
    }

    public void initialize() {

    }

}
