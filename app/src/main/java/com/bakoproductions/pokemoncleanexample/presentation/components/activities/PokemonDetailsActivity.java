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
 */

public class PokemonDetailsActivity extends AppCompatActivity implements
                                                                    PokemonDetailsScreen,
                                                                    View.OnClickListener {
    private static final String KEY_EXTRA_POKEMON = "extra_pokemon";

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

    private PokemonDetailsPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Pokemon pokemon = (Pokemon) getIntent().getSerializableExtra(KEY_EXTRA_POKEMON);
        initializePresenter(pokemon);
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
