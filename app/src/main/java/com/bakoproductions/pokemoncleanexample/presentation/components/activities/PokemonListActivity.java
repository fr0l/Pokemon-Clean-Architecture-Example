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

public class PokemonListActivity extends AppCompatActivity implements PokemonListScreen, View.OnClickListener {

    private TextView welcomeMessage;
    private RecyclerView list;
    private View progressPanel;

    private LinearLayoutManager listLayoutManager;
    private PokemonListAdapter adapter;

    private PokemonListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializePresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.destroy();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void initializeUI() {
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
        if (v.getId() == R.id.welcomeText) {
            presenter.onWelcomeInteracted();
        }
    }

    private void initializePresenter() {
        presenter = new PokemonListPresenter(this);
        presenter.initialize();
    }

    private class ListScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            presenter.onListScrolled(listLayoutManager.findFirstVisibleItemPosition());
        }
    }
}
