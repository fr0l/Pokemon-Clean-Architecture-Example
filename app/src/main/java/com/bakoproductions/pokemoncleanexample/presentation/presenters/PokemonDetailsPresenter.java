package com.bakoproductions.pokemoncleanexample.presentation.presenters;

import com.bakoproductions.pokemoncleanexample.R;
import com.bakoproductions.pokemoncleanexample.data.repository.PokemonRepository;
import com.bakoproductions.pokemoncleanexample.data.repositoryCached.PokemonDetailsDataStatic;
import com.bakoproductions.pokemoncleanexample.domain.interactors.GetPokemonDetailsUseCase;
import com.bakoproductions.pokemoncleanexample.domain.models.Pokemon;
import com.bakoproductions.pokemoncleanexample.domain.models.PokemonAbility;
import com.bakoproductions.pokemoncleanexample.domain.models.PokemonDetails;
import com.bakoproductions.pokemoncleanexample.domain.models.PokemonStat;
import com.bakoproductions.pokemoncleanexample.domain.models.errors.PokemonDetailsError;
import com.bakoproductions.pokemoncleanexample.presentation.screens.PokemonDetailsScreen;
import com.squareup.otto.Subscribe;

/**
 * Created by Michael on 17/4/2017.
 */

public class PokemonDetailsPresenter extends BasePresenter {
    private PokemonDetailsScreen screen;

    public PokemonDetailsPresenter(PokemonDetailsScreen screen) {
        this.screen = screen;
    }

    public void initialize(Pokemon pokemon) {
        screen.initializeUI();
        screen.setToolbarTitle(getString(R.string.pokemon_details, pokemon.getNameFormatted()));
        register();

        fillPokemon(pokemon);
        getDetails(pokemon);
    }

    public void destroy() {
        unregister();
    }

    public void onWeightInteracted() {
        screen.showLegend(getString(R.string.leg_weight));
    }

    public void onBaseXPInteracted() {
        screen.showLegend(getString(R.string.leg_base_xp));
    }

    public void onTypesInteracted() {
        screen.showLegend(getString(R.string.leg_types));
    }

    public void onAbilitiesInteracted() {
        screen.showLegend(getString(R.string.leg_abilities));
    }

    public void onStatsInteracted() {
        screen.showLegend(getString(R.string.leg_stats));
    }

    public void getDetails(Pokemon pokemon) {
        screen.showLoading();
        executeUseCase(new GetPokemonDetailsUseCase(
                pokemon.getId(),
                new PokemonRepository(),
                PokemonDetailsDataStatic.get()));
    }

    @Subscribe
    public void onPokemonDetailsReceived(PokemonDetails details) {
        screen.hideLoading();
        screen.showDetailsPanel();
        fillDetails(details);
    }

    @Subscribe
    public void onPokemonDetailsError(PokemonDetailsError error) {
        screen.hideLoading();

        if (error.isNetworkError()) {
            screen.showNoInternetError();
        } else {
            screen.showError(error);
        }
    }

    void fillPokemon(Pokemon pokemon) {
        screen.setAvatar(pokemon.getAvatarUrl());
        screen.setName(pokemon.getNameFormatted());
    }

    void fillDetails(PokemonDetails details) {
        fillWeight(details);
        fillBaseXP(details);
        fillTypes(details);
        fillAbilities(details);
        fillStats(details);
    }

    void fillWeight(PokemonDetails details) {
        screen.setWeight(details.getWeightKG() + getString(R.string.kg));
    }

    void fillBaseXP(PokemonDetails details) {
        screen.setBaseXP(details.getBaseXP() + getString(R.string.xp));
    }

    void fillTypes(PokemonDetails details) {
        StringBuilder builder = new StringBuilder();

        for (int i=0;i<details.getTypes().size();i++) {
            if (i != 0) {
                builder.append("\n");
            }
            builder.append(details.getTypes().get(i));
        }

        screen.setTypes(builder.toString());
    }

    void fillAbilities(PokemonDetails details) {
        StringBuilder builder = new StringBuilder();

        for (int i=0;i<details.getAbilities().size();i++) {
            if (i != 0) {
                builder.append("\n");
            }

            PokemonAbility ability = details.getAbilities().get(i);
            String visibility =
                    ability.isHidden() ? getString(R.string.hidden) : getString(R.string.visible);
            builder.append(ability.getName())
                    .append(" - ").append(visibility);
        }

        screen.setAbilities(builder.toString());
    }

    void fillStats(PokemonDetails details) {
        StringBuilder builder = new StringBuilder();

        for (int i=0;i<details.getStats().size();i++) {
            if (i != 0) {
                builder.append("\n");
            }

            PokemonStat stat = details.getStats().get(i);
            builder.append(stat.getName())
                    .append("\n  • ").append(getString(R.string.effort)).append("  ").append(stat.getEffort())
                    .append("\n  • ").append(getString(R.string.base_stat)).append("  ").append(stat.getBase());
        }

        screen.setStats(builder.toString());
    }

    String getString(int resource) {
        return screen.getContext().getString(resource);
    }

    String getString(int resource, String arg) {
        return screen.getContext().getString(resource, arg);
    }
}
