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
