package com.bakoproductions.pokemoncleanexample.data.entities.mappers;

import com.bakoproductions.pokemoncleanexample.data.entities.responses.PokemonListItemResult;
import com.bakoproductions.pokemoncleanexample.data.entities.responses.PokemonListResponse;
import com.bakoproductions.pokemoncleanexample.domain.models.Pokemon;
import com.bakoproductions.pokemoncleanexample.domain.models.PokemonList;

/**
 * Created by Michael on 15/4/2017.
 */

public class PokemonListMapper {
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
