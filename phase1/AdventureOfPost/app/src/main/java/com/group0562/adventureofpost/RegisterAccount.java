package com.group0562.adventureofpost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.group0562.adventureofpost.trivia.DatabaseHelper;

public class RegisterAccount extends AppCompatActivity {
    EditText registerUser, registerPass, registercPass;
    Button registerButton;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        registerUser = findViewById(R.id.username);
        registerPass = findViewById(R.id.password);
        registerButton = findViewById(R.id.registerButton);
        registercPass = findViewById(R.id.cPass);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUser = registerUser.getText().toString();
                String newPass = registerPass.getText().toString();
                String cPass = registercPass.getText().toString();
                if(newUser.equals("")||newPass.equals("")||cPass.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields are Empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(newPass.equals(cPass)){
                        Boolean checkmail = db.checkMail(newUser);
                        if(checkmail){
                            Boolean insert = db.insert(newUser, newPass);
                            if(insert == true){
                                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Email Already Exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });
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
