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

package com.bakoproductions.pokemoncleanexample.presentation.components.utils;

import android.widget.ImageView;

import com.bakoproductions.pokemoncleanexample.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Michael on 16/4/2017.
 */

/**
 * You should use any Image Loading library right away in the activity.
 * We usually have this "wrapper" class that delegates the requests to an
 * underlying library like Picasso.
 *
 * Bear in mind that this class doesn't have to be inside the Data Layer
 * The actual data that we need is the avatar url which is fetched from the Data Layer.
 */
public class AvatarLoader {
    /**
     * Just a simple static method to put an image from the web to the ImageView
     * @param imageView The view that the image is added
     * @param url The url that Picasso should request
     */
    public static void loadAvatar(ImageView imageView, String url) {
        Picasso
                .with(imageView.getContext())
                .load(url)
                .placeholder(R.drawable.pokeball)
                .into(imageView);
    }
}
