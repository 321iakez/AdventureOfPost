package com.group0562.adventureofpost;

import android.os.Bundle;
import android.view.View;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    //TODO I dont know if this is correct
    public final static String EXTRA_MESSAGE = "com.group0562.AdventureOfPost.MESSAGE";
    EditText userName, passWord;
    Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register = (Button)findViewById(R.id.button2);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                onClickRegisterNew(v);
            }
        });
    }

    public void onClickLogin(View view){
        Intent intent = new Intent(this, GameActivity.class);
        //TODO I don't know if these lines are required
        //EditText editText = (EditText) findViewById(R.id.editText);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void onClickRegisterNew(View view){
        Intent intent = new Intent(this, RegisterAccount.class);
        //TODO I don't know if these lines are required
        //EditText editText = (EditText) findViewById(R.id.editText);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
