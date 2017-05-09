package com.example.ecnill.separatedlogin.fragments;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecnill.separatedlogin.MainActivity;
import com.example.ecnill.separatedlogin.R;
import com.example.ecnill.separatedlogin.utils.Utils;

/**
 * Created by ecnill on 12/10/16.
 */

public class MainProgramFragment extends Fragment{
    private String TAG = MainProgramFragment.class.getSimpleName();

    public MainProgramFragment() {
        Log.d(TAG, TAG + ": created");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_main, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MainActivity activity = ((MainActivity) getActivity());
        activity.getSupportActionBar().show();    // turn on ActionBar after login fragment

        SharedPreferences settings = activity.getSharedPreferences(Utils.EULA, 0);
        final boolean eulaOK = settings.getBoolean(Utils.EULA, false);

        if (!eulaOK) {
            Fragment fr = new EulaFragment();
            activity.replaceFragment(R.id.content_main, fr, false);
        } else {
            TextView textView = (TextView)activity.findViewById(R.id.main_text);
            textView.setText(getResources().getString(R.string.hello_world));

            FloatingActionButton fab = (FloatingActionButton) activity.findViewById(R.id.fab);
            fab.setVisibility(View.VISIBLE);
        }
        setHasOptionsMenu(true);
    }


}
