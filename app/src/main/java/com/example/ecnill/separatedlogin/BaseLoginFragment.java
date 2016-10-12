package com.example.ecnill.separatedlogin;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
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

/**
 * Created by ecnill on 12/10/16.
 */

public abstract class BaseLoginFragment extends Fragment {

    private final String TAG;

    public BaseLoginFragment(String TAG) {
        this.TAG = TAG;
    }

    protected abstract View.OnClickListener getButtonListener(Activity activity, View layout);
    protected abstract void setInputFormProperties(View view);
    protected abstract void setButtonText(View view);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final Activity activity = getActivity();
        final View layout = inflater.inflate(R.layout.fragment_sign_in, container, false);

        setInputFormProperties(layout);

        ViewTreeObserver vto = layout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                final Button button = (Button) activity.findViewById(R.id.next);
                setButtonText(layout);
                button.setOnClickListener(getButtonListener(activity, layout));
                layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        return layout;
    }

    protected abstract static class BaseButtonListener implements View.OnClickListener {
        final Activity activity;
        final View layout;
        final Handler handler = new Handler();

        protected BaseButtonListener(Activity activity, View layout) {
            this.activity = activity;
            this.layout = layout;
        }

        protected abstract Fragment getNextFragment();
        protected abstract String getPropertyName();
        protected abstract boolean isValidate(String value);
        protected abstract int getValidationErrorMessageID();
        protected abstract boolean isCorrect(String value);
        protected abstract int getErrorCorrectMessageID();

        @Override
        public void onClick(View v) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    final TextInputLayout fieldWrapper = (TextInputLayout) activity.findViewById(R.id.input_layout_login_form);
                    final String text = fieldWrapper.getEditText().getText().toString();
                    if (!isValidate(text)) {
                        fieldWrapper.setError(activity.getString(getValidationErrorMessageID()));
                        return;
                    }
                    if (!isCorrect(text)) {
                        fieldWrapper.setError(activity.getString(getErrorCorrectMessageID()));
                        return;
                    }
                    fieldWrapper.setErrorEnabled(false);
                    SharedPreferences settings = activity.getSharedPreferences(Utils.LOGIN_SETTINGS, 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString(getPropertyName(), ((EditText) activity.findViewById(R.id.edit_text_login_form)).getText().toString());
                    editor.apply();

                    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(layout.getWindowToken(), 0);

                    final FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
                    ft.replace(R.id.content_main, getNextFragment());
                    ft.commit();
                }
            }, Utils.BUTTON_ANIMATION_DELAY);

        }
    }

}
