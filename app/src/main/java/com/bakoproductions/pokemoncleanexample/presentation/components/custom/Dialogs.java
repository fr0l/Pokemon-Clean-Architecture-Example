package com.bakoproductions.pokemoncleanexample.presentation.components.custom;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.bakoproductions.pokemoncleanexample.R;
import com.bakoproductions.pokemoncleanexample.domain.models.errors.Error;

/**
 * Created by Michael on 16/4/2017.
 */

public class Dialogs {
    public static void showNoInternet(Context context) {
        showMessageDialog(context, context.getString(R.string.no_internet_error));
    }

    public static void showError(Context context, Error error) {
        showMessageDialog(context, error.getMessage());
    }

    public static void showMessageDialog(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }
}
