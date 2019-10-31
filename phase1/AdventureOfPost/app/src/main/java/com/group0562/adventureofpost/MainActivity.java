package com.group0562.adventureofpost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    //TODO I dont know if this is correct
    public final static String EXTRA_MESSAGE = "com.group0562.AdventureOfPost.MESSAGE";
    EditText userName, passWord;
    Button register, login;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        register = findViewById(R.id.button2);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRegisterNew(v);
            }
        });
        userName = findViewById(R.id.editText2);
        passWord = findViewById(R.id.editText);
        login = findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = userName.getText().toString();
                String pass = passWord.getText().toString();
                Boolean checkemailpassword = db.emailpassword(email, pass);
                if (checkemailpassword) {
                    Toast.makeText(getApplicationContext(), "successfully login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onClickLogin(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        //TODO I don't know if these lines are required
        //EditText editText = (EditText) findViewById(R.id.editText);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void onClickRegisterNew(View view) {
        Intent intent = new Intent(this, RegisterAccount.class);
        //TODO I don't know if these lines are required
        //EditText editText = (EditText) findViewById(R.id.editText);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
