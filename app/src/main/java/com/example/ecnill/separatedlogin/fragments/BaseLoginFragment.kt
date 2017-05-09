package com.example.ecnill.separatedlogin.fragments

import android.app.Activity
import android.support.v4.app.Fragment
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.TextInputLayout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText

import com.example.ecnill.separatedlogin.R
import com.example.ecnill.separatedlogin.utils.FragmentChangeListener
import com.example.ecnill.separatedlogin.utils.ValidateUtils

import butterknife.ButterKnife

/**
 * Created by ecnill on 12/10/16.
 */

abstract class BaseLoginFragment(private val TAG: String) : Fragment(), ViewTreeObserver.OnGlobalLayoutListener {

    private lateinit var layout: View

    protected abstract fun getButtonListener(activity: Activity, layout: View): View.OnClickListener
    protected abstract fun setInputFormProperties(view: View)
    protected abstract fun setButtonText(view: View)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        layout = inflater.inflate(R.layout.fragment_sign_in, container, false)
        setInputFormProperties(layout)
        layout.viewTreeObserver.addOnGlobalLayoutListener(this)
        return layout
    }

    override fun onGlobalLayout() {
        val button = ButterKnife.findById<Button>(activity, R.id.next)
        setButtonText(layout)
        button.setOnClickListener(getButtonListener(activity, layout))
        layout.viewTreeObserver.removeOnGlobalLayoutListener(this)
    }

    protected abstract class BaseButtonListener protected constructor(internal val activity: Activity, internal val layout: View, internal val editProperty: String) : View.OnClickListener {
        internal val handler = Handler()

        protected abstract val nextFragment: Fragment
        protected abstract fun isValid(value: String): Boolean
        protected abstract val validationErrorMessageId: Int
        protected abstract fun isCorrect(value: String): Boolean
        protected abstract val errorCorrectMessageId: Int

        override fun onClick(v: View) {
            handler.postDelayed({
                val fieldWrapper = ButterKnife.findById<TextInputLayout>(activity, R.id.input_layout_login_form)
                val text = fieldWrapper.editText!!.text.toString()
                if (!isValid(text)) {
                    fieldWrapper.error = activity.getString(validationErrorMessageId)
                    return@postDelayed
                }
                if (!isCorrect(text)) {
                    fieldWrapper.error = activity.getString(errorCorrectMessageId)
                    return@postDelayed
                }
                fieldWrapper.isErrorEnabled = false
                val settings = activity.getSharedPreferences(ValidateUtils.LOGIN_SETTINGS, 0)
                val editor = settings.edit()
                editor.putString(editProperty, (activity.findViewById(R.id.edit_text_login_form) as EditText).text.toString())
                editor.apply()

                val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(layout.windowToken, 0)

                val fr = nextFragment
                val fc = activity as FragmentChangeListener
                fc.replaceFragment((layout.parent as ViewGroup).id, fr, false)
            }, ValidateUtils.BUTTON_ANIMATION_DELAY)
        }

    }

}
