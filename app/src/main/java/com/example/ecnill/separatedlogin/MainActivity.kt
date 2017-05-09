package com.example.ecnill.separatedlogin

import com.example.ecnill.separatedlogin.fragments.MainProgramFragment
import com.example.ecnill.separatedlogin.utils.FragmentChangeListener
import com.example.ecnill.separatedlogin.utils.SnackbarUtils

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View

import butterknife.ButterKnife
import butterknife.OnClick

class MainActivity : AppCompatActivity(), FragmentChangeListener {

    private val TAG = MainActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate")
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        setSupportActionBar(ButterKnife.findById<Toolbar>(this, R.id.toolbar))
        replaceFragment(R.id.content_main, MainProgramFragment(), true)
    }

    @OnClick(R.id.fab)
    fun floatButtonClick(view: View) {
        SnackbarUtils.createSnackbar(view, "Something...").show()
    }

    override fun replaceFragment(containerId: Int, fragment: Fragment, addToBackStack: Boolean) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(containerId, fragment, fragment.javaClass.simpleName)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.javaClass.simpleName)
        }
        fragmentTransaction.commit()
    }

}
