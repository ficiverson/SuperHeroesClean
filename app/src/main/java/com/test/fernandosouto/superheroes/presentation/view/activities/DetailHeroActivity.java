package com.test.fernandosouto.superheroes.presentation.view.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.google.android.flexbox.FlexboxLayout;
import com.squareup.picasso.Picasso;
import com.test.fernandosouto.superheroes.R;
import com.test.fernandosouto.superheroes.domain.data.Result;
import com.test.fernandosouto.superheroes.model.MarvelHero;
import com.test.fernandosouto.superheroes.presentation.presenter.DetailHeroPresenter;
import com.test.fernandosouto.superheroes.presentation.view.ViewTranslator;

import java.util.Arrays;

import fisk.chipcloud.ChipCloud;
import fisk.chipcloud.ChipCloudConfig;

/**
 * Created by fernando souto on 30/01/2018.
 */

public class DetailHeroActivity extends AppCompatActivity implements ViewTranslator<MarvelHero> {

    public static final String BUNDLE_MARVEL_HERO = "marvel_hero";

    private DetailHeroPresenter presenter;
    private Toolbar toolbar;
    private MarvelHero mMarvelHero;

    public static void start(Context context, MarvelHero marvelHero) {
        Intent intent = new Intent(context, DetailHeroActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_MARVEL_HERO, marvelHero);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_heroes);

        if (getIntent().getExtras() != null) {
            //Comes from the list and it has an article
            if (getIntent().getExtras().containsKey(BUNDLE_MARVEL_HERO)) {
                mMarvelHero = getIntent().getExtras().getParcelable(BUNDLE_MARVEL_HERO);
            }

        } else {
            Toast.makeText(this, "We cannot show the Marvel HERO :(", Toast.LENGTH_LONG).show();
            finish();
        }

        toolbar = findViewById(R.id.tb_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        toolbar.setTitle(mMarvelHero.getRealName());
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(mMarvelHero.getRealName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        presenter = new DetailHeroPresenter(this);

        initializeView();
    }

    private void initializeView() {
        ImageView heroImage = findViewById(R.id.im_home_hero);
        Picasso.with(this).load(mMarvelHero.getPhoto()).into(heroImage);

        TextView abilities = findViewById(R.id.tv_abilities_info);
        abilities.setText(mMarvelHero.getAbilities());

        TextView power = findViewById(R.id.tv_power_info);
        power.setText(mMarvelHero.getPower());


        TextView height = findViewById(R.id.tv_height_info);
        height.setText(mMarvelHero.getHeight());


        String[] groups = mMarvelHero.getGroups().split(",", -1);
        FlexboxLayout flexboxDrawable = (FlexboxLayout) findViewById(R.id.fb_groups);

        ChipCloudConfig drawableConfig = new ChipCloudConfig()
                .selectMode(ChipCloud.SelectMode.none)
                .uncheckedChipColor(Color.parseColor("#ddaa00"))
                .uncheckedTextColor(Color.parseColor("#ffffff"));

        ChipCloud drawableChipCloud = new ChipCloud(this, flexboxDrawable, drawableConfig);
        drawableChipCloud.addChips(Arrays.asList(groups));


    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


    @Override
    public void showProgress() {
        //nothing to do
    }

    @Override
    public void hideProgress() {
        //nothing to do
    }

    @Override
    public void loadData(Result<MarvelHero> data) {
        //nothing to do
    }

    @Override
    public void showError(String message) {

    }
}
