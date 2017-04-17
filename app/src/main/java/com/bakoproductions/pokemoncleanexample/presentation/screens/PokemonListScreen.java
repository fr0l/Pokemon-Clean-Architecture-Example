package com.bakoproductions.pokemoncleanexample.presentation.screens;

import com.bakoproductions.pokemoncleanexample.domain.models.Pokemon;

import java.util.ArrayList;

/**
 * Created by Michael on 14/4/2017.
 */

public interface PokemonListScreen extends BaseScreen {
    void showWelcomePanel();
    void hideWelcomePanel();

    void addToPokemonList(ArrayList<Pokemon> pokemonList);
    void showListLoading();
    void hideListLoading();
}
