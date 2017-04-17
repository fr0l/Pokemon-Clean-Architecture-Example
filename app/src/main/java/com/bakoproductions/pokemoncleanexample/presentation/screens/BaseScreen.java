package com.bakoproductions.pokemoncleanexample.presentation.screens;

import android.content.Context;

import com.bakoproductions.pokemoncleanexample.domain.models.errors.Error;

/**
 * Created by Michael on 14/4/2017.
 */

public interface BaseScreen {
    Context getContext();
    void initializeUI();
    void setToolbarTitle(String title);
    void showLoading();
    void hideLoading();
    void showNoInternetError();
    void showError(Error error);
}
