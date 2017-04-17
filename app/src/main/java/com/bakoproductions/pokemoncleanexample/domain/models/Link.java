package com.bakoproductions.pokemoncleanexample.domain.models;

import java.io.Serializable;

/**
 * Created by Michael on 15/4/2017.
 */

public class Link implements Serializable {
    private String linkUrl;

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public boolean isValid() {
        return linkUrl != null && !linkUrl.isEmpty();
    }
}
