package com.bakoproductions.pokemoncleanexample.domain.interactors;

import com.bakoproductions.pokemoncleanexample.domain.repository.PreferencesDataSource;

import java.util.ArrayList;

/**
 * Created by Michael on 15/4/2017.
 */

public class PreferencesUseCase {
    private final PreferencesDataSource dataSource;

    public PreferencesUseCase(PreferencesDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int getIntPref(String key) {
        return dataSource.getIntFromPreferences(key);
    }

    public String getStringPref(String key) {
        return dataSource.getStringFromPreferences(key);
    }

    public boolean getBooleanPref(String key) {
        return dataSource.getBooleanFromPreferences(key);
    }

    public float getFloatPref(String key) {
        return dataSource.getFloatFromPreferences(key);
    }

    public long getLongPref(String key) {
        return dataSource.getLongFromPreferences(key);
    }

    public ArrayList<String> getStringList(String key) {
        return dataSource.getStringListFromPreferences(key);
    }

    public void savePref(String key, String value) {
        dataSource.writeToPreferences(key, value);
    }

    public void savePref(String key, long value) {
        dataSource.writeToPreferences(key, value);
    }

    public void savePref(String key, float value) {
        dataSource.writeToPreferences(key, value);
    }

    public void savePref(String key, boolean value) {
        dataSource.writeToPreferences(key, value);
    }

    public void savePref(String key, int value) {
        dataSource.writeToPreferences(key, value);
    }

    public void savePref(String key, ArrayList<String> value) {
        dataSource.writeToPreferences(key, value);
    }

    public void clearSingle(String key) {
        dataSource.clear(key);
    }

    public void clearAll() {
        dataSource.clearAll();
    }
}
