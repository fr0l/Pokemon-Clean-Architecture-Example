package com.bakoproductions.pokemoncleanexample.presentation.components.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by Michael on 17/4/2017.
 */

public class AnimationUtils {
    public static void startFadeInAnimator(final View view) {
        ObjectAnimator fadeAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, 0, 1);
        fadeAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);

                view.setVisibility(View.VISIBLE);
            }
        });
        fadeAnimator.setDuration(150);
        fadeAnimator.start();
    }

    public static void startFadeOutAnimator(final View view) {
        ObjectAnimator fadeAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, 1, 0);
        fadeAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                view.setVisibility(View.GONE);
            }
        });
        fadeAnimator.setDuration(150);
        fadeAnimator.start();
    }
}
