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

import com.bakoproductions.pokemoncleanexample.data.entities.responses.PokemonDetailsResponse;
import com.bakoproductions.pokemoncleanexample.domain.models.PokemonDetails;

/**
 * Created by Michael on 16/4/2017.
 */
public class PokemonDetailsMapper {
    public PokemonDetails transform(String id, PokemonDetailsResponse response) {
        PokemonDetails details = new PokemonDetails();

        if (response == null) {
            return details;
        }

        details.setId(id);
        details.setBaseXP(response.getBaseExperience());
        details.setWeight(response.getWeight());
        details.setAbilities(new PokemonAbilitiesMapper().transform(response.getAbilities()));
        details.setTypes(new PokemonTypeMapper().transform(response.getTypes()));
        details.setStats(new PokemonStatsMapper().transform(response.getStats()));
        return details;
    }
}
