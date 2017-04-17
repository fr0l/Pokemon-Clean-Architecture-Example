package com.bakoproductions.pokemoncleanexample.data.entities.mappers;

import com.bakoproductions.pokemoncleanexample.domain.models.Link;

/**
 * Created by Michael on 15/4/2017.
 */

public class LinkMapper {
    public Link transform(String url) {
        Link link = new Link();
        link.setLinkUrl(url);
        return link;
    }
}
