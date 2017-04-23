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

package com.bakoproductions.pokemoncleanexample.data.repository;

import com.bakoproductions.pokemoncleanexample.data.entities.mappers.ErrorMapper;
import com.bakoproductions.pokemoncleanexample.data.entities.mappers.PokemonDetailsMapper;
import com.bakoproductions.pokemoncleanexample.data.entities.mappers.PokemonListMapper;
import com.bakoproductions.pokemoncleanexample.data.entities.responses.PokemonDetailsResponse;
import com.bakoproductions.pokemoncleanexample.data.entities.responses.PokemonListResponse;
import com.bakoproductions.pokemoncleanexample.data.rest.PokemonClient;
import com.bakoproductions.pokemoncleanexample.data.rest.PokemonService;
import com.bakoproductions.pokemoncleanexample.domain.executors.BusProvider;
import com.bakoproductions.pokemoncleanexample.domain.models.Link;
import com.bakoproductions.pokemoncleanexample.domain.models.errors.PokemonDetailsError;
import com.bakoproductions.pokemoncleanexample.domain.models.errors.PokemonListError;
import com.bakoproductions.pokemoncleanexample.domain.repository.PokemonDataSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Michael on 15/4/2017.
 */

public class PokemonRepository implements PokemonDataSource {
    private PokemonClient client;

    public PokemonRepository() {
        client = PokemonService.createClient(PokemonClient.class);
    }

    @Override
    public void getPokemonList(int limit) {
        Call<PokemonListResponse> call = client.getPokemonList(limit);
        call.enqueue(new Callback<PokemonListResponse>() {
            @Override
            public void onResponse(Call<PokemonListResponse> call, Response<PokemonListResponse> response) {
                if (response.isSuccessful()) {
                    BusProvider.getRestBusInstance().post(
                            new PokemonListMapper().transform(response.body())
                    );
                } else {
                    BusProvider.getRestBusInstance().post(
                            new ErrorMapper().transform(new PokemonListError(), response)
                    );
                }
            }

            @Override
            public void onFailure(Call<PokemonListResponse> call, Throwable t) {
                BusProvider.getRestBusInstance().post(
                        new ErrorMapper().transform(new PokemonListError(), t)
                );
            }
        });
    }

    @Override
    public void getPokemonList(Link link) {
        Call<PokemonListResponse> call = client.getPokemonList(link.getLinkUrl());
        call.enqueue(new Callback<PokemonListResponse>() {
            @Override
            public void onResponse(Call<PokemonListResponse> call, Response<PokemonListResponse> response) {
                if (response.isSuccessful()) {
                    BusProvider.getRestBusInstance().post(
                            new PokemonListMapper().transform(response.body())
                    );
                } else {
                    BusProvider.getRestBusInstance().post(
                            new ErrorMapper().transform(new PokemonListError(), response)
                    );
                }
            }

            @Override
            public void onFailure(Call<PokemonListResponse> call, Throwable t) {
                BusProvider.getRestBusInstance().post(
                        new ErrorMapper().transform(new PokemonListError(), t)
                );
            }
        });
    }

    @Override
    public void getPokemonDetails(final String id) {
        Call<PokemonDetailsResponse> call = client.getPokemonDetails(id);
        call.enqueue(new Callback<PokemonDetailsResponse>() {
            @Override
            public void onResponse(Call<PokemonDetailsResponse> call, Response<PokemonDetailsResponse> response) {
                if (response.isSuccessful()) {
                    BusProvider.getRestBusInstance().post(
                            new PokemonDetailsMapper().transform(id, response.body())
                    );
                } else {
                    BusProvider.getRestBusInstance().post(
                            new ErrorMapper().transform(new PokemonDetailsError(), response)
                    );
                }
            }

            @Override
            public void onFailure(Call<PokemonDetailsResponse> call, Throwable t) {
                BusProvider.getRestBusInstance().post(
                        new ErrorMapper().transform(new PokemonDetailsError(), t)
                );
            }
        });
    }
}
