package com.test.fernandosouto.superheroes.domain.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by fernando souto on 30/01/2018.
 */

public class Result<P> {

    private final Status mStatus;
    private final P mData;

    public Result(@NonNull Result<P> wrappingResult) {
        this(wrappingResult.status(), wrappingResult.data());
    }


    public Result(@NonNull Status status, @Nullable P data) {
        mStatus = status;
        mData = data;
    }


    @Nullable
    public P data() {
        return mData;
    }


    @NonNull
    public Status status() {
        return mStatus;
    }
}
