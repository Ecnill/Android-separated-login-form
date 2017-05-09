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

import butterknife.ButterKnife

/**
 * Created by ecnill on 12/10/16.
 */

class PasswordFragment : BaseLoginFragment(PasswordFragment.TAG) {

    companion object {
        private val TAG = PasswordFragment::class.java.simpleName
    }

    private val PASSWORD = "password"

    override fun setInputFormProperties(view: View) {
        val textInputLayout = ButterKnife.findById<TextInputLayout>(view, R.id.input_layout_login_form)
        textInputLayout.hint = resources.getString(R.string.settings_password)
        val editText = ButterKnife.findById<EditText>(view, R.id.edit_text_login_form)
        editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
    }

    override fun setButtonText(view: View) {
        val next = view.findViewById(R.id.next) as Button
        next.text = resources.getString(R.string.button_next)
    }

    override fun getButtonListener(activity: Activity, layout: View): View.OnClickListener {
        class PasswordFragmentButtonListener internal constructor(activity: Activity, layout: View, editProperty: String) : BaseLoginFragment.BaseButtonListener(activity, layout, editProperty) {

            override val nextFragment: Fragment
                get() = ServerFragment()

            override fun isValid(value: String): Boolean {
                return ValidateUtils.validate(value, ValidateUtils.PASSWORD_PATTERN)
            }

            override val validationErrorMessageId: Int
                get() = R.string.settings_valid_password

            override fun isCorrect(value: String): Boolean {
                return ValidateUtils.isPasswordCorrect(value)
            }

            override val errorCorrectMessageId: Int
                get() = R.string.settings_correct_password
        }

        return PasswordFragmentButtonListener(activity, layout, PASSWORD)
    }

}
