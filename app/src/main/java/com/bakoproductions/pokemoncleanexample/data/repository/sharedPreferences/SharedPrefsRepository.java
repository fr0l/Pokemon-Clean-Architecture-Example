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

package com.bakoproductions.pokemoncleanexample.data.repository.sharedPreferences;

import android.app.Application;
import android.content.SharedPreferences;

import com.bakoproductions.pokemoncleanexample.domain.repository.PreferencesDataSource;
import com.bakoproductions.pokemoncleanexample.presentation.components.PokemonApplication;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Michael on 15/4/2017.
 */

public class SharedPrefsRepository implements PreferencesDataSource {
    private static SharedPrefsRepository INSTANCE;

    public static SharedPrefsRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SharedPrefsRepository();
        }

        return INSTANCE;
    }

    private SharedPreferences settings;

    private SharedPrefsRepository() {
        settings = PokemonApplication.getInstance().getApplicationContext()
                .getSharedPreferences(Prefs.PREFS_FILE_NAME, Application.MODE_PRIVATE);
    }

    @Override
    public int getIntFromPreferences(String key) {
        return settings.getInt(key, 0);
    }

    @Override
    public String getStringFromPreferences(String key) {
        return settings.getString(key, "");
    }

    @Override
    public boolean getBooleanFromPreferences(String key) {
        return settings.getBoolean(key, false);
    }

    @Override
    public float getFloatFromPreferences(String key) {
        return settings.getFloat(key, 0.0f);
    }

    @Override
    public long getLongFromPreferences(String key) {
        return settings.getLong(key, 0L);
    }

    @Override
    public ArrayList<String> getStringListFromPreferences(String key) {
        String value = settings.getString(key, "");

        ArrayList<String> ret = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(value);

            for (int i = 0; i < array.length(); i++) {
                ret.add(array.getString(i));
            }

            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return ret;
        }
    }

    @Override
    public void writeToPreferences(String key, int value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    @Override
    public void writeToPreferences(String key, String value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    @Override
    public void writeToPreferences(String key, boolean value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    @Override
    public void writeToPreferences(String key, float value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    @Override
    public void writeToPreferences(String key, long value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    @Override
    public void writeToPreferences(String key, ArrayList<String> value) {
        SharedPreferences.Editor editor = settings.edit();

        JSONArray array = new JSONArray();
        if (value != null) {
            for (int i = 0; i < value.size(); i++) {
                array.put(value.get(i));
            }
        }

        editor.putString(key, array.toString());
        editor.apply();
    }

    @Override
    public void clearAll() {
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.apply();
    }

    @Override
    public void clear(String key) {
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(key);
        editor.apply();
    }
}
