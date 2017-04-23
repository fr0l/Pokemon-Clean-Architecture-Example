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
import java.util.ArrayList;

/**
 * Created by Michael on 15/4/2017.
 */

public class PokemonList implements Serializable {
    private ArrayList<Pokemon> pokemonList;
    private Link nextLink;

    public PokemonList() {
        pokemonList = new ArrayList<>();
    }

    public ArrayList<Pokemon> getPokemonList() {
        return pokemonList;
    }

    public void setPokemonList(ArrayList<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    public int getPageSize() {
        if (pokemonList != null) {
            return pokemonList.size();
        }

        return 0;
    }

    public Link getNextLink() {
        return nextLink;
    }

    public void setNextLink(Link nextLink) {
        this.nextLink = nextLink;
    }

    public boolean hasNextList() {
        return nextLink != null && nextLink.isValid();
    }
}
