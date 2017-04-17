package com.bakoproductions.pokemoncleanexample.domain.interactors;

import com.bakoproductions.pokemoncleanexample.domain.models.PokemonDetails;
import com.bakoproductions.pokemoncleanexample.domain.models.errors.PokemonDetailsError;
import com.bakoproductions.pokemoncleanexample.domain.repository.PokemonDataSource;
import com.bakoproductions.pokemoncleanexample.domain.repository.PokemonDetailsDataCache;
import com.squareup.otto.Subscribe;

/**
 * Created by Michael on 16/4/2017.
 */

public class GetPokemonDetailsUseCase extends BaseUseCase {
    private String id;
    private PokemonDataSource dataSource;
    private PokemonDetailsDataCache dataCache;

    public GetPokemonDetailsUseCase(String id, PokemonDataSource dataSource, PokemonDetailsDataCache dataCache) {
        this.id = id;
        this.dataSource = dataSource;
        this.dataCache = dataCache;
    }

    @Override
    protected Object setSubscriber() {
        return new Object() {
            @Subscribe
            public void onPokemonDetailsReceived(PokemonDetails details) {
                dataCache.addPokemonDetails(details);
                post(details);
                unregisterUseCaseSubscriber();
            }

            @Subscribe
            public void onPokemonDetailsError(PokemonDetailsError error) {
                post(error);
                unregisterUseCaseSubscriber();
            }
        };
    }

    @Override
    protected void onExecute() {
        if (dataCache.hasPokemonDetails(id)) {
            post(dataCache.getPokemonDetails(id));
        } else {
            registerUseCaseSubscriber();
            dataSource.getPokemonDetails(id);
        }
    }
}
