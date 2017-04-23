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

import com.bakoproductions.pokemoncleanexample.data.entities.responses.Ability;
import com.bakoproductions.pokemoncleanexample.domain.models.PokemonAbility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 16/4/2017.
 */

public class PokemonAbilitiesMapper {
    public PokemonAbility transform(Ability response) {
        if (response == null) {
            return null;
        }

        PokemonAbility ability = new PokemonAbility();

        ability.setHidden(response.getIsHidden());
        if (response.getAbilityDetails() != null) {
            ability.setName(response.getAbilityDetails().getName());
        }

        return ability;
    }

    public ArrayList<PokemonAbility> transform(List<Ability> response) {
        ArrayList<PokemonAbility> abilities = new ArrayList<>();
        if (response == null) {
            return abilities;
        }

        for (int i=0;i<response.size();i++) {
            abilities.add(transform(response.get(i)));
        }

        return abilities;
    }
}
