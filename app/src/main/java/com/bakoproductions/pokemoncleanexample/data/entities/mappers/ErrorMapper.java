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

import com.bakoproductions.pokemoncleanexample.domain.models.errors.Error;

import retrofit2.Response;

/**
 * Created by Michael on 15/4/2017.
 */

public class ErrorMapper {
    /**
     * Transforms the response to an Error
     *
     * @param error The error to be sent back to the data layer
     * @param response The retrofit response
     * @param <T> The class of the response
     * @param <E> The error class
     * @return The error object with its data filled
     */
    public <T, E extends Error> E transform(E error, Response<T> response) {
        error.setStatus(response.code());
        error.setMessage(response.message());

        return error;
    }

    /**
     * Transforms the exception to an Error
     * @param error The error to be sent back to the data layer
     * @param throwable The exception from Retrofit
     * @param <E> The error class
     * @return
     */
    public <E extends Error> E transform(E error, Throwable throwable) {
        error.setStatus(Error.NETWORK_ERROR_CODE);
        error.setMessage(throwable.getMessage());

        return error;
    }
}
