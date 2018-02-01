package com.test.fernandosouto.superheroes.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * Created by fernando souto on 31/01/2018.
 */

@RunWith(BlockJUnit4ClassRunner.class)
public abstract class MarvelJUnitTest {

    @Before
    public void onStart() throws Exception{
        //Intended to be overridden
    }

    @After
    public void onDestroy() throws Exception{
        //Intended to be overridden.
    }
}

