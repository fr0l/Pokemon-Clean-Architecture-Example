package com.bakoproductions.pokemoncleanexample.data.entities.mappers;

import com.bakoproductions.pokemoncleanexample.domain.models.errors.Error;

import retrofit2.Response;

/**
 * Created by Michael on 15/4/2017.
 */

public class ErrorMapper {
    public <T, E extends Error> E transform(E error, Response<T> response) {
        error.setStatus(response.code());
        error.setMessage(response.message());

        return error;
    }

    public <E extends Error> E transform(E error, Throwable throwable) {
        error.setStatus(Error.NETWORK_ERROR_CODE);
        error.setMessage(throwable.getMessage());

        return error;
    }
}
