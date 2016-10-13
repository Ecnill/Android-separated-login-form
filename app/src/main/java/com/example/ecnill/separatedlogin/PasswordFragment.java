package com.example.ecnill.separatedlogin;

import android.app.Activity;
import android.app.Fragment;
import android.support.design.widget.TextInputLayout;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by ecnill on 12/10/16.
 */

public class PasswordFragment extends BaseLoginFragment {

    private static String TAG = "LoginFragment";
    private String PASSWORD = "password";

    public PasswordFragment() {
        super(TAG);
    }


    @Override
    protected void setInputFormProperties(View view) {
        TextInputLayout textInputLayout = (TextInputLayout)view.findViewById(R.id.input_layout_login_form);
        textInputLayout.setHint(getResources().getString(R.string.settings_password));
        EditText editText = (EditText) view.findViewById(R.id.edit_text_login_form);
        editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }

    @Override
    protected void setButtonText(View view) {
        final Button next = (Button) view.findViewById(R.id.next);
        next.setText(getResources().getString(R.string.button_next));
    }

    @Override
    protected  View.OnClickListener getButtonListener(Activity activity, View layout) {
        class PasswordFragmentButtonListener extends BaseButtonListener {

            private PasswordFragmentButtonListener(Activity activity, View layout, String editProperty) {
                super(activity, layout, editProperty);
            }

            @Override
            protected Fragment getNextFragment() {
                return new ServerFragment();
            }

            @Override
            protected boolean isValid(String value) {
                return Utils.validate(value, Utils.PASSWORD_PATTERN);
            }

            @Override
            protected int getValidationErrorMessageID() {
                return R.string.settings_valid_password;
            }

            @Override
            protected boolean isCorrect(String value) {
                return Utils.isPasswordCorrect(value);
            }

            @Override
            protected int getErrorCorrectMessageID() {
                return R.string.settings_correct_password;
            }
        }
        return new PasswordFragmentButtonListener(activity, layout, PASSWORD);
    }

}


