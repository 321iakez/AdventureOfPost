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
        db = new DatabaseHelper(this);
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
                if (newUser.equals("") || newPass.equals("") || cPass.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fields are Empty", Toast.LENGTH_SHORT).show();
                } else {
                    if (newPass.equals(cPass)) {
                        Boolean checkmail = db.checkMail(newUser);
                        if (checkmail) {
                            Boolean insert = db.insert(newUser, newPass);
                            if (insert) {
                                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterAccount.this, MainActivity.class);
                                startActivity(intent);
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Email Already Exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });
    }
}