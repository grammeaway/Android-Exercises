package com.example.serviceexercise;

import android.os.Bundle;
import android.view.View;

public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        initViews();
    }

    public void onBackButtonClicked(View v) {
        finish();
    }

}
