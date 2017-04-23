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

import com.bakoproductions.pokemoncleanexample.data.entities.responses.Stat;
import com.bakoproductions.pokemoncleanexample.domain.models.PokemonStat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 16/4/2017.
 */

public class PokemonStatsMapper {
    public PokemonStat transform(Stat response) {
        if (response == null) {
            return null;
        }

        PokemonStat stat = new PokemonStat();
        if (response.getStatDetails() != null) {
            stat.setName(response.getStatDetails().getName());
        }
        stat.setBase(response.getBaseStat());
        stat.setEffort(response.getEffort());
        return stat;
    }

    public ArrayList<PokemonStat> transform(List<Stat> response) {
        ArrayList<PokemonStat> stats = new ArrayList<>();

        if (response == null) {
            return stats;
        }

        for (int i=0;i<response.size();i++) {
            PokemonStat stat = transform(response.get(i));

            if (stat != null) {
                stats.add(stat);
            }
        }

        return stats;
    }
}
