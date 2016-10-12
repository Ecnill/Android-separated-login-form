package com.example.ecnill.separatedlogin;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

/**
 * Created by ecnill on 12/10/16.
 */

public class MainProgramFragment extends Fragment{
    private String TAG = "MainProgramFragment";
    public static final String LOGIN_SETTINGS = "LoginSettings";
    public static final String EULA = "EULA";

    ProgressBar progress;
    private Handler mHandler = new Handler();

    public MainProgramFragment() {
        Log.d(TAG, "DirectoryListFragment: created instance");
    }

    public static MainProgramFragment newInstance() {
        return new MainProgramFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progress = (ProgressBar) getActivity().findViewById(R.id.progress);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_main, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).getSupportActionBar().show();    //turn on ActionBar after login fragment

        SharedPreferences settings = getActivity().getSharedPreferences(EULA, 0);
        final boolean eulaOK = settings.getBoolean(EULA, false);

        if (!eulaOK) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, new EulaFragment());
            ft.commit();
        } else {
            settings = getActivity().getSharedPreferences(LOGIN_SETTINGS, 0);
            final String username = settings.getString("username", null);
            final String password = settings.getString("password", null);
            final String server = settings.getString("server", null);
        }
        setHasOptionsMenu(true);
    }







}
