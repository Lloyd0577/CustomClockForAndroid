package com.example.administrator.timeviewdemo;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    private TimeView time_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time_view = (TimeView)findViewById(R.id.time_view);
        time_view.setTime(9,35,43);
        time_view.start();
    }
}
