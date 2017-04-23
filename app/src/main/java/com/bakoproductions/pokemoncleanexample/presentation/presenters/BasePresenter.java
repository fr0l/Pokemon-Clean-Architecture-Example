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

package com.bakoproductions.pokemoncleanexample.presentation.presenters;

import com.bakoproductions.pokemoncleanexample.data.repository.sharedPreferences.SharedPrefsRepository;
import com.bakoproductions.pokemoncleanexample.domain.executors.BusProvider;
import com.bakoproductions.pokemoncleanexample.domain.interactors.BaseUseCase;
import com.bakoproductions.pokemoncleanexample.domain.interactors.PreferencesUseCase;
import com.bakoproductions.pokemoncleanexample.presentation.Navigator;

/**
 * Created by Michael on 15/4/2017.
 */

public abstract class BasePresenter {
    private PreferencesUseCase prefsUseCase;

    public BasePresenter() {
        prefsUseCase = new PreferencesUseCase(SharedPrefsRepository.getInstance());
    }

    public Navigator getNavigator() {
        return Navigator.get();
    }

    public void executeUseCase(BaseUseCase useCase) {
        useCase.execute();
    }

    // -- Prefs helper methods
    public void storeSharedPreferences(String key, boolean value) {
        prefsUseCase.savePref(key, value);
    }

    public boolean getSharedPreferencesBoolean(String key) {
        return prefsUseCase.getBooleanPref(key);
    }

    // =================== SUBSCRIBERS ======================
    public void register() {
        try {
            BusProvider.getUIBusInstance().register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unregister() {
        try {
            BusProvider.getUIBusInstance().unregister(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
