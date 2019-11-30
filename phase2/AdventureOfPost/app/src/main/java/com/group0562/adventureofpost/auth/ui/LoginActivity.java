package com.group0562.adventureofpost.auth.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.group0562.adventureofpost.GameActivity;
import com.group0562.adventureofpost.R;
import com.group0562.adventureofpost.auth.AuthInteractor;
import com.group0562.adventureofpost.auth.AuthPresenter;
import com.group0562.adventureofpost.auth.AuthView;

public class LoginActivity extends AppCompatActivity implements AuthView {

    private EditText username;
    private EditText password;
    private AuthPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        findViewById(R.id.register).setOnClickListener(this::onClickRegister);
        findViewById(R.id.login).setOnClickListener(this::onClickLogin);

        presenter = new AuthPresenter(this, new AuthInteractor());
    }

    public void onClickLogin(View view) {
        presenter.validateCredentials(username.getText().toString(), password.getText().toString());
    }

    public void onClickRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void setUsernameError() {
        username.setError(getString(R.string.username_error));
    }

    @Override
    public void setPasswordError() {
        password.setError(getString(R.string.password_error));
    }

    @Override
    public void onSuccess() {
        Toast.makeText(getApplicationContext(), "successfully login", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("username", username.getText());
        startActivity(intent);
    }

    @Override
    public void onFail() {
        Toast.makeText(getApplicationContext(), "Wrong username or password", Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
