/*
 * Copyright 2017 Michael Bakogiannis, Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bakoproductions.pokemoncleanexample.domain.models;

import java.io.Serializable;

/**
 * Created by Michael on 15/4/2017.
 *
 * Holds the basic data of a Pokemon
 */
public class Pokemon implements Serializable {
    private String id;
    private String name;
    private String nameFormatted;
    private Link url;

    /**
     * Finds and returns the id of the Pokemon. The id comes from the end of the url
     * @return the id of the Pokemon
     */
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

    /**
     * Formats the name of the Pokemon in order to be human readable
     * @return the real name of the Pokemon
     */
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

    /**
     * The pokeapi.co has banned the stripe urls. This page pokemondb.net provides most of the
     * pokemon avatars
     * @return The pokemondb avatar url
     */
    public String getAvatarUrl() {
        return "https://img.pokemondb.net/artwork/" + name + ".jpg";
    }
}
