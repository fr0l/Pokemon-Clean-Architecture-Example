package com.bakoproductions.pokemoncleanexample.data.entities.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Michael on 16/4/2017.
 */

public class Stat {
    @SerializedName("stat")
    @Expose
    private StatDetails statDetails;
    @SerializedName("effort")
    @Expose
    private Integer effort;
    @SerializedName("base_stat")
    @Expose
    private Integer baseStat;

    public StatDetails getStatDetails() {
        return statDetails;
    }

    public void setStatDetails(StatDetails statDetails) {
        this.statDetails = this.statDetails;
    }

    public Integer getEffort() {
        return effort;
    }

    public void setEffort(Integer effort) {
        this.effort = effort;
    }

    public Integer getBaseStat() {
        return baseStat;
    }

    public void setBaseStat(Integer baseStat) {
        this.baseStat = baseStat;
    }
}
