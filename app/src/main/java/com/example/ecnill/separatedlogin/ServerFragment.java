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

public class ServerFragment extends BaseLoginFragment {

    private static String TAG = "ServerFragment";

    public ServerFragment() {
        super(TAG);
    }

    @Override
    protected void setInputFormProperties(View view) {
        TextInputLayout textInputLayout = (TextInputLayout)view.findViewById(R.id.input_layout_login_form);
        textInputLayout.setHint(getResources().getString(R.string.settings_server));
        EditText editText = (EditText) view.findViewById(R.id.edit_text_login_form);
        editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_URI);
    }

    @Override
    protected void setButtonText(View view) {
        final Button next = (Button) view.findViewById(R.id.next);
        next.setText(getResources().getString(R.string.button_sign_in));
    }

    @Override
    protected View.OnClickListener getButtonListener(Activity activity, View layout) {
        class ServerFragmentButtonListener extends BaseButtonListener {

            String SERVER_PROPERTY = "server";

            protected ServerFragmentButtonListener(Activity activity, View layout) {
                super(activity, layout);
            }

            @Override
            protected Fragment getNextFragment() {
                return new MainProgramFragment();
            }

            @Override
            protected String getPropertyName() {
                return SERVER_PROPERTY;
            }

            @Override
            protected boolean isValidate(String value) {
                return Utils.validateServerAddress(value);
            }

            @Override
            protected int getValidationErrorMessageID() {
                return R.string.settings_valid_server_address;
            }

            @Override
            protected boolean isCorrect(String value) {
                return Utils.isServerAddressCorrect(value);
            }

            @Override
            protected int getErrorCorrectMessageID() {
                return R.string.settings_correct_server_address;
            }
        }
        return new ServerFragmentButtonListener(activity, layout);
    }
}
