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
