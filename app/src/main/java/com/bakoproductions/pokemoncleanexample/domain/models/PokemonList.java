package com.bakoproductions.pokemoncleanexample.domain.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Michael on 15/4/2017.
 */

public class PokemonList implements Serializable {
    private ArrayList<Pokemon> pokemonList;
    private Link nextLink;

    public PokemonList() {
        pokemonList = new ArrayList<>();
    }

    public ArrayList<Pokemon> getPokemonList() {
        return pokemonList;
    }

    public void setPokemonList(ArrayList<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    public int getPageSize() {
        if (pokemonList != null) {
            return pokemonList.size();
        }

        return 0;
    }

    public Link getNextLink() {
        return nextLink;
    }

    public void setNextLink(Link nextLink) {
        this.nextLink = nextLink;
    }

    public boolean hasNextList() {
        return nextLink != null && nextLink.isValid();
    }
}
