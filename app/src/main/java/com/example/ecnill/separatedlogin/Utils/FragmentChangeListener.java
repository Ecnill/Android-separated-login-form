package com.example.ecnill.separatedlogin.Utils;

import android.support.v4.app.Fragment;

/**
 * Created by ostr on 27/02/17.
 */

public interface FragmentChangeListener {

    void replaceFragment(int containerId, Fragment fragment, boolean addToBackStack);

}
