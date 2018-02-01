package com.test.fernandosouto.superheroes.mock;

import com.test.fernandosouto.superheroes.BuildConfig;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by fernando souto on 31/01/2018.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,  sdk = 25)
public abstract class MarvelRoboelectricTest {

    @Before
    public void onStart() throws Exception {
        //Intended to be overridden
    }

    @After
    public void onDestroy() throws Exception {
        //Intended to be overridden.
    }

}


