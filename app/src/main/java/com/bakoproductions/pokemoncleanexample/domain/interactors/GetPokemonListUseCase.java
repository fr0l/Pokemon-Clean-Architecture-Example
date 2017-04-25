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

import com.bakoproductions.pokemoncleanexample.domain.models.Link;
import com.bakoproductions.pokemoncleanexample.domain.models.PokemonList;
import com.bakoproductions.pokemoncleanexample.domain.models.errors.PokemonListError;
import com.bakoproductions.pokemoncleanexample.domain.repository.PokemonDataSource;
import com.squareup.otto.Subscribe;

/**
 * Created by Michael on 15/4/2017.
 *
 * This use case is responsible for getting a page of Pokemons
 */
public class GetPokemonListUseCase extends BaseUseCase {
    private int limit;
    private Link nextLink;

    private PokemonDataSource dataSource;

    /**
     * The constructor we call to create the first page request
     * @param limit How many data we want to fetch the first time. This is also used by the rest api
     *              to know how many data will be included to every next page.
     * @param dataSource The implementor of the actual request
     */
    public GetPokemonListUseCase(int limit, PokemonDataSource dataSource) {
        this.limit = limit;
        this.dataSource = dataSource;
    }

    /**
     * The constructor we call to create every next page request
     * @param nextLink The next page's link
     * @param dataSource The implementor of the actual request
     */
    public GetPokemonListUseCase(Link nextLink, PokemonDataSource dataSource) {
        this.nextLink = nextLink;
        this.dataSource = dataSource;
    }

    /*
        The parent requests to get the subscribers when it's constructor is hit
     */
    @Override
    protected Object setSubscriber() {
        return new Object() {
            @Subscribe
            public void onPokemonListReceived(PokemonList pokemonList) {
                // Just post back the response and unregister the listener
                post(pokemonList);
                unregisterUseCaseSubscriber();
            }

            @Subscribe
            public void onPokemonListError(PokemonListError error) {
                // Just post back the error and unregister the listener
                post(error);
                unregisterUseCaseSubscriber();
            }
        };
    }

    @Override
    protected void onExecute() {
        // Register the listener
        registerUseCaseSubscriber();

        if (nextLink != null) {
            // If the request was made by the second constructor get the next page
            dataSource.getPokemonList(nextLink);
        } else {
            // Otherwise get the first page
            dataSource.getPokemonList(limit);
        }
    }
}
