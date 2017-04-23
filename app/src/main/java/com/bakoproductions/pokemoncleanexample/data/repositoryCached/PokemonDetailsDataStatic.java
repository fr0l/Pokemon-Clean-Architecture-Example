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

package com.bakoproductions.pokemoncleanexample.data.repositoryCached;

import com.bakoproductions.pokemoncleanexample.domain.models.PokemonDetails;
import com.bakoproductions.pokemoncleanexample.domain.repository.PokemonDetailsDataCache;

import java.util.ArrayList;

/**
 * Created by Michael on 17/4/2017.
 */

public class PokemonDetailsDataStatic implements PokemonDetailsDataCache {
    private static PokemonDetailsDataStatic INSTANCE;

    public static PokemonDetailsDataStatic get() {
        if (INSTANCE == null) {
            INSTANCE = new PokemonDetailsDataStatic();
        }

        return INSTANCE;
    }

    private ArrayList<PokemonDetails> detailsList;

    private PokemonDetailsDataStatic() {
        detailsList = new ArrayList<>();
    }

    @Override
    public void addPokemonDetails(PokemonDetails details) {
        detailsList.add(details);
    }

    @Override
    public boolean hasPokemonDetails(String id) {
        return detailsList.contains(new PokemonDetails(id));
    }

    @Override
    public PokemonDetails getPokemonDetails(String id) {
        return detailsList.get(detailsList.indexOf(new PokemonDetails(id)));
    }

    @Override
    public void removePokemonDetails(String id) {
        detailsList.remove(detailsList.indexOf(new PokemonDetails(id)));
    }

    @Override
    public void removeAll() {
        detailsList.clear();
    }
}
