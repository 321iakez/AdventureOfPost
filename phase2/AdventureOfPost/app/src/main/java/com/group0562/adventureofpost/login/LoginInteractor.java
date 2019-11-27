package com.group0562.adventureofpost.login;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

class LoginInteractor {

    interface OnVerifyFinishedListener {
        void onUsernameEmptyError();

        void onPasswordEmptyError();

        void onLoginSuccess();

        void onLoginFail();

        void onRegisterSuccess();
    }

    void login(String username, String password, OnVerifyFinishedListener listener) {
        // Database instance
        DatabaseHelper db = new DatabaseHelper();

        // Check for empty inputs
        if (TextUtils.isEmpty(username)) {
            listener.onUsernameEmptyError();
        }
        if (TextUtils.isEmpty(password)) {
            listener.onPasswordEmptyError();
        }

        // Verify credentials
        if (db.verify(username, password)) {
            listener.onLoginSuccess();
        } else {
            listener.onLoginFail();
        }
    }

    void register(String username, String password, String confirmPassword, OnVerifyFinishedListener listener) {

    }
}
