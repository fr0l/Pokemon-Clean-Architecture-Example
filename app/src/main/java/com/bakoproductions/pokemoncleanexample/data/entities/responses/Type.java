package com.bakoproductions.pokemoncleanexample.data.entities.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Michael on 16/4/2017.
 */

public class Type {
    @SerializedName("slot")
    @Expose
    private Integer slot;
    @SerializedName("type")
    @Expose
    private TypeDetails typeDetails;

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public TypeDetails getTypeDetails() {
        return typeDetails;
    }

    public void setTypeDetails(TypeDetails typeDetails) {
        this.typeDetails = typeDetails;
    }
}
