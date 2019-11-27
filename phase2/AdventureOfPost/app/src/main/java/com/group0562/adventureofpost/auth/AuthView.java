package com.group0562.adventureofpost.auth;

import android.content.Context;

public interface AuthView {

    void setUsernameError();

    void setPasswordError();

    void onSuccess();

    void onFail();

    Context getContext();
}
