package com.bakoproductions.pokemoncleanexample.data.rest;

import com.bakoproductions.pokemoncleanexample.data.entities.responses.PokemonDetailsResponse;
import com.bakoproductions.pokemoncleanexample.data.entities.responses.PokemonListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Michael on 15/4/2017.
 */

public interface PokemonClient {
    @GET("pokemon/")
    Call<PokemonListResponse> getPokemonList(@Query("limit") int limit);

    @GET
    Call<PokemonListResponse> getPokemonList(@Url String nextUrl);

    @GET("pokemon/{id}/")
    Call<PokemonDetailsResponse> getPokemonDetails(@Path("id") String id);
}
