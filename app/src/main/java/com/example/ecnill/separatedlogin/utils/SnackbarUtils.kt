package com.example.ecnill.separatedlogin.utils

import android.graphics.Color
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.TextView

import butterknife.ButterKnife

/**
 * Created by ecnill on 5/9/2017.
 */

object SnackbarUtils {

    fun createSnackbar(view: View, message: String): Snackbar {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        snackbar.setActionTextColor(Color.RED)
        snackbar.view.setBackgroundColor(Color.WHITE)
        ButterKnife.findById<TextView>(snackbar.view, android.support.design.R.id.snackbar_text).setTextColor(Color.BLUE)
        return snackbar
    }

}
