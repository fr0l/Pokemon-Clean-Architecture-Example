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

package com.bakoproductions.pokemoncleanexample.data.entities.mappers;

import com.bakoproductions.pokemoncleanexample.data.entities.responses.PokemonListItemResult;
import com.bakoproductions.pokemoncleanexample.data.entities.responses.PokemonListResponse;
import com.bakoproductions.pokemoncleanexample.domain.models.Pokemon;
import com.bakoproductions.pokemoncleanexample.domain.models.PokemonList;

/**
 * Created by Michael on 15/4/2017.
 */

public class PokemonListMapper {
    /**
     * Transforms the PokemonListResponse response to the PokemonList model
     *
     * @param response The data layer's GSON entity
     * @return The domain layer's model
     */
    public PokemonList transform(PokemonListResponse response) {
        PokemonList result = new PokemonList();

        if (response == null || response.getResults() == null) {
            return result;
        }

        for (int i=0;i<response.getResults().size();i++) {
            PokemonListItemResult item = response.getResults().get(i);

            Pokemon pokemon = new Pokemon();
            pokemon.setName(item.getName());
            pokemon.setUrl(new LinkMapper().transform(item.getUrl()));

            result.getPokemonList().add(pokemon);
        }

        result.setNextLink(new LinkMapper().transform(response.getNext()));
        return result;
    }
}
