package com.group0562.adventureofpost.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.group0562.adventureofpost.GameActivity;
import com.group0562.adventureofpost.R;


public class MainActivity extends AppCompatActivity {

//    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.register).setOnClickListener(this::onClickRegister);
        findViewById(R.id.login).setOnClickListener(this::onClickLogin);
    }

    public void onClickLogin(View view) {
        // Database instance
        DatabaseHelper db = new DatabaseHelper(this);

        // Acquire user inputs
        String username = ((EditText) findViewById(R.id.username)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();

        // Verify credentials
        if (db.verify(username, password)) {
            Toast.makeText(getApplicationContext(), "successfully login", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Wrong username or password", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
