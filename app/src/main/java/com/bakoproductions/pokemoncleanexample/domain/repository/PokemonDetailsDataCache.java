package com.bakoproductions.pokemoncleanexample.domain.repository;

import com.bakoproductions.pokemoncleanexample.domain.models.PokemonDetails;

/**
 * Created by Michael on 17/4/2017.
 */

public interface PokemonDetailsDataCache {
    void addPokemonDetails(PokemonDetails details);

    boolean hasPokemonDetails(String id);

    PokemonDetails getPokemonDetails(String id);

    void removePokemonDetails(String id);

    void removeAll();
}
