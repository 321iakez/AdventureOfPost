package com.group0562.adventureofpost.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.group0562.adventureofpost.R;
import com.group0562.adventureofpost.presenters.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainPresenter.view {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
