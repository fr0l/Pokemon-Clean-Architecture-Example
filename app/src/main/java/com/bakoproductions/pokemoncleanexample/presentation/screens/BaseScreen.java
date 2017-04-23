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

package com.bakoproductions.pokemoncleanexample.presentation.screens;

import android.content.Context;

import com.bakoproductions.pokemoncleanexample.domain.models.errors.Error;

/**
 * Created by Michael on 14/4/2017.
 *
 * This is the basic things we need our UI to do in every screen
 */
public interface BaseScreen {
    // Sometimes the Presenter needs access to the Context
    Context getContext();

    // The presenter decides when the initialization if the UI elements should happen
    void initializeUI();

    // Need make the toolbar title naming mechanism dynamic
    void setToolbarTitle(String title);

    // Two methods that are responsible for showing and hiding a loading progress
    // when we request some data asynchronously.
    void showLoading();
    void hideLoading();

    // Two methods that take care the presentation of common errors to the user
    void showNoInternetError();
    void showError(Error error);
}
