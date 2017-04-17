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
