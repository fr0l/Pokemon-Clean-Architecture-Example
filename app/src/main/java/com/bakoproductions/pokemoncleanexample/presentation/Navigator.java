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
