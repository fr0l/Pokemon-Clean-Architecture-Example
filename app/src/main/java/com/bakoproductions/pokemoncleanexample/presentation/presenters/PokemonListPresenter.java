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

import android.util.Log;

import com.bakoproductions.pokemoncleanexample.R;
import com.bakoproductions.pokemoncleanexample.data.repository.PokemonRepository;
import com.bakoproductions.pokemoncleanexample.data.repository.sharedPreferences.Prefs;
import com.bakoproductions.pokemoncleanexample.data.repositoryCached.PokemonDetailsDataStatic;
import com.bakoproductions.pokemoncleanexample.domain.interactors.GetPokemonListUseCase;
import com.bakoproductions.pokemoncleanexample.domain.models.Link;
import com.bakoproductions.pokemoncleanexample.domain.models.Pokemon;
import com.bakoproductions.pokemoncleanexample.domain.models.PokemonList;
import com.bakoproductions.pokemoncleanexample.domain.models.errors.PokemonListError;
import com.bakoproductions.pokemoncleanexample.presentation.screens.PokemonListScreen;
import com.squareup.otto.Subscribe;

/**
 * Created by Michael on 14/4/2017.
 */

public class PokemonListPresenter extends BasePresenter {
    private PokemonListScreen screen;

    private int totalPokemon;
    private int lastPageSize;
    private Link nextPageLink;
    private boolean loadingNewPage;

    /**
     * We usually keep the constructor simple.
     * @param screen The interface that helps this presenter
     *               present a ui to any given underlying android component
     */
    public PokemonListPresenter(PokemonListScreen screen) {
        this.screen = screen;
    }

    /**
     * All these stuff need to be outside of the constructor,
     * because the actual presenter instance is not created inside the
     * it's body
     */
    public void initialize() {
        // This class starts listening for events future Use Case invocations
        register();

        screen.initializeUI();
        screen.setToolbarTitle(screen.getContext().getString(R.string.pokemon_list_toolbar_title));

        // We need a shared preference here, check the method getSharedPreferencesBoolean(...)
        // @ BaseUsePresenter
        if (!getSharedPreferencesBoolean(Prefs.WELCOME_SHOWN)) {
            showWelcomeUI();
        } else {
            loadPokemonData();
        }
    }

    public void destroy() {
        // Unregister subscribers. If we forget this then every @Subscribe method
        // will be called more than one times per Use Case
        unregister();

        // Removing cashed data that we don't need anymore
        PokemonDetailsDataStatic.get().removeAll();
    }

    /**
     * Here is where we decide what to do when the user clickes
     * the welcome panel
     */
    public void onWelcomeInteracted() {
        screen.hideWelcomePanel();
        loadPokemonData();
    }

    /**
     * Here is where we decide if we want to load more data when the list is scrolling
     * @param firstItem The first visible item of the RecyclerView
     */
    public void onListScrolled(int firstItem) {
        if (firstItem > (totalPokemon - lastPageSize / 2.0f) && !loadingNewPage && nextPageLink.getLinkUrl() != null) {
            Log.d("Bakos", "First: " + firstItem + " > " + (totalPokemon - lastPageSize / 2.0f) + " total " + totalPokemon);
            loadMorePokemonData();
        }
    }

    /**
     * Is called when the user clicks on a Pokemon
     * @param pokemon The pokemon that the user clicked from the list
     */
    public void onPokemonInteracted(Pokemon pokemon) {
        getNavigator().navigateToDetails(screen.getContext(), pokemon);
    }

    void showWelcomeUI() {
        storeSharedPreferences(Prefs.WELCOME_SHOWN, true);
        screen.showWelcomePanel();
    }

    void loadPokemonData() {
        // We need to show the loading progress before request the data
        screen.showLoading();

        // Requesting the data, while providing how many pokemon we want to fetch first (20)
        executeUseCase(new GetPokemonListUseCase(20, new PokemonRepository()));
    }

    void loadMorePokemonData() {
        // We need to show a loading progress on the bottom of the list
        screen.showListLoading();

        loadingNewPage = true;

        // Requesting the next page of data
        executeUseCase(new GetPokemonListUseCase(nextPageLink, new PokemonRepository()));
    }

    @Subscribe
    public void onPokemonListReceived(PokemonList pokemonList) {
        /*
          Both GetPokemonListUseCase invocations end here, either we requested the first page
          or every next page
         */
        if (totalPokemon == 0) {
            // First time hide the screen loading
            screen.hideLoading();
        } else {
            // Every next time we remove the bottom list loading progress
            loadingNewPage = false;
            screen.hideListLoading();
        }

        // Adding the fetched data to the RecyclerView
        screen.addToPokemonList(pokemonList.getPokemonList());

        // We need these variables for infinite scrolling
        lastPageSize = pokemonList.getPageSize();
        totalPokemon += lastPageSize;
        nextPageLink = pokemonList.getNextLink();
    }

    @Subscribe
    public void onPokemonListError(PokemonListError error) {
        // Both GetPokemonListUseCase invocations end here

        // Hiding the appropriate loading progress
        if (totalPokemon == 0) {
            screen.hideLoading();
        } else {
            loadingNewPage = false;
            screen.hideListLoading();
        }

        // Showing the appropriate dialog
        if (error.isNetworkError()) {
            screen.showNoInternetError();
        } else {
            screen.showError(error);
        }
    }
}
