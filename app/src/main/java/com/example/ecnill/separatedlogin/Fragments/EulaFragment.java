package com.example.ecnill.separatedlogin.Fragments;

import android.app.Activity;
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
import com.example.ecnill.separatedlogin.Utils.FragmentChangeListener;
import com.example.ecnill.separatedlogin.Utils.Utils;

/**
 * Created by ecnill on 12/10/16.
 */

public class EulaFragment extends Fragment {

    private String TAG = EulaFragment.class.getSimpleName();
    private Handler handler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final Activity activity = getActivity();
        ((MainActivity) activity).getSupportActionBar().hide();
        final View layout = inflater.inflate(R.layout.fragment_eula, container, false);
        FloatingActionButton fab = (FloatingActionButton) activity.findViewById(R.id.fab);
        fab.setVisibility(View.GONE);

        ViewTreeObserver vto = layout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                final Button next = (Button) activity.findViewById(R.id.next);
                next.setEnabled(false);
                final CheckBox check = (CheckBox) activity.findViewById(R.id.checkbox);
                check.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (check.isChecked()) {
                            next.setClickable(true);
                        }

                        SharedPreferences settings = activity.getSharedPreferences(Utils.EULA, 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putBoolean(Utils.EULA, true);
                        editor.apply();

                        final Fragment fr = new LoginFragment();
                        final FragmentChangeListener fc = (FragmentChangeListener)activity;

                        next.setEnabled(true);
                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        fc.replaceFragment(R.id.content_main, fr, false);
                                    }
                                }, Utils.BUTTON_ANIMATION_DELAY);

                            }
                        });
                    }
                });
                layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }

        });

        return layout;
    }


}
