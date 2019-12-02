package com.group0562.adventureofpost.auth.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.group0562.adventureofpost.R;
import com.group0562.adventureofpost.auth.AuthInteractor;
import com.group0562.adventureofpost.auth.AuthPresenter;
import com.group0562.adventureofpost.auth.AuthView;

public class RegisterActivity extends AppCompatActivity implements AuthView {

    /**
     * The username of the player logging in.
     */
    private EditText username;

    /**
     * The password of the player logging in.
     */
    private EditText password;

    /**
     * Confirmation of the password.
     */
    private EditText confPassword;

    /**
     * The presenter that controls this view.
     */
    private AuthPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confPassword = findViewById(R.id.cPass);
        findViewById(R.id.registerButton).setOnClickListener(this::registerOnClick);

        presenter = new AuthPresenter(this, new AuthInteractor());
    }

    public void registerOnClick(View view) {
        presenter.validateRegister(username.getText().toString(), password.getText().toString(), confPassword.getText().toString());
    }

    @Override
    public void setUsernameError() {
        username.setError(getString(R.string.username_error));
    }

    @Override
    public void setPasswordError() {
        password.setError(getString(R.string.reg_password_error));
    }

    @Override
    public void onSuccess() {
        Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFail() {
        Toast.makeText(getApplicationContext(), "Username Already Exists", Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }
}