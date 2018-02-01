package com.test.fernandosouto.superheroes.domain.repository;

import android.support.annotation.NonNull;

import com.test.fernandosouto.superheroes.domain.data.Result;
import com.test.fernandosouto.superheroes.domain.data.Status;
import com.test.fernandosouto.superheroes.domain.datasource.MarvelHereoRemoteDataSource;
import com.test.fernandosouto.superheroes.model.MarvelHero;

import java.io.IOException;
import java.util.List;

/**
 * Created by fernando souto on 30/01/2018.
 */

public class MarvelHeroeRepository {

    @NonNull
    MarvelHereoRemoteDataSource marvelHereoRemoteDataSource;

    public MarvelHeroeRepository(@NonNull MarvelHereoRemoteDataSource remoteDataSource) {
        marvelHereoRemoteDataSource = remoteDataSource;
    }

    @NonNull
    public Result<List<MarvelHero>> getAllHeroes() throws Exception {
        Status.Builder status = Status.builder();
        List<MarvelHero> response = null;
        try {
            response = marvelHereoRemoteDataSource.getAllHereoes();
        } catch (IOException exception) {
            status.error(exception);
        }
        return new Result<>(status.build(), response);
    }
}
