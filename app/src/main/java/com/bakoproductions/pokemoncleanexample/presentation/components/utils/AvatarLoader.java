package com.bakoproductions.pokemoncleanexample.presentation.components.utils;

import android.widget.ImageView;

import com.bakoproductions.pokemoncleanexample.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Michael on 16/4/2017.
 */

public class AvatarLoader {
    public static void loadAvatar(ImageView imageView, String url) {
        Picasso
                .with(imageView.getContext())
                .load(url)
                .placeholder(R.drawable.pokeball)
                .into(imageView);
    }
}
