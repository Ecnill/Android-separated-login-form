package com.example.ecnill.separatedlogin;

import android.app.Fragment;
import android.app.FragmentTransaction;
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
import android.widget.ProgressBar;

/**
 * Created by ecnill on 12/10/16.
 */

public class EulaFragment extends Fragment {
    private String TAG = "EulaFragment";

    ProgressBar progress;
    private Handler handler = new Handler();

    public EulaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ((MainActivity) getActivity()).getSupportActionBar().hide();
        final View layout = inflater.inflate(R.layout.fragment_eula, container, false);
        progress = (ProgressBar) getActivity().findViewById(R.id.progress);
        final FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.GONE);

        ViewTreeObserver vto = layout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                final Button next = (Button) getActivity().findViewById(R.id.next);
                next.setEnabled(false);
                final CheckBox check = (CheckBox) getActivity().findViewById(R.id.checkbox);
                check.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (check.isChecked()) {
                            next.setClickable(true);
                        }

                        SharedPreferences settings = getActivity().getSharedPreferences(Utils.EULA, 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putBoolean(Utils.EULA, true);
                        editor.apply();

                        final FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.content_main, new LoginFragment());

                        next.setEnabled(true);
                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        ft.commit();
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

