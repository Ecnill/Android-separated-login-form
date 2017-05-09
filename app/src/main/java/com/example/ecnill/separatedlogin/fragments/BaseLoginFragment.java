package com.example.ecnill.separatedlogin.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.ecnill.separatedlogin.R;
import com.example.ecnill.separatedlogin.utils.FragmentChangeListener;
import com.example.ecnill.separatedlogin.utils.ValidateUtils;

import butterknife.ButterKnife;

/**
 * Created by ecnill on 12/10/16.
 */

public abstract class BaseLoginFragment extends Fragment implements ViewTreeObserver.OnGlobalLayoutListener {

    private final String TAG;
    private View layout;

    public BaseLoginFragment(String TAG) {
        this.TAG = TAG;
    }

    protected abstract View.OnClickListener getButtonListener(Activity activity, View layout);
    protected abstract void setInputFormProperties(View view);
    protected abstract void setButtonText(View view);

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_sign_in, container, false);
        setInputFormProperties(layout);
        ViewTreeObserver vto = layout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(this);
        return layout;
    }

    @Override public void onGlobalLayout() {
        final Button button = ButterKnife.findById(getActivity(), R.id.next);
        setButtonText(layout);
        button.setOnClickListener(getButtonListener(getActivity(), layout));
        layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    protected abstract static class BaseButtonListener implements View.OnClickListener {
        final Activity activity;
        final View layout;
        final Handler handler = new Handler();
        final String editProperty;

        protected BaseButtonListener(Activity activity, View layout, String editProperty) {
            this.editProperty = editProperty;
            this.activity = activity;
            this.layout = layout;
        }

        protected abstract Fragment getNextFragment();
        protected abstract boolean isValid(String value);
        protected abstract int getValidationErrorMessageId();
        protected abstract boolean isCorrect(String value);
        protected abstract int getErrorCorrectMessageId();

        @Override public void onClick(View v) {
            handler.postDelayed(new Runnable() {
                @Override public void run() {
                    final TextInputLayout fieldWrapper = ButterKnife.findById(activity, R.id.input_layout_login_form);
                    final String text = fieldWrapper.getEditText().getText().toString();
                    if (!isValid(text)) {
                        fieldWrapper.setError(activity.getString(getValidationErrorMessageId()));
                        return;
                    }
                    if (!isCorrect(text)) {
                        fieldWrapper.setError(activity.getString(getErrorCorrectMessageId()));
                        return;
                    }
                    fieldWrapper.setErrorEnabled(false);
                    SharedPreferences settings = activity.getSharedPreferences(ValidateUtils.LOGIN_SETTINGS, 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString(editProperty, ((EditText) activity.findViewById(R.id.edit_text_login_form)).getText().toString());
                    editor.apply();

                    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(layout.getWindowToken(), 0);

                    Fragment fr = getNextFragment();
                    FragmentChangeListener fc = (FragmentChangeListener)activity;
                    fc.replaceFragment(((ViewGroup)layout.getParent()).getId(), fr, false);
                }
            }, ValidateUtils.BUTTON_ANIMATION_DELAY);

        }

    }

}
