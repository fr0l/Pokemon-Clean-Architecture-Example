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

    public PokemonListPresenter(PokemonListScreen screen) {
        this.screen = screen;
    }

    public void initialize() {
        register();

        screen.initializeUI();
        screen.setToolbarTitle(screen.getContext().getString(R.string.pokemon_list_toolbar_title));

        if (!getSharedPreferencesBoolean(Prefs.WELCOME_SHOWN)) {
            showWelcomeUI();
        } else {
            loadPokemonData();
        }
    }

    public void destroy() {
        unregister();

        PokemonDetailsDataStatic.get().removeAll();
    }

    public void onWelcomeInteracted() {
        screen.hideWelcomePanel();
        loadPokemonData();
    }

    public void onListScrolled(int firstItem) {
        if (firstItem > (totalPokemon - lastPageSize / 2.0f) && !loadingNewPage && nextPageLink.getLinkUrl() != null) {
            Log.d("Bakos", "First: " + firstItem + " > " + (totalPokemon - lastPageSize / 2.0f) + " total " + totalPokemon);
            loadMorePokemonData();
        }
    }

    public void onPokemonInteracted(Pokemon pokemon) {
        getNavigator().navigateToDetails(screen.getContext(), pokemon);
    }

    void showWelcomeUI() {
        storeSharedPreferences(Prefs.WELCOME_SHOWN, true);
        screen.showWelcomePanel();
    }

    void loadPokemonData() {
        screen.showLoading();
        executeUseCase(new GetPokemonListUseCase(20, new PokemonRepository()));
    }

    void loadMorePokemonData() {
        screen.showListLoading();

        loadingNewPage = true;
        executeUseCase(new GetPokemonListUseCase(nextPageLink, new PokemonRepository()));
    }

    @Subscribe
    public void onPokemonListReceived(PokemonList pokemonList) {
        if (totalPokemon == 0) {
            screen.hideLoading();
        } else {
            loadingNewPage = false;
            screen.hideListLoading();
        }

        screen.addToPokemonList(pokemonList.getPokemonList());

        lastPageSize = pokemonList.getPageSize();
        totalPokemon += lastPageSize;
        nextPageLink = pokemonList.getNextLink();
    }

    @Subscribe
    public void onPokemonListError(PokemonListError error) {
        if (totalPokemon == 0) {
            screen.hideLoading();
        } else {
            loadingNewPage = false;
            screen.hideListLoading();
        }

        if (error.isNetworkError()) {
            screen.showNoInternetError();
        } else {
            screen.showError(error);
        }
    }
}
