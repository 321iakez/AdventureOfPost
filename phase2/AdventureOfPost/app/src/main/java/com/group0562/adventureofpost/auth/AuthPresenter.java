package com.group0562.adventureofpost.auth;

public class AuthPresenter implements AuthInteractor.AuthListener {

    private AuthView authView;
    private AuthInteractor interactor;

    public AuthPresenter(AuthView authView, AuthInteractor interactor) {
        this.authView = authView;
        this.interactor = interactor;
    }

    public void validateCredentials(String username, String password) {
        interactor.login(username, password, this, authView.getContext());
    }

    public void validateRegister(String username, String password, String confirmPassword) {
        interactor.register(username, password, confirmPassword, this, authView.getContext());
    }

    @Override
    public void onUsernameError() {
        authView.setUsernameError();
    }

    @Override
    public void onPasswordError() {
        authView.setPasswordError();
    }

    @Override
    public void onSuccess() {
        authView.onSuccess();
    }

    @Override
    public void onVerifyFail() {
        authView.onFail();
    }
}
