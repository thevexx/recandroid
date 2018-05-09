package com.example.admin.tutoserevices;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by admin on 08/03/2018.
 */

public class Functions {
    private SessionManager session;


    //Main URL
 // private static String MAIN_URL = "http://192.168.253.4/mehatdev/reclamamtionprojectdev/app/serviceslogin.php?op=login";

    private static String MAIN_URL = "http://192.168.1.27/reclamamtionprojectdev/app/serviceslogin.php?op=login";

    // Login URL
    public static String LOGIN_URL = MAIN_URL;

    // Register URL
    public static String GETLIST_URL = "http://192.168.1.27/reclamamtionprojectdev/app/serviceslogin.php?op=getliste";

    // OTP Verification
    public static String REGISTER_URL =  "http://192.168.1.27/reclamamtionprojectdev/app/registerservice.php";

    // Forgot Password
    public static String OTP_VERIFY_URL = MAIN_URL + "";

    public static boolean isValidEmailAddress(String email) {
        String ePattern = "";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }


    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

        public boolean logoutUser(Context context){
            DatabaseHandler db = new DatabaseHandler(context);
            db.resetTables();
            return true;
        }


    }
