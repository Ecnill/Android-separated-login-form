package com.example.ecnill.separatedlogin.fragments

import android.support.v4.app.Fragment
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.FloatingActionButton
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.CheckBox

import com.example.ecnill.separatedlogin.MainActivity
import com.example.ecnill.separatedlogin.R
import com.example.ecnill.separatedlogin.utils.FragmentChangeListener
import com.example.ecnill.separatedlogin.utils.ValidateUtils

import butterknife.ButterKnife

/**
 * Created by ecnill on 12/10/16.
 */

class EulaFragment : Fragment(), ViewTreeObserver.OnGlobalLayoutListener {

    private val TAG = EulaFragment::class.simpleName
    private val handler = Handler()
    private lateinit var layout: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as MainActivity).supportActionBar!!.hide()

        layout = inflater.inflate(R.layout.fragment_eula, container, false)
        val fab = ButterKnife.findById<FloatingActionButton>(activity, R.id.fab)
        fab.visibility = View.GONE
        val vto = layout.viewTreeObserver
        vto.addOnGlobalLayoutListener(this)
        return layout
    }

    override fun onGlobalLayout() {
        val next = ButterKnife.findById<Button>(activity, R.id.next)
        next.isEnabled = false

        val check = ButterKnife.findById<CheckBox>(activity, R.id.checkbox)
        check.setOnClickListener { _ ->
            if (check.isChecked) {
                next.isClickable = true
            }

            val settings = activity.getSharedPreferences(ValidateUtils.EULA, 0)
            val editor = settings.edit()
            editor.putBoolean(ValidateUtils.EULA, true)
            editor.apply()

            val fr = LoginFragment()
            val fc = activity as FragmentChangeListener

            next.isEnabled = true
            next.setOnClickListener { _ ->
                handler.postDelayed(
                        { fc.replaceFragment(R.id.content_main, fr, false) }, ValidateUtils.BUTTON_ANIMATION_DELAY)
            }
        }

        layout.viewTreeObserver.removeOnGlobalLayoutListener(this)
    }

}
