package com.bakoproductions.pokemoncleanexample.domain.models;

import java.io.Serializable;

/**
 * Created by Michael on 15/4/2017.
 */

public class Pokemon implements Serializable {
    private String id;
    private String name;
    private String nameFormatted;
    private Link url;

    public String getId() {
        if (id != null) {
            return id;
        }

        if (url.isValid()) {
            id = url.getLinkUrl()
                    .replace("http://pokeapi.co/api/v2/pokemon/", "")
                    .replace("/", "");
        }

        return id;
    }

    public String getName() {
        return name;
    }

    public String getNameFormatted() {
        if (name == null) {
            return null;
        }

        if (nameFormatted != null) {
            return nameFormatted;
        }

        String[] nameSplit = name.split("-");
        StringBuilder builder = new StringBuilder();
        for (int i=0;i<nameSplit.length;i++) {
            if (i != 0) {
                builder.append(" ");
            }
            char firstLetter = Character.toUpperCase(nameSplit[i].charAt(0));
            builder.append(firstLetter).append(nameSplit[i].substring(1));
        }

        nameFormatted = builder.toString();
        return nameFormatted;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Link getUrl() {
        return url;
    }

    public void setUrl(Link url) {
        this.url = url;
    }

    public String getAvatarUrl() {
        return "https://img.pokemondb.net/artwork/" + name + ".jpg";
    }
}
