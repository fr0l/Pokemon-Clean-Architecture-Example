package com.bakoproductions.pokemoncleanexample.domain.repository;

import com.bakoproductions.pokemoncleanexample.domain.models.Link;

/**
 * Created by Michael on 15/4/2017.
 */

public interface PokemonDataSource {
    void getPokemonList(int limit);
    void getPokemonList(Link link);
    void getPokemonDetails(String id);
}
