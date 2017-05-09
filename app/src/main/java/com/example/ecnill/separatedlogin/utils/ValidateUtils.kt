package com.example.ecnill.separatedlogin.utils

import java.util.regex.Pattern

/**
 * Created by ecnill on 9/1/2016.
 */

object ValidateUtils {

    val SERVER_PATTERN = "(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
    val EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@$SERVER_PATTERN"
    val PASSWORD_PATTERN = "(?=.*[a-zA-Z0-9@#$%,.-_]).{5,}"
    // more advanced password pattern: "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})"
    // more then 6 characters; uppercase, lower case characters and digit are required.

    // delays in millis for postDelayed
    var BUTTON_ANIMATION_DELAY: Long = 400

    // strings for SharedPreferences
    val LOGIN_SETTINGS = "LoginSettings"
    val EULA = "EULA"


    fun validate(value: String, pattern: String): Boolean {
        val p = Pattern.compile(pattern)
        return p.matcher(value).matches()
    }

    //==============================================================================================
    /**
     * Temp method just for checking login field's message.
     * @param email
     * *
     * @return True if email is exist in database.
     */
    fun isEmailExist(email: String): Boolean {
        return "ecnill@example.com" == email
    }

    /**
     * Temp method just for checking password field's message.
     * @param password
     * *
     * @return True if password is correct.
     */
    fun isPasswordCorrect(password: String): Boolean {
        return "Aa11A" == password
    }

    /**
     * Temp method just for checking server address field's message.
     * @param server
     * *
     * @return
     */
    fun isServerAddressCorrect(server: String): Boolean {
        return "example.com" == server
    }

}
