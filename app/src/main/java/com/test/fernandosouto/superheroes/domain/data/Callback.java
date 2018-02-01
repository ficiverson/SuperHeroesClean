package com.test.fernandosouto.superheroes.domain.data;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

/**
 * Created by fernando souto on 30/01/2018.
 */

public interface Callback<T> {
    @MainThread
    void onFinish(@NonNull Result<T> result);
}