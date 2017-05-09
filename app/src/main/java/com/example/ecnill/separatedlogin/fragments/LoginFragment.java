package com.example.ecnill.separatedlogin.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.design.widget.TextInputLayout;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ecnill.separatedlogin.R;
import com.example.ecnill.separatedlogin.utils.ValidateUtils;

/**
 * Created by ecnill on 12/10/16.
 */

public final class LoginFragment extends BaseLoginFragment {

    private static String TAG = "LoginFragment";
    private String USERNAME = "username";

    public LoginFragment() {
        super(TAG);
    }

    @Override
    protected void setInputFormProperties(View view) {
        TextInputLayout textInputLayout = (TextInputLayout)view.findViewById(R.id.input_layout_login_form);
        textInputLayout.setHint(getResources().getString(R.string.settings_username));
        EditText editText = (EditText) view.findViewById(R.id.edit_text_login_form);
        editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
    }

    @Override
    protected void setButtonText(View view) {
        final Button next = (Button) view.findViewById(R.id.next);
        next.setText(getResources().getString(R.string.button_next));
    }

    @Override
    protected  View.OnClickListener getButtonListener(Activity activity, View layout) {
        class LoginFragmentButtonListener extends BaseButtonListener {

            private LoginFragmentButtonListener(Activity activity, View layout, String editProperty) {
                super(activity, layout, editProperty);
            }

            @Override
            protected Fragment getNextFragment() {
                return new PasswordFragment();
            }

            @Override
            protected boolean isValid(String value) {
                return ValidateUtils.validate(value, ValidateUtils.EMAIL_PATTERN);
            }

            @Override
            protected int getValidationErrorMessageId() {
                return R.string.settings_valid_mail;
            }

            @Override
            protected boolean isCorrect(String value) {
                return ValidateUtils.isEmailExist(value);
            }

            @Override
            protected int getErrorCorrectMessageId() {
                return R.string.settings_exist_mail;
            }
        }

        return new LoginFragmentButtonListener(activity, layout, USERNAME);
    }

}
