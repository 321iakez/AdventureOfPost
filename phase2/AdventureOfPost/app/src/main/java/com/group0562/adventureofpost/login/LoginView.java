package com.group0562.adventureofpost.login;

interface LoginView {

    void setUsernameError();

    void setPasswordError();

    void onLoginSuccess();

    void onLoginError();
}
