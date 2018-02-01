package com.test.fernandosouto.superheroes.domain.executor;

import android.support.annotation.Nullable;

import com.test.fernandosouto.superheroes.domain.data.Callback;

/**
 * Created by fernando souto on 30/01/2018.
 */

public interface Executor<T> {
    Cancelable execute(@Nullable Callback<T> callback);
}
