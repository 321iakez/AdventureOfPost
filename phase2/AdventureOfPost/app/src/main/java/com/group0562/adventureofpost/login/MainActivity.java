package com.group0562.adventureofpost.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.group0562.adventureofpost.GameActivity;
import com.group0562.adventureofpost.R;


public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button register, login;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        register = findViewById(R.id.register);
        register.setOnClickListener(this::onClickRegister);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        login.setOnClickListener(this::onClickLogin);
    }

    public void onClickLogin(View view) {
        String email = username.getText().toString();
        String pass = password.getText().toString();
        if (db.emailpassword(email, pass)) {
            Toast.makeText(getApplicationContext(), "successfully login", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Wrong email or password", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
