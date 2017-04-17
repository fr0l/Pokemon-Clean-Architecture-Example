package com.bakoproductions.pokemoncleanexample.presentation.screens;

/**
 * Created by Michael on 17/4/2017.
 */

public interface PokemonDetailsScreen extends BaseScreen {
    void showDetailsPanel();
    void setAvatar(String avatarUrl);
    void setName(String name);
    void setWeight(String weight);
    void setBaseXP(String xp);
    void setTypes(String types);
    void setAbilities(String abilities);
    void setStats(String stats);

    void showLegend(String message);
}
