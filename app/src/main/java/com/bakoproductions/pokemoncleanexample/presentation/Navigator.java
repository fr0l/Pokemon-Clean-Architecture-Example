package com.bakoproductions.pokemoncleanexample.presentation;

import android.content.Context;
import android.content.Intent;

import com.bakoproductions.pokemoncleanexample.domain.models.Pokemon;
import com.bakoproductions.pokemoncleanexample.presentation.components.activities.PokemonDetailsActivity;

/**
 * Created by Michael on 17/4/2017.
 */

public class Navigator {
    private static Navigator INSTANCE;

    public static Navigator get() {
        if (INSTANCE == null) {
            INSTANCE = new Navigator();
        }

        return INSTANCE;
    }

    public void navigateToDetails(Context context, Pokemon pokemon) {
        Intent intent = PokemonDetailsActivity.getCallingIntent(context, pokemon);
        context.startActivity(intent);
    }
}
