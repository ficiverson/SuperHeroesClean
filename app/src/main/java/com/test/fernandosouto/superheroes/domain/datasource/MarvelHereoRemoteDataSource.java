package com.test.fernandosouto.superheroes.domain.datasource;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.test.fernandosouto.superheroes.domain.model.SuperHeroResponse;
import com.test.fernandosouto.superheroes.model.MarvelHero;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by fernando souto on 30/01/2018.
 */

public class MarvelHereoRemoteDataSource {

    private String API_URL = "https://api.myjson.com/bins/bvyob";

    public MarvelHereoRemoteDataSource() {

    }

    public List<MarvelHero> getAllHereoes() throws Exception {

        List<MarvelHero> marvelHeroes = new ArrayList<>();
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        try {
            Request request = new Request.Builder()
                    .url(API_URL)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                //parse response
                Gson gson = new Gson();
                SuperHeroResponse superHeroResponse = gson.fromJson(response.body().string(), SuperHeroResponse.class);
                marvelHeroes = superHeroResponse.getSuperheroes();
            }

        } catch (JsonSyntaxException parseException) {
            throw parseException;
        }

        return marvelHeroes;
    }
}
