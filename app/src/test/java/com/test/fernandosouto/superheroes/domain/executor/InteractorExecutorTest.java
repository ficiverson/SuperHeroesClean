package com.test.fernandosouto.superheroes.domain.executor;


import com.test.fernandosouto.superheroes.domain.data.Callback;
import com.test.fernandosouto.superheroes.domain.interactor.MarvelApiInteractor;
import com.test.fernandosouto.superheroes.mock.CallbackFlag;
import com.test.fernandosouto.superheroes.mock.MarvelRoboelectricTest;
import com.test.fernandosouto.superheroes.mock.MockServer;
import com.test.fernandosouto.superheroes.model.MarvelHero;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.test.fernandosouto.superheroes.mock.fixtures.ServerFixtures.MARVEL_API_RESPONSE;
import static com.test.fernandosouto.superheroes.mock.fixtures.ServerFixtures.enqueueServerError;
import static com.test.fernandosouto.superheroes.mock.fixtures.ServerFixtures.enqueueServerFile;
import static com.test.fernandosouto.superheroes.mock.instrumentation.ListHereoesInstrument.givenAInteractorExecutionCallback;
import static com.test.fernandosouto.superheroes.mock.instrumentation.ListHereoesInstrument.givenASuccessMarvelApiCallback;
import static com.test.fernandosouto.superheroes.mock.instrumentation.ListHereoesInstrument.givenAMarvelApiInteractor;

/**
 * Created by fernando souto on 31/01/2018.
 */

public class InteractorExecutorTest extends MarvelRoboelectricTest {

    private CallbackFlag mCallbackFlag;
    private MockServer mMockServer;
    private String mockUrl;

    @Override
    public void onStart() throws IOException {
        mMockServer = MockServer.create();
        mockUrl = mMockServer.start();
        mCallbackFlag = CallbackFlag.newCallbackFlag();
    }

    @Override
    public void onDestroy() throws IOException {
        mMockServer.shutdown();
        mockUrl = null;
    }

    @Test
    public void thatCallbackIsFlaggedAfterInteractorExecution() throws Exception {
        enqueueServerFile(mMockServer, MARVEL_API_RESPONSE);

        Callback<List<MarvelHero>> callback = givenASuccessMarvelApiCallback(mCallbackFlag);
        MarvelApiInteractor marvelApiInteractor = givenAMarvelApiInteractor();
        InteractorExecutionCallback interactorExecutionCallback = givenAInteractorExecutionCallback(mCallbackFlag);

        InteractorExecutor<List<MarvelHero>> interactorExecutor = new InteractorExecutor<>(marvelApiInteractor, interactorExecutionCallback);
        interactorExecutor.execute(callback);
    }

    @Test
    public void thatCanGetAllMarvelHeroes() throws Exception {
        enqueueServerError(mMockServer, 404);

        Callback<List<MarvelHero>> callback = givenASuccessMarvelApiCallback(mCallbackFlag);
        MarvelApiInteractor marvelApiInteractor = givenAMarvelApiInteractor();
        InteractorExecutionCallback interactorExecutionCallback = givenAInteractorExecutionCallback(mCallbackFlag);

        InteractorExecutor<List<MarvelHero>> interactorExecutor = new InteractorExecutor<>(marvelApiInteractor, interactorExecutionCallback);
        interactorExecutor.execute(callback);
    }
}
