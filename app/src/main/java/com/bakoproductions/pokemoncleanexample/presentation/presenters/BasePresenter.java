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
