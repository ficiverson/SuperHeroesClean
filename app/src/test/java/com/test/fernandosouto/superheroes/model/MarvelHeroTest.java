package com.test.fernandosouto.superheroes.model;

import com.test.fernandosouto.superheroes.mock.MarvelRoboelectricTest;
import com.test.fernandosouto.superheroes.mock.instrumentation.TestUtils;

import org.junit.Test;


import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by fernando souto on 31/01/2018.
 */
public class MarvelHeroTest extends MarvelRoboelectricTest {


    @Test
    public void thatCanCreateAMarvelHero() throws Exception {
        MarvelHero marvelHero = new MarvelHero.Builder("skywalker")
                .withAbilities("The force")
                .withGroups("Empire, jedi")
                .withHeiht("200")
                .withPhoto("http://image.jpeg")
                .withPower("Max power")
                .withRealName("Darth Vader")
                .build();

        assertThat(marvelHero).isNotNull();
        assertThat(marvelHero.getHeight()).isEqualTo("200");
        assertThat(marvelHero.getAbilities()).isEqualTo("The force");
        assertThat(marvelHero.getGroups()).isEqualTo("Empire, jedi");
        assertThat(marvelHero.getName()).isEqualTo("skywalker");
        assertThat(marvelHero.getRealName()).isEqualTo("Darth Vader");
        assertThat(marvelHero.getPhoto()).isEqualTo("http://image.jpeg");
        assertThat(marvelHero.getPower()).isEqualTo("Max power");
    }

    @Test
    public void thatCanParcelMarvelHero() throws Exception {
        MarvelHero marvelHero = new MarvelHero.Builder("skywalker")
                .withAbilities("The force")
                .withGroups("Empire, jedi")
                .withHeiht("200")
                .withPhoto("http://image.jpeg")
                .withPower("Max power")
                .withRealName("Darth Vader")
                .build();

        MarvelHero marvelHeroFromParcel = TestUtils.testParcel(marvelHero, MarvelHero.CREATOR);

        assertThat(marvelHeroFromParcel).isNotNull();
        assertThat(marvelHero.getHeight()).isEqualTo(marvelHeroFromParcel.getHeight());
        assertThat(marvelHero.getAbilities()).isEqualTo(marvelHeroFromParcel.getAbilities());
        assertThat(marvelHero.getGroups()).isEqualTo(marvelHeroFromParcel.getGroups());
        assertThat(marvelHero.getName()).isEqualTo(marvelHeroFromParcel.getName());
        assertThat(marvelHero.getRealName()).isEqualTo(marvelHeroFromParcel.getRealName());
        assertThat(marvelHero.getPhoto()).isEqualTo(marvelHeroFromParcel.getPhoto());
        assertThat(marvelHero.getPower()).isEqualTo(marvelHeroFromParcel.getPower());
    }
}
