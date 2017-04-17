package com.bakoproductions.pokemoncleanexample.presentation.components;

import android.app.Application;

/**
 * Created by Michael on 15/4/2017.
 */

public class PokemonApplication extends Application {
    private static PokemonApplication INSTANCE;

    public static PokemonApplication getInstance() {
        if (INSTANCE == null) {
            throw new IllegalStateException("Application is not created yet");
        }

        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;
    }
}
