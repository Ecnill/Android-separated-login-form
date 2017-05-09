package com.example.ecnill.separatedlogin.utils

import android.support.v4.app.Fragment

/**
 * Created by ecnill on 27/02/17.
 */

interface FragmentChangeListener {

    fun replaceFragment(containerId: Int, fragment: Fragment, addToBackStack: Boolean)

}
