package com.test.fernandosouto.superheroes.domain.interactor;

import android.support.annotation.NonNull;

import com.test.fernandosouto.superheroes.domain.data.Result;
import com.test.fernandosouto.superheroes.domain.executor.InteractorExecutor;
import com.test.fernandosouto.superheroes.domain.repository.MarvelHeroeRepository;
import com.test.fernandosouto.superheroes.model.MarvelHero;

import java.util.List;

/**
 * Created by fernando souto on 30/01/2018.
 */

public class MarvelApiInteractor implements InteractorExecutor.Interactor<List<MarvelHero>> {

    MarvelHeroeRepository marvelHeroeRepository;

    public MarvelApiInteractor(MarvelHeroeRepository repository) {
        marvelHeroeRepository = repository;
    }

    @NonNull
    @Override
    public Result<List<MarvelHero>> executeInteractor() throws Exception {
        Result<List<MarvelHero>> result = null;
        result = marvelHeroeRepository.getAllHeroes();
        return result;
    }
}
