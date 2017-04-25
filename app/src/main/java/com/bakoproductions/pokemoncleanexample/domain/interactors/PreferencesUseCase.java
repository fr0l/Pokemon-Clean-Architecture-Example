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

package com.bakoproductions.pokemoncleanexample.domain.interactors;

import com.bakoproductions.pokemoncleanexample.domain.repository.PreferencesDataSource;

import java.util.ArrayList;

/**
 * Created by Michael on 15/4/2017.
 *
 * This is a simpler use case as everything happens synchronously. There are no subscribers here.
 */
public class PreferencesUseCase {
    private final PreferencesDataSource dataSource;

    /**
     * The creator of this use case
     * @param dataSource The implementor of the request. (In this case the implementor will always
     *                   be the SharedPrefsRepository)
     */
    public PreferencesUseCase(PreferencesDataSource dataSource) {
        this.dataSource = dataSource;
    }

    /*
        All the methods below support some of the most common operations when we store key - value
        preferences in a data source.
     */

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
