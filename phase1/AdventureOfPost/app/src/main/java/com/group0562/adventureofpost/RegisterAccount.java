package com.group0562.adventureofpost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterAccount extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);
    }

    public void onClickRegister(View view) {
        String username, password, securityQuestion, answer;
        EditText txtUsername = findViewById(R.id.username);
        username = txtUsername.getText().toString();
        EditText txtPassword = findViewById(R.id.password);
        password = txtPassword.getText().toString();
        EditText txtSecurityQ = findViewById(R.id.securityQuestion);
        securityQuestion = txtSecurityQ.getText().toString();
        EditText txtAnswer = findViewById(R.id.answer);
        answer = txtAnswer.getText().toString();


        //TODO this is just to check should be removed soon
        System.out.println("Account Username: " + username);
        System.out.println("Account Password: " + password);
        System.out.println("Account Security Question: " + securityQuestion);
        System.out.println("Account Answer: " + answer);

        //TODO Uncomment lines to have register button take user back to login screen
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);


    }

}
