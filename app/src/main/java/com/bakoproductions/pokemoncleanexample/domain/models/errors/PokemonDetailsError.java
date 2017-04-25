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

package com.bakoproductions.pokemoncleanexample.domain.models.errors;

/**
 * Created by Michael on 16/4/2017.
 */
public class PokemonDetailsError extends Error {
    // Normally you don't have to declare an error object for every use case
    // But if you use Otto you have to.
    // By that you can ensure that every use case will only listen to what it needs to listen
    // and this is a downside of using Otto
}
