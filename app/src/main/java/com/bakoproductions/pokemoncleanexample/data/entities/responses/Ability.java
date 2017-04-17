package com.bakoproductions.pokemoncleanexample.data.entities.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Michael on 16/4/2017.
 */

public class Ability {
    @SerializedName("slot")
    @Expose
    private Integer slot;
    @SerializedName("is_hidden")
    @Expose
    private Boolean isHidden;
    @SerializedName("ability")
    @Expose
    private AbilityDetails abilityDetails;

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public Boolean getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Boolean isHidden) {
        this.isHidden = isHidden;
    }

    public AbilityDetails getAbilityDetails() {
        return abilityDetails;
    }

    public void setAbilityDetails(AbilityDetails abilityDetails) {
        this.abilityDetails = abilityDetails;
    }
}
