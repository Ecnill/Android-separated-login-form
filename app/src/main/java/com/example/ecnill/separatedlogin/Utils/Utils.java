package com.example.ecnill.separatedlogin.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ecnill on 9/1/2016.
 */

public class Utils {

    public static final String SERVER_PATTERN = "(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    public static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@"
            + SERVER_PATTERN;
    public static final String PASSWORD_PATTERN = "(?=.*[a-zA-Z0-9@#$%,.-_]).{5,}";
    // more advanced password pattern: "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})"
    // more then 6 characters; uppercase, lower case characters and digit are required.

    // delays in millis for postDelayed
    public static long BUTTON_ANIMATION_DELAY = 400;
    public static long REFRESH_ANIMATION_DELAY = 1000;

    // strings for SharedPreferences
    public static final String LOGIN_SETTINGS = "LoginSettings";
    public static final String EULA = "EULA";


    public static boolean validate(String value, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(value);
        return matcher.matches();
    }

    //==============================================================================================
    /**
     * Temp method just for checking login field's message.
     * @param email
     * @return True if email is exist in database.
     */
    public static boolean isEmailExist(String email) {
        return "ecnill@example.com".equals(email);
    }

    /**
     * Temp method just for checking password field's message.
     * @param password
     * @return True if password is correct.
     */
    public static boolean isPasswordCorrect(String password) {
        return "Aa11A".equals(password);
    }

    /**
     * Temp method just for checking server address field's message.
     * @param server
     * @return
     */
    public static boolean isServerAddressCorrect(String server) {
        return "example.com".equals(server);
    }

}

