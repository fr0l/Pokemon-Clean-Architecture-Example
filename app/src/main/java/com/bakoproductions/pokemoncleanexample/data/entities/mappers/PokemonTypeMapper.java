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

package com.bakoproductions.pokemoncleanexample.data.entities.mappers;

import com.bakoproductions.pokemoncleanexample.data.entities.responses.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 16/4/2017.
 */

public class PokemonTypeMapper {
    public String transform(Type response) {
        if (response == null) {
            return null;
        }

        if (response.getTypeDetails() != null) {
            return response.getTypeDetails().getName();
        } else {
            return null;
        }
    }

    public ArrayList<String> transform(List<Type> response) {
        ArrayList<String> types = new ArrayList<>();
        if (response == null) {
            return types;
        }

        for (int i=0;i<response.size();i++) {
            String type = transform(response.get(i));

            if (type != null) {
                types.add(type);
            }
        }

        return types;
    }
}
