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

package com.bakoproductions.pokemoncleanexample.presentation.components.activities;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bakoproductions.pokemoncleanexample.R;
import com.bakoproductions.pokemoncleanexample.domain.models.Pokemon;
import com.bakoproductions.pokemoncleanexample.domain.models.errors.Error;
import com.bakoproductions.pokemoncleanexample.presentation.components.adapters.PokemonListAdapter;
import com.bakoproductions.pokemoncleanexample.presentation.components.custom.Dialogs;
import com.bakoproductions.pokemoncleanexample.presentation.components.utils.AnimationUtils;
import com.bakoproductions.pokemoncleanexample.presentation.presenters.PokemonListPresenter;
import com.bakoproductions.pokemoncleanexample.presentation.screens.PokemonListScreen;

import java.util.ArrayList;

/**
 * Welcome to the Pokemon Clean Architecture Example Project
 *
 * This is the first screen that appears when the app stars
 */
public class PokemonListActivity extends AppCompatActivity implements PokemonListScreen, View.OnClickListener {

    private TextView welcomeMessage;
    private RecyclerView list;
    private View progressPanel;

    private LinearLayoutManager listLayoutManager;
    private PokemonListAdapter adapter;

    // Needing a reference to the presenter
    private PokemonListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * We don't usually setContentView(...) before we initialize the presenter
         * We need the presenter to request it. With this way we are able to control
         * the view lifecycle events like GoogleMap.onMapReady() callback etc.
         *
         * There is no map in this example but this is what we usually do.
         *
         * see the initializeUI() method
         */
        initializePresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.destroy();
    }

    /**
     * Below are the implementations of the PokemonListScreen
     */
    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void initializeUI() {
        // This is where the ui is constructed
        setContentView(R.layout.act_pokemon_list);

        welcomeMessage = (TextView) findViewById(R.id.welcomeText);
        welcomeMessage.setOnClickListener(this);

        list = (RecyclerView) findViewById(R.id.list);
        listLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        list.setLayoutManager(listLayoutManager);
        list.addOnScrollListener(new ListScrollListener());

        progressPanel = findViewById(R.id.progressPanel);
    }

    @Override
    public void setToolbarTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    @Override
    public void showLoading() {
        AnimationUtils.startFadeInAnimator(progressPanel);
    }

    @Override
    public void hideLoading() {
        AnimationUtils.startFadeOutAnimator(progressPanel);
    }

    @Override
    public void showNoInternetError() {
        Dialogs.showNoInternet(this);
    }

    @Override
    public void showError(Error error) {
        Dialogs.showError(this, error);
    }

    @Override
    public void showWelcomePanel() {
        AnimationUtils.startFadeInAnimator(welcomeMessage);
    }

    @Override
    public void hideWelcomePanel() {
        AnimationUtils.startFadeOutAnimator(welcomeMessage);
    }

    @Override
    public void addToPokemonList(ArrayList<Pokemon> pokemonList) {
        // When this method is hit for the first time, the adapter gets initialized first
        if (adapter == null) {
            adapter = new PokemonListAdapter(pokemonList);
            adapter.setClickListener(new PokemonListAdapter.PokemonClickListener() {
                @Override
                public void onPokemonClicked(Pokemon pokemon) {
                    presenter.onPokemonInteracted(pokemon);
                }
            });
            list.setAdapter(adapter);
        } else {
            // We don't need to initialize again, but inform the RecyclerView that
            // more rows are added
            adapter.addMorePokemon(pokemonList);
        }
    }

    @Override
    public void showListLoading() {
        list.post(new Runnable() {
            @Override
            public void run() {
                adapter.addLoading();
            }
        });

    }

    @Override
    public void hideListLoading() {
        list.post(new Runnable() {
            @Override
            public void run() {
                adapter.removeLoading();
            }
        });
    }

    @Override
    public void onClick(View v) {
        // We delegate the interaction of the outside world (user)
        // To the presenter in order to let it decide who to deal with it
        if (v.getId() == R.id.welcomeText) {
            presenter.onWelcomeInteracted();
        }
    }

    private void initializePresenter() {
        // Creating the presenter
        presenter = new PokemonListPresenter(this);
        presenter.initialize();
    }

    // This is another interaction similar to the click events
    private class ListScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            presenter.onListScrolled(listLayoutManager.findFirstVisibleItemPosition());
        }
    }
}
