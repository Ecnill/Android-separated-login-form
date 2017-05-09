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

class ServerFragment : BaseLoginFragment(ServerFragment.TAG) {

    companion object {
        private val TAG = ServerFragment::class.java.simpleName
    }

    private val SERVER = "server"

    override fun setInputFormProperties(view: View) {
        val textInputLayout = ButterKnife.findById<TextInputLayout>(view, R.id.input_layout_login_form)
        textInputLayout.hint = resources.getString(R.string.settings_server)
        val editText = ButterKnife.findById<EditText>(view, R.id.edit_text_login_form)
        editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_URI
    }

    override fun setButtonText(view: View) {
        val next = ButterKnife.findById<Button>(view, R.id.next)
        next.text = resources.getString(R.string.button_sign_in)
    }

    override fun getButtonListener(activity: Activity, layout: View): View.OnClickListener {
        class ServerFragmentButtonListener internal constructor(activity: Activity, layout: View, editProperty: String) : BaseLoginFragment.BaseButtonListener(activity, layout, editProperty) {

            override val nextFragment: Fragment
                get() = fragmentManager.findFragmentByTag(MainProgramFragment::class.java.simpleName)

            override fun isValid(value: String): Boolean {
                return ValidateUtils.validate(value, ValidateUtils.SERVER_PATTERN)
            }

            override val validationErrorMessageId: Int
                get() = R.string.settings_valid_server_address

            override fun isCorrect(value: String): Boolean {
                return ValidateUtils.isServerAddressCorrect(value)
            }

            override val errorCorrectMessageId: Int
                get() = R.string.settings_correct_server_address
        }

        return ServerFragmentButtonListener(activity, layout, SERVER)
    }

}
