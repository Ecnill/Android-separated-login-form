package com.example.ecnill.separatedlogin.utils;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;

/**
 * Created by ecnill on 5/9/2017.
 */

public abstract class SnackbarUtils {

    public static Snackbar createSnackbar(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.RED);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.WHITE);

        TextView textView = ButterKnife.findById(snackbarView, android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.BLUE);
        return snackbar;
    }

}
