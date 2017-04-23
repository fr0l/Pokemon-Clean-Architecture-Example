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
 */

public class GetPokemonListUseCase extends BaseUseCase {
    private int limit;
    private Link nextLink;

    private PokemonDataSource dataSource;

    public GetPokemonListUseCase(int limit, PokemonDataSource dataSource) {
        this.limit = limit;
        this.dataSource = dataSource;
    }

    public GetPokemonListUseCase(Link nextLink, PokemonDataSource dataSource) {
        this.nextLink = nextLink;
        this.dataSource = dataSource;
    }

    @Override
    protected Object setSubscriber() {
        return new Object() {
            @Subscribe
            public void onPokemonListReceived(PokemonList pokemonList) {
                post(pokemonList);
                unregisterUseCaseSubscriber();
            }

            @Subscribe
            public void onPokemonListError(PokemonListError error) {
                post(error);
                unregisterUseCaseSubscriber();
            }
        };
    }

    @Override
    protected void onExecute() {
        registerUseCaseSubscriber();

        if (nextLink != null) {
            dataSource.getPokemonList(nextLink);
        } else {
            getFistPokemonData();
        }
    }

    private void getFistPokemonData() {
        dataSource.getPokemonList(limit);
    }
}
