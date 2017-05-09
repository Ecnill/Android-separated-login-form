package com.example.ecnill.separatedlogin.fragments

import android.app.Activity
import android.support.v4.app.Fragment
import android.support.design.widget.TextInputLayout
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText

import com.example.ecnill.separatedlogin.R
import com.example.ecnill.separatedlogin.utils.ValidateUtils

/**
 * Created by ecnill on 12/10/16.
 */

class LoginFragment : BaseLoginFragment(LoginFragment.TAG) {

    companion object {
        private val TAG = LoginFragment::class.java.simpleName
    }

    private val USERNAME = "username"

    override fun setInputFormProperties(view: View) {
        val textInputLayout = view.findViewById(R.id.input_layout_login_form) as TextInputLayout
        textInputLayout.hint = resources.getString(R.string.settings_username)
        val editText = view.findViewById(R.id.edit_text_login_form) as EditText
        editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
    }

    override fun setButtonText(view: View) {
        val next = view.findViewById(R.id.next) as Button
        next.text = resources.getString(R.string.button_next)
    }

    override fun getButtonListener(activity: Activity, layout: View): View.OnClickListener {
        class LoginFragmentButtonListener internal constructor(activity: Activity, layout: View, editProperty: String) : BaseLoginFragment.BaseButtonListener(activity, layout, editProperty) {

            override val nextFragment: Fragment
                get() = PasswordFragment()

            override fun isValid(value: String): Boolean {
                return ValidateUtils.validate(value, ValidateUtils.EMAIL_PATTERN)
            }

            override val validationErrorMessageId: Int
                get() = R.string.settings_valid_mail

            override fun isCorrect(value: String): Boolean {
                return ValidateUtils.isEmailExist(value)
            }

            override val errorCorrectMessageId: Int
                get() = R.string.settings_exist_mail
        }

        return LoginFragmentButtonListener(activity, layout, USERNAME)
    }

}
