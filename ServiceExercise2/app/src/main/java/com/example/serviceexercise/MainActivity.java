package com.example.serviceexercise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    public void onNextButtonClicked(View v) {
        Intent i = new Intent(getBaseContext(), SecondActivity.class);
        startActivity(i);
    }

}