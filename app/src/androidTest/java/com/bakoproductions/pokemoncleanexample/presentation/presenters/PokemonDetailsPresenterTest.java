/*
 * Copyright 2017 Michael Bakogiannis, Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bakoproductions.pokemoncleanexample.presentation.presenters;

import com.bakoproductions.pokemoncleanexample.R;
import com.bakoproductions.pokemoncleanexample.domain.interactors.BaseUseCase;
import com.bakoproductions.pokemoncleanexample.domain.interactors.GetPokemonDetailsUseCase;
import com.bakoproductions.pokemoncleanexample.domain.models.Link;
import com.bakoproductions.pokemoncleanexample.domain.models.Pokemon;
import com.bakoproductions.pokemoncleanexample.domain.models.PokemonAbility;
import com.bakoproductions.pokemoncleanexample.domain.models.PokemonDetails;
import com.bakoproductions.pokemoncleanexample.domain.models.PokemonStat;
import com.bakoproductions.pokemoncleanexample.domain.models.errors.Error;
import com.bakoproductions.pokemoncleanexample.domain.models.errors.PokemonDetailsError;
import com.bakoproductions.pokemoncleanexample.presentation.screens.PokemonDetailsScreen;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Created by Michael on 22/04/2017.
 */
public class PokemonDetailsPresenterTest {
    @Mock
    private PokemonDetailsScreen screen;

    private PokemonDetailsPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = spy(new PokemonDetailsPresenter(screen));
    }

    @Test
    public void testInitialize() {
        Pokemon pokemon = getTestPokemon();
        doReturn(pokemon.getNameFormatted() + " details")
                .when(presenter)
                .getString(R.string.pokemon_details, pokemon.getNameFormatted());
        doNothing().when(presenter).fillPokemon(pokemon);
        doNothing().when(presenter).getDetails(pokemon);

        presenter.initialize(pokemon);
        verify(screen).initializeUI();
        verify(screen).setToolbarTitle(
                presenter.getString(R.string.pokemon_details, pokemon.getNameFormatted()));
        verify(presenter).register();

        verify(presenter).fillPokemon(pokemon);
        verify(presenter).getDetails(pokemon);
    }

    @Test
    public void testDestroy() {
        doNothing().when(presenter).unregister();
        presenter.destroy();

        verify(presenter).unregister();
    }

    @Test
    public void testOnWeightInteracted() {
        String legend = "The weight of this Pokémon";
        doReturn(legend).when(presenter).getString(R.string.leg_weight);

        presenter.onWeightInteracted();
        verify(screen).showLegend(legend);
    }

    @Test
    public void testOnBaseXPInteracted() {
        String legend = "The base experience gained for defeating this Pokémon";
        doReturn(legend).when(presenter).getString(R.string.leg_base_xp);

        presenter.onBaseXPInteracted();
        verify(screen).showLegend(legend);
    }

    @Test
    public void testOnTypesInteracted() {
        String legend = "Types are properties for Pokémon and their moves.";
        doReturn(legend).when(presenter).getString(R.string.leg_types);

        presenter.onTypesInteracted();
        verify(screen).showLegend(legend);
    }

    @Test
    public void testOnAbilitiesInteracted() {
        String legend = "A list of abilities this Pokémon could potentially have";
        doReturn(legend).when(presenter).getString(R.string.leg_abilities);

        presenter.onAbilitiesInteracted();
        verify(screen).showLegend(legend);
    }

    @Test
    public void testOnStatsInteracted() {
        String legend = "A list of abilities this Pokémon could potentially have";
        doReturn(legend).when(presenter).getString(R.string.leg_stats);

        presenter.onStatsInteracted();
        verify(screen).showLegend(legend);
    }

    @Test
    public void testGetDetails() {
        Pokemon pokemon = getTestPokemon();

        doNothing().when(presenter).executeUseCase(Matchers.<BaseUseCase>any());
        presenter.getDetails(pokemon);

        verify(screen).showLoading();
        verify(presenter).executeUseCase(Matchers.isA(GetPokemonDetailsUseCase.class));
    }

    @Test
    public void testOnPokemonDetailsReceived() {
        PokemonDetails pokemonDetails = getTestPokemonDetails();
        doNothing().when(presenter).fillDetails(pokemonDetails);
        presenter.onPokemonDetailsReceived(pokemonDetails);

        verify(screen).hideLoading();
        verify(screen).showDetailsPanel();

        verify(presenter).fillDetails(pokemonDetails);
    }

    @Test
    public void testOnPokemonDetailsErrorNetwork() {
        PokemonDetailsError error = new PokemonDetailsError();
        error.setStatus(Error.NETWORK_ERROR_CODE);

        presenter.onPokemonDetailsError(error);
        verify(screen).hideLoading();
        verify(screen).showNoInternetError();
        verify(screen, never()).showError(error);
    }

    @Test
    public void testOnPokemonDetailsErrorOther() {
        PokemonDetailsError error = new PokemonDetailsError();
        error.setMessage("Other error");

        presenter.onPokemonDetailsError(error);
        verify(screen).hideLoading();
        verify(screen, never()).showNoInternetError();
        verify(screen).showError(error);
    }

    private Pokemon getTestPokemon() {
        Pokemon pokemon = new Pokemon();
        pokemon.setName("bulbasaur");
        Link link = new Link();
        link.setLinkUrl("http://pokeapi.co/api/v2/pokemon/1/");
        pokemon.setUrl(link);
        return pokemon;
    }

    private PokemonDetails getTestPokemonDetails() {
        PokemonDetails details = new PokemonDetails();
        details.setId("1");
        details.setWeight(69);
        details.setBaseXP(64);

        ArrayList<String> types = new ArrayList<>();
        types.add("poison");
        types.add("grass");
        details.setTypes(types);

        ArrayList<PokemonAbility> abilities = new ArrayList<>();
        PokemonAbility chlorophyll = new PokemonAbility();
        chlorophyll.setName("chlorophyll");
        chlorophyll.setHidden(true);
        abilities.add(chlorophyll);

        PokemonAbility overgrow = new PokemonAbility();
        overgrow.setName("overgrow");
        overgrow.setHidden(false);
        abilities.add(overgrow);

        ArrayList<PokemonStat> stats = new ArrayList<>();

        PokemonStat speed = new PokemonStat();
        speed.setName("speed");
        speed.setBase(45);
        speed.setEffort(0);
        stats.add(speed);

        PokemonStat sDefence = new PokemonStat();
        sDefence.setName("special-defence");
        sDefence.setBase(65);
        sDefence.setEffort(0);
        stats.add(sDefence);

        PokemonStat sAttack = new PokemonStat();
        sAttack.setName("special-attack");
        sAttack.setBase(65);
        sAttack.setEffort(1);
        stats.add(sAttack);

        PokemonStat def = new PokemonStat();
        def.setName("defence");
        def.setBase(49);
        def.setEffort(0);
        stats.add(def);

        PokemonStat at = new PokemonStat();
        at.setName("attack");
        at.setBase(49);
        at.setEffort(0);
        stats.add(at);

        PokemonStat hp = new PokemonStat();
        hp.setName("hp");
        hp.setBase(45);
        hp.setEffort(0);
        stats.add(at);
        details.setStats(stats);

        return details;
    }
}