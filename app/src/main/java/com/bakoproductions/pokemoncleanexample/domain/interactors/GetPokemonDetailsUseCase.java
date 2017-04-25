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
 *
 * The use case that is responsible for getting all the data that describe the pokemon
 *
 * Input: pokemon id
 * Output:
 *      1) pokemon data
 *      2) error
 *
 * This use case also handles a caching mechanism. When the pokemon details for a certain pokemon
 * are requested this use case checks if these details exist in the cache. If they do, the use case
 * does not make a network request rather a simple request from the cash repository. Both of those
 * requests end to the data layer.
 */
public class GetPokemonDetailsUseCase extends BaseUseCase {
    private String id;
    private PokemonDataSource dataSource;
    private PokemonDetailsDataCache dataCache;

    /**
     * The constructor of this use case
     *
     * @param id The pokemon id whose details we are requesting
     * @param dataSource The implementor of the actual network request
     * @param dataCache The implementor of the cache
     */
    public GetPokemonDetailsUseCase(String id, PokemonDataSource dataSource, PokemonDetailsDataCache dataCache) {
        this.id = id;
        this.dataSource = dataSource;
        this.dataCache = dataCache;
    }

    /*
        The parent requests to get the subscribers when it's constructor is hit
     */
    @Override
    protected Object setSubscriber() {
        return new Object() {
            @Subscribe
            public void onPokemonDetailsReceived(PokemonDetails details) {
                // Save the data to the cache
                dataCache.addPokemonDetails(details);
                // Post it back to the PokemonDetailsPresenter
                post(details);
                // No need for the subscriber anymore
                unregisterUseCaseSubscriber();
            }

            @Subscribe
            public void onPokemonDetailsError(PokemonDetailsError error) {
                // Post the error back to the PokemonDetailsPresenter
                post(error);
                // No need for the subscriber anymore
                unregisterUseCaseSubscriber();
            }
        };
    }

    @Override
    protected void onExecute() {
        // We need to check first if the data exist in cache
        if (dataCache.hasPokemonDetails(id)) {
            // If they do post the data back to the presenter
            post(dataCache.getPokemonDetails(id));
        } else {
            // Otherwise register the subscriber and make the request
            registerUseCaseSubscriber();
            dataSource.getPokemonDetails(id);
        }
    }
}
