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

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bakoproductions.pokemoncleanexample.R;
import com.bakoproductions.pokemoncleanexample.domain.models.Pokemon;
import com.bakoproductions.pokemoncleanexample.domain.models.errors.Error;
import com.bakoproductions.pokemoncleanexample.presentation.components.custom.Dialogs;
import com.bakoproductions.pokemoncleanexample.presentation.components.utils.AnimationUtils;
import com.bakoproductions.pokemoncleanexample.presentation.components.utils.AvatarLoader;
import com.bakoproductions.pokemoncleanexample.presentation.presenters.PokemonDetailsPresenter;
import com.bakoproductions.pokemoncleanexample.presentation.screens.PokemonDetailsScreen;

/**
 * Created by Michael on 17/4/2017.
 *
 * This is the second activity of the project.
 */
public class PokemonDetailsActivity extends AppCompatActivity implements
                                                                    PokemonDetailsScreen,
                                                                    View.OnClickListener {
    private static final String KEY_EXTRA_POKEMON = "extra_pokemon";

    /**
     * It is a good practise to provide a "static factory method" for your Activity. Although you
     * cannot add a static factory method over the activity's constructor, you can guide the
     * developer that requests the PokemonDetailsActivity to provide a Pokemon in order to run
     * properly.
     *
     * @param context The context needed to create the Intent
     * @param pokemon The Pokemon that we need to display
     * @return the actual Intent that has all the data we need in order to initiate this activity
     */
    public static Intent getCallingIntent(Context context, Pokemon pokemon) {
        Intent intent = new Intent(context, PokemonDetailsActivity.class);
        intent.putExtra(KEY_EXTRA_POKEMON, pokemon);
        return intent;
    }

    private View pokemonDetailsPanel;
    private ImageView pokemonAvatar;
    private TextView pokemonName;
    private View pokemonWeightPanel;
    private TextView pokemonWeightLabel;
    private View pokemonBaseXPPanel;
    private TextView pokemonBaseXPLabel;
    private View pokemonTypesPanel;
    private TextView pokemonTypesLabel;
    private View pokemonAbilitiesPanel;
    private TextView pokemonAbilitiesLabel;
    private View pokemonStatsPanel;
    private TextView pokemonStatsLabel;

    private View progressPanel;

    // We need the reference to the presenter
    private PokemonDetailsPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Getting the pokemon data
        Pokemon pokemon = (Pokemon) getIntent().getSerializableExtra(KEY_EXTRA_POKEMON);

        // Creating the presenter
        initializePresenter(pokemon);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.destroy();
    }

    /*
        Screen method implementations
     */

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void initializeUI() {
        setContentView(R.layout.act_pokemon_details);

        pokemonDetailsPanel = findViewById(R.id.detailsPanel);
        pokemonAvatar = (ImageView) findViewById(R.id.pokemonAvatar);
        pokemonName = (TextView) findViewById(R.id.pokemonName);
        pokemonWeightPanel = findViewById(R.id.weightPanel);
        pokemonWeightPanel.setOnClickListener(this);
        pokemonWeightLabel = (TextView) findViewById(R.id.weightLabel);
        pokemonBaseXPPanel = findViewById(R.id.baseXPPanel);
        pokemonBaseXPPanel.setOnClickListener(this);
        pokemonBaseXPLabel = (TextView) findViewById(R.id.baseXPLabel);
        pokemonTypesPanel = findViewById(R.id.typesPanel);
        pokemonTypesPanel.setOnClickListener(this);
        pokemonTypesLabel = (TextView) findViewById(R.id.typesLabel);
        pokemonAbilitiesPanel = findViewById(R.id.abilitiesPanel);
        pokemonAbilitiesPanel.setOnClickListener(this);
        pokemonAbilitiesLabel = (TextView) findViewById(R.id.abilitiesLabel);
        pokemonStatsPanel = findViewById(R.id.statsPanel);
        pokemonStatsPanel.setOnClickListener(this);
        pokemonStatsLabel = (TextView) findViewById(R.id.statsLabel);

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
    public void showDetailsPanel() {
        AnimationUtils.startFadeInAnimator(pokemonDetailsPanel);
    }

    @Override
    public void setAvatar(String avatarUrl) {
        AvatarLoader.loadAvatar(pokemonAvatar, avatarUrl);
    }

    @Override
    public void setName(String name) {
        pokemonName.setText(name);
    }

    @Override
    public void setWeight(String weight) {
        pokemonWeightLabel.setText(weight);
    }

    @Override
    public void setBaseXP(String xp) {
        pokemonBaseXPLabel.setText(xp);
    }

    @Override
    public void setTypes(String types) {
        pokemonTypesLabel.setText(types);
    }

    @Override
    public void setAbilities(String abilities) {
        pokemonAbilitiesLabel.setText(abilities);
    }

    @Override
    public void setStats(String stats) {
        pokemonStatsLabel.setText(stats);
    }

    @Override
    public void showLegend(String message) {
        Dialogs.showMessageDialog(this, message);
    }

    /*
        Click events delegate to the presenter
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.weightPanel:
                presenter.onWeightInteracted();
                break;
            case R.id.baseXPPanel:
                presenter.onBaseXPInteracted();
                break;
            case R.id.typesPanel:
                presenter.onTypesInteracted();
                break;
            case R.id.abilitiesPanel:
                presenter.onAbilitiesInteracted();
                break;
            case R.id.statsPanel:
                presenter.onStatsInteracted();
                break;
        }
    }

    private void initializePresenter(Pokemon pokemon) {
        presenter = new PokemonDetailsPresenter(this);
        presenter.initialize(pokemon);
    }
}
