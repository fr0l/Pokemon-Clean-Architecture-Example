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
