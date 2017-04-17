package com.bakoproductions.pokemoncleanexample.data.rest;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Michael on 15/4/2017.
 */

public class PokemonService {
    private static final String BASE_URL = "http://pokeapi.co/api/v2/";

    private static HttpLoggingInterceptor LOG_INTERCEPTOR =
            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient HTTP_CLIENT =
            new OkHttpClient.Builder()
                    .addInterceptor(LOG_INTERCEPTOR)
                    .build();

    private static Retrofit RETROFIT_SERVICE =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(HTTP_CLIENT)
                    .build();


    public static <S> S createClient(Class<S> clientInterface) {
        return RETROFIT_SERVICE.create(clientInterface);
    }
}
