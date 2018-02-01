package com.test.fernandosouto.superheroes.presentation.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.fernandosouto.superheroes.R;
import com.test.fernandosouto.superheroes.model.MarvelHero;
import com.test.fernandosouto.superheroes.presentation.presenter.ListHeroesPresenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by fernando souto on 30/01/2018.
 */

public class HeroesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MarvelHero> superHeroes;
    private ListHeroesPresenter presenter;

    public HeroesAdapter(ListHeroesPresenter presenter) {
        this.presenter = presenter;
        this.superHeroes = new ArrayList<>();
    }

    public void addAll(List<MarvelHero> heroes) {
        superHeroes.addAll(heroes);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.marvel_hero_row, parent, false);
        return new HeroViewHolder(view, presenter);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HeroViewHolder superHeroViewHolder = (HeroViewHolder) holder;
        MarvelHero superHero = superHeroes.get(position);
        superHeroViewHolder.render(superHero);
    }

    @Override
    public int getItemCount() {
        return superHeroes.size();
    }
}