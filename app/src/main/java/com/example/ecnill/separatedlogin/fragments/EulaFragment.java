package com.example.ecnill.separatedlogin.fragments;

import android.support.v4.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.ecnill.separatedlogin.MainActivity;
import com.example.ecnill.separatedlogin.R;
import com.example.ecnill.separatedlogin.utils.FragmentChangeListener;
import com.example.ecnill.separatedlogin.utils.ValidateUtils;

import butterknife.ButterKnife;

/**
 * Created by ecnill on 12/10/16.
 */

public final class EulaFragment extends Fragment implements ViewTreeObserver.OnGlobalLayoutListener {

    private String TAG = EulaFragment.class.getSimpleName();
    private Handler handler = new Handler();
    private View layout;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).getSupportActionBar().hide();
        layout = inflater.inflate(R.layout.fragment_eula, container, false);
        FloatingActionButton fab = ButterKnife.findById(getActivity(), R.id.fab);
        fab.setVisibility(View.GONE);
        ViewTreeObserver vto = layout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(this);
        return layout;
    }

    @Override public void onGlobalLayout() {
        final Button next = ButterKnife.findById(getActivity(), R.id.next);
        next.setEnabled(false);

        final CheckBox check = ButterKnife.findById(getActivity(), R.id.checkbox);
        check.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (check.isChecked()) {
                    next.setClickable(true);
                }

                SharedPreferences settings = getActivity().getSharedPreferences(ValidateUtils.EULA, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean(ValidateUtils.EULA, true);
                editor.apply();

                final Fragment fr = new LoginFragment();
                final FragmentChangeListener fc = (FragmentChangeListener)getActivity();

                next.setEnabled(true);
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                fc.replaceFragment(R.id.content_main, fr, false);
                            }
                        }, ValidateUtils.BUTTON_ANIMATION_DELAY);
                    }
                });
            }
        });

        layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }
}
