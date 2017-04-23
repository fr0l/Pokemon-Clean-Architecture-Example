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

package com.bakoproductions.pokemoncleanexample.domain.repository;

import java.util.ArrayList;

/**
 * Created by Michael on 15/4/2017.
 */

public interface PreferencesDataSource {
    int getIntFromPreferences(String key);

    String getStringFromPreferences(String key);

    boolean getBooleanFromPreferences(String key);

    float getFloatFromPreferences(String key);

    long getLongFromPreferences(String key);

    ArrayList<String> getStringListFromPreferences(String key);

    void writeToPreferences(String key, int value);

    void writeToPreferences(String key, String value);

    void writeToPreferences(String key, boolean value);

    void writeToPreferences(String key, float value);

    void writeToPreferences(String key, long value);

    void writeToPreferences(String key, ArrayList<String> value);

    void clearAll();

    void clear(String key);
}
