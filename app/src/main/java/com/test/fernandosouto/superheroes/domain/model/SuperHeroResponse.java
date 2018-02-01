package com.test.fernandosouto.superheroes.domain.model;

import com.test.fernandosouto.superheroes.model.MarvelHero;

import java.util.List;

/**
 * Created by fernando souto on 30/01/2018.
 */

public class SuperHeroResponse {

    List<MarvelHero> superheroes;

    public List<MarvelHero> getSuperheroes() {
        return superheroes;
    }

    public void setSuperheroes(List<MarvelHero> superhereoes) {
        this.superheroes = superhereoes;
    }


}
