package com.example.ecnill.separatedlogin;

import com.example.ecnill.separatedlogin.fragments.MainProgramFragment;
import com.example.ecnill.separatedlogin.utils.FragmentChangeListener;
import com.example.ecnill.separatedlogin.utils.SnackbarUtils;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public final class MainActivity extends AppCompatActivity implements FragmentChangeListener {

    private String TAG = MainActivity.class.getSimpleName();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = ButterKnife.findById(this, R.id.toolbar);
        setSupportActionBar(toolbar);
        Fragment fr = new MainProgramFragment();
        replaceFragment(R.id.content_main, fr, true);
    }

    @OnClick(R.id.fab)
     public void floatButtonClick(View view) {
        Snackbar snackbar = SnackbarUtils.createSnackbar(view, "Something...");
        snackbar.show();
    }

    @Override public void replaceFragment(int containerId, Fragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerId, fragment, fragment.getClass().getSimpleName());
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        fragmentTransaction.commit();
    }

}
