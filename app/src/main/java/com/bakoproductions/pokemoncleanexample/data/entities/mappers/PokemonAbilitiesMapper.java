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
