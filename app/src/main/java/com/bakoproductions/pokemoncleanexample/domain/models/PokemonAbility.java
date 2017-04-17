package com.bakoproductions.pokemoncleanexample.domain.models;

/**
 * Created by Michael on 16/4/2017.
 */

public class PokemonAbility {
    private boolean hidden;
    private String name;

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
