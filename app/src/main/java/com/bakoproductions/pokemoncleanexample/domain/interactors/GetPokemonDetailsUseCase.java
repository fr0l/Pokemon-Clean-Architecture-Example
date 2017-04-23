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

package com.bakoproductions.pokemoncleanexample.domain.interactors;

import com.bakoproductions.pokemoncleanexample.domain.models.PokemonDetails;
import com.bakoproductions.pokemoncleanexample.domain.models.errors.PokemonDetailsError;
import com.bakoproductions.pokemoncleanexample.domain.repository.PokemonDataSource;
import com.bakoproductions.pokemoncleanexample.domain.repository.PokemonDetailsDataCache;
import com.squareup.otto.Subscribe;

/**
 * Created by Michael on 16/4/2017.
 */

public class GetPokemonDetailsUseCase extends BaseUseCase {
    private String id;
    private PokemonDataSource dataSource;
    private PokemonDetailsDataCache dataCache;

    public GetPokemonDetailsUseCase(String id, PokemonDataSource dataSource, PokemonDetailsDataCache dataCache) {
        this.id = id;
        this.dataSource = dataSource;
        this.dataCache = dataCache;
    }

    @Override
    protected Object setSubscriber() {
        return new Object() {
            @Subscribe
            public void onPokemonDetailsReceived(PokemonDetails details) {
                dataCache.addPokemonDetails(details);
                post(details);
                unregisterUseCaseSubscriber();
            }

            @Subscribe
            public void onPokemonDetailsError(PokemonDetailsError error) {
                post(error);
                unregisterUseCaseSubscriber();
            }
        };
    }

    @Override
    protected void onExecute() {
        if (dataCache.hasPokemonDetails(id)) {
            post(dataCache.getPokemonDetails(id));
        } else {
            registerUseCaseSubscriber();
            dataSource.getPokemonDetails(id);
        }
    }
}
