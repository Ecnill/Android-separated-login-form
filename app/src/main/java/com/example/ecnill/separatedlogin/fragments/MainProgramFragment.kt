package com.example.ecnill.separatedlogin.fragments

import android.support.v4.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.ecnill.separatedlogin.MainActivity
import com.example.ecnill.separatedlogin.R
import com.example.ecnill.separatedlogin.utils.ValidateUtils

import butterknife.ButterKnife

/**
 * Created by ecnill on 12/10/16.
 */

class MainProgramFragment : Fragment() {

    private val TAG = MainProgramFragment::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.content_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = activity as MainActivity
        activity.supportActionBar?.show()    // turn on ActionBar after login fragment

        val settings = activity.getSharedPreferences(ValidateUtils.EULA, 0)
        val eulaOK = settings.getBoolean(ValidateUtils.EULA, false)

        if (!eulaOK) {
            activity.replaceFragment(R.id.content_main, EulaFragment(), false)
        } else {
            ButterKnife.findById<TextView>(activity, R.id.main_text).text = resources.getString(R.string.hello_world)
            ButterKnife.findById<View>(activity, R.id.fab).visibility = View.VISIBLE
        }
        setHasOptionsMenu(true)
    }

}
