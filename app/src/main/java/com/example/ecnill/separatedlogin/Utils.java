package com.example.ecnill.separatedlogin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ecnill on 9/1/2016.
 */

class Utils {

    private static final String SERVER_PATTERN = "(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@"
            + SERVER_PATTERN;

    private static final String PASSWORD_PATTERN = "(?=.*[a-zA-Z0-9@#$%,.-_]).{5,}";

    // more advanced password pattern: "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})"
    // more then 6 characters; uppercase, lower case characters and digit are required.

    private static Pattern mailPattern = Pattern.compile(EMAIL_PATTERN);
    private static Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
    private static Pattern serverPattern = Pattern.compile(SERVER_PATTERN);
    private static Matcher matcher;

    // delay in millis for postDelayed
    public static long BUTTON_ANIMATION_DELAY = 400;
    public static long REFRESH_ANIMATION_DELAY = 1000;

    // strings for SharedPreferences
    public static final String LOGIN_SETTINGS = "LoginSettings";
    public static final String EULA = "EULA";

    /**
     *
     * @param email
     * @return True if email is valid.
     */
    public static boolean validateEmail(String email) {
        matcher = mailPattern.matcher(email);
        return matcher.matches();
    }

    /**
     *
     * @param server
     * @return True if server address is valid.
     */
    public static boolean validateServerAddress(String server) {
        matcher = serverPattern.matcher(server);
        return matcher.matches();
    }

    /**
     *
     * @param password
     * @return True is password is valid.
     */
    public static boolean validatePassword(String password) {
        matcher = passwordPattern.matcher(password);
        return matcher.matches();
    }


    //==============================================================================================
    /**
     * Temp method just for checking login field's message.
     * @param email
     * @return True if email is exist in database.
     */
    public static boolean isEmailExist(String email) {
        return email.equals("ecnill@example.com");
    }


    /**
     * Temp method just for checking password field's message.
     * @param password
     * @return True if password is correct.
     */
    public static boolean isPasswordCorrect(String password) {
        return password.equals("Aa11A");
    }

    /**
     * Temp method just for checking server address field's message.
     * @param server
     * @return
     */
    public static boolean isServerAddressCorrect(String server) {
        return server.equals("example.com");
    }
    //==============================================================================================

}

