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

import butterknife.ButterKnife;

/**
 * Created by ecnill on 12/10/16.
 */

public final class ServerFragment extends BaseLoginFragment {

    private static String TAG = ServerFragment.class.getSimpleName();
    private String SERVER = "server";

    public ServerFragment() {
        super(TAG);
    }

    @Override
    protected void setInputFormProperties(View view) {
        TextInputLayout textInputLayout = (TextInputLayout)view.findViewById(R.id.input_layout_login_form);
        textInputLayout.setHint(getResources().getString(R.string.settings_server));
        EditText editText = ButterKnife.findById(view, R.id.edit_text_login_form);
        editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_URI);
    }

    @Override
    protected void setButtonText(View view) {
        final Button next = ButterKnife.findById(view, R.id.next);
        next.setText(getResources().getString(R.string.button_sign_in));
    }

    @Override
    protected View.OnClickListener getButtonListener(Activity activity, View layout) {
        class ServerFragmentButtonListener extends BaseButtonListener {

            private ServerFragmentButtonListener(Activity activity, View layout, String editProperty) {
                super(activity, layout, editProperty);
            }

            @Override
            protected Fragment getNextFragment() {
                return getFragmentManager().findFragmentByTag(MainProgramFragment.class.getSimpleName());
            }

            @Override
            protected boolean isValid(String value) {
                return ValidateUtils.validate(value, ValidateUtils.SERVER_PATTERN);
            }

            @Override
            protected int getValidationErrorMessageId() {
                return R.string.settings_valid_server_address;
            }

            @Override
            protected boolean isCorrect(String value) {
                return ValidateUtils.isServerAddressCorrect(value);
            }

            @Override
            protected int getErrorCorrectMessageId() {
                return R.string.settings_correct_server_address;
            }
        }

        return new ServerFragmentButtonListener(activity, layout, SERVER);
    }

}
