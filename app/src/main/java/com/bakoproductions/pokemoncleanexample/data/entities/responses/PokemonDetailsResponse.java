package com.bakoproductions.pokemoncleanexample.data.entities.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Michael on 16/4/2017.
 */

public class PokemonDetailsResponse {
    @SerializedName("forms")
    @Expose
    private List<Form> forms;
    @SerializedName("abilities")
    @Expose
    private List<Ability> abilities = null;
    @SerializedName("stats")
    @Expose
    private List<Stat> stats = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("weight")
    @Expose
    private Integer weight;
    @SerializedName("base_experience")
    @Expose
    private Integer baseExperience;
    @SerializedName("types")
    @Expose
    private List<Type> types = null;

    public PokemonDetailsResponse() {
        forms = null;
    }

    public List<Form> getForms() {
        return forms;
    }

    public void setForms(List<Form> forms) {
        this.forms = forms;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public List<Stat> getStats() {
        return stats;
    }

    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(Integer baseExperience) {
        this.baseExperience = baseExperience;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

}