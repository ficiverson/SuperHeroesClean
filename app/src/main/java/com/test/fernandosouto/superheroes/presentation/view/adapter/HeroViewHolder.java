package com.test.fernandosouto.superheroes.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.fernandosouto.superheroes.R;
import com.test.fernandosouto.superheroes.model.MarvelHero;
import com.test.fernandosouto.superheroes.presentation.presenter.DetailHeroPresenter;
import com.test.fernandosouto.superheroes.presentation.presenter.ListHeroesPresenter;

/**
 * Created by fernando souto on 30/01/2018.
 */

public class HeroViewHolder extends RecyclerView.ViewHolder {

    private ListHeroesPresenter presenter;
    private View itemView;
    private ImageView heroImage;
    private TextView heroName;

    public HeroViewHolder(View itemView, ListHeroesPresenter presenter) {
        super(itemView);

        this.presenter = presenter;
        this.itemView = itemView;

        heroImage = itemView.findViewById(R.id.im_hero_image);
        heroName = itemView.findViewById(R.id.tv_hero_name);
    }

    public void render(MarvelHero marvelHero) {
        hookListeners(marvelHero);
        if (marvelHero.getPhoto() != null) {
            Picasso.with(getContext()).load(marvelHero.getPhoto()).into(heroImage);
        }
        if (marvelHero.getName() != null) {
            heroName.setText(marvelHero.getName());
        }
    }

    private void hookListeners(final MarvelHero marvelHero) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onMarvelHeroClick(getContext(), marvelHero);
            }
        });
    }

    private Context getContext() {
        return itemView.getContext();
    }
}