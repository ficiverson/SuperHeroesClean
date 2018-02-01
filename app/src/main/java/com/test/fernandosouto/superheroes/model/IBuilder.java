package com.test.fernandosouto.superheroes.model;

import android.support.annotation.NonNull;

/**
 * Created by fernando souto on 31/01/2018.
 */
public interface IBuilder<T> {
    @NonNull
    T build();
}