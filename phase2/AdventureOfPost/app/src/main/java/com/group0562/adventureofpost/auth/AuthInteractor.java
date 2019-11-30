package com.group0562.adventureofpost.auth;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.group0562.adventureofpost.database.DatabaseHelper;

public class AuthInteractor {

    interface AuthListener {
        void onUsernameError();

        void onPasswordError();

        void onSuccess();

        void onVerifyFail();
    }

    void login(String username, String password, AuthListener listener, Context context) {
        // Database instance
        DatabaseHelper db = new DatabaseHelper(context);

        // Check for empty inputs
        if (TextUtils.isEmpty(username)) {
            listener.onUsernameError();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            listener.onPasswordError();
            return;
        }

        // Verify credentials
        if (db.verify(username, password)) {
            listener.onSuccess();
        } else {
            listener.onVerifyFail();
        }
    }

    void register(String username, String password, String confirmPassword, AuthListener listener, Context context) {
        // Database instance
        DatabaseHelper db = new DatabaseHelper(context);

        // Verify whether inputs are empty
        if (username.equals("")) {
            listener.onUsernameError();
            return;
        }
        if (password.equals("") || confirmPassword.equals("")) {
            listener.onPasswordError();
            return;
        }

        // Check whether both password inputs match
        if (password.equals(confirmPassword)) {
            if (db.checkUsernameDup(username)) {
                long newRowId = db.insertAuth(username, password);
                Log.i("RegisterActivity", "Register new user at row" + newRowId);

                listener.onSuccess();
            } else {
                listener.onVerifyFail();
            }
        } else {
            listener.onPasswordError();
        }
    }
}
