package com.group0562.adventureofpost.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.group0562.adventureofpost.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);
        findViewById(R.id.registerButton).setOnClickListener(this::registerOnClick);
    }

    public void registerOnClick(View view) {
        // Database instance
        DatabaseHelper db = new DatabaseHelper(this);

        // Acquire user inputs
        String username = ((EditText) findViewById(R.id.username)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();
        String confirmPassword = ((EditText) findViewById(R.id.cPass)).getText().toString();

        // Verify inputs and store them in database
        if (username.equals("") || password.equals("") || confirmPassword.equals("")) {
            Toast.makeText(getApplicationContext(), "Fields are Empty", Toast.LENGTH_SHORT).show();
        }

        // Check whether both password inputs match
        else if (password.equals(confirmPassword)) {
            if (db.checkUsernameDup(username)) {
                if (db.insert(username, password)) {
                    Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Username Already Exists", Toast.LENGTH_SHORT).show();
            }
        }

        else {
            Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_SHORT).show();
        }
    }
}