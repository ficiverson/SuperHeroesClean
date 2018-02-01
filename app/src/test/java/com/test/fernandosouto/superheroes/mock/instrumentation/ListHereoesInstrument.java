package com.test.fernandosouto.superheroes.mock.instrumentation;

import android.support.annotation.NonNull;

import com.test.fernandosouto.superheroes.domain.data.Callback;
import com.test.fernandosouto.superheroes.domain.data.Result;
import com.test.fernandosouto.superheroes.domain.datasource.MarvelHereoRemoteDataSource;
import com.test.fernandosouto.superheroes.domain.executor.InteractorExecutionCallback;
import com.test.fernandosouto.superheroes.domain.interactor.MarvelApiInteractor;
import com.test.fernandosouto.superheroes.domain.repository.MarvelHeroeRepository;
import com.test.fernandosouto.superheroes.mock.CallbackFlag;
import com.test.fernandosouto.superheroes.model.MarvelHero;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by fernando souto on 31/01/2018.
 */

public class ListHereoesInstrument {

    public static Callback<List<MarvelHero>> givenASuccessMarvelApiCallback(final CallbackFlag flag) {
        return new Callback<List<MarvelHero>>() {
            @Override
            public void onFinish(@NonNull Result<List<MarvelHero>> result) {
                flag.flagExecuted();
                assertThat(result.status().isOk()).isTrue();
                assertThat(result.status().isOk()).isTrue();
                assertThat(result.data().size()).isEqualTo(6);
            }
        };
    }

    public static MarvelApiInteractor givenAMarvelApiInteractor() {
        return new MarvelApiInteractor(new MarvelHeroeRepository(new MarvelHereoRemoteDataSource()));
    }

    public static InteractorExecutionCallback givenAInteractorExecutionCallback(final CallbackFlag flag) {
        return new InteractorExecutionCallback() {
            @Override
            public void onPostExecute() {
                assertThat(flag.isFlagged()).isTrue();
            }
        };
    }
}
