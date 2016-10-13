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
import android.widget.TextView;

/**
 * Created by ecnill on 12/10/16.
 */

public class MainProgramFragment extends Fragment{
    private String TAG = "MainProgramFragment";

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
        Log.e(TAG, "onCreate");
        progress = (ProgressBar) getActivity().findViewById(R.id.progress);

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
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, new EulaFragment());
            ft.commit();
        } else {
            TextView textView = (TextView)activity.findViewById(R.id.main_text);
            textView.setText(getResources().getString(R.string.hello_world));
        }
        setHasOptionsMenu(true);
    }



}
