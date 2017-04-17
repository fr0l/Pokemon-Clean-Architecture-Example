package com.bakoproductions.pokemoncleanexample.domain.interactors;

/**
 * Created by Michael on 16/4/2017.
 */

public interface Cacheable<S> {
    void setCachedData(S data);

    S getCachedData();

    boolean hasCachedData();

    void clear();
}
