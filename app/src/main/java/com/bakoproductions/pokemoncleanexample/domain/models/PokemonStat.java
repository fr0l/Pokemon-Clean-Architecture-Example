package com.bakoproductions.pokemoncleanexample.domain.models;

/**
 * Created by Michael on 16/4/2017.
 */

public class PokemonStat {
    private String name;
    private int effort;
    private int base;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEffort() {
        return effort;
    }

    public void setEffort(int effort) {
        this.effort = effort;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }
}
