package com.group0562.adventureofpost.login;

class LoginPresenter implements LoginInteractor.OnVerifyFinishedListener {

    private LoginView loginView;
    private LoginInteractor interactor;
    private RegisterView registerView;

    LoginPresenter(LoginView loginView, LoginInteractor interactor, RegisterView registerView) {
        this.loginView = loginView;
        this.interactor = interactor;
        this.registerView = registerView;
    }

    void validateCredentials(String username, String password) {
        interactor.login(username, password, this);
    }

    @Override
    public void onUsernameEmptyError() {

    }

    @Override
    public void onPasswordEmptyError() {

    }

    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void onLoginFail() {

    }

    @Override
    public void onRegisterSuccess() {

    }
}
