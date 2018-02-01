package com.test.fernandosouto.superheroes.presentation.presenter;

import com.test.fernandosouto.superheroes.domain.data.Result;
import com.test.fernandosouto.superheroes.mock.CallbackFlag;
import com.test.fernandosouto.superheroes.mock.MarvelRoboelectricTest;
import com.test.fernandosouto.superheroes.mock.MockServer;
import com.test.fernandosouto.superheroes.model.MarvelHero;
import com.test.fernandosouto.superheroes.presentation.view.ViewTranslator;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.test.fernandosouto.superheroes.mock.fixtures.ServerFixtures.MARVEL_API_RESPONSE;
import static com.test.fernandosouto.superheroes.mock.fixtures.ServerFixtures.enqueueServerError;
import static com.test.fernandosouto.superheroes.mock.fixtures.ServerFixtures.enqueueServerFile;
import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by fernando souto on 31/01/2018.
 */

public class ListHereoesPresenterTest extends MarvelRoboelectricTest {

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
    public void thatCanGetAllMarvelHeroes() throws Exception {
        enqueueServerFile(mMockServer, MARVEL_API_RESPONSE);

        ListHeroesPresenter presenter =new ListHeroesPresenter(new ViewTranslator<List<MarvelHero>>() {
            @Override
            public void showProgress() {

            }

            @Override
            public void hideProgress() {

            }

            @Override
            public void loadData(Result<List<MarvelHero>> data) {
                assertThat(data.status().isOk()).isTrue();
                assertThat(data.status().isOk()).isTrue();
                assertThat(data.data().size()).isEqualTo(6);
            }

            @Override
            public void showError(String message) {

            }
        });
    }

    @Test
    public void thatCanHandleAErrorFromApi() throws Exception {
        enqueueServerError(mMockServer, 404);

        ListHeroesPresenter presenter =new ListHeroesPresenter(new ViewTranslator<List<MarvelHero>>() {
            @Override
            public void showProgress() {

            }

            @Override
            public void hideProgress() {

            }

            @Override
            public void loadData(Result<List<MarvelHero>> data) {

            }

            @Override
            public void showError(String message) {
                assertThat(message).isEqualTo("Error loading :(");
            }
        });
    }
}