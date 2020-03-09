package com.example.fragmentexercise1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;

import com.example.fragmentexercise1.fragments.BlueFragment;
import com.example.fragmentexercise1.fragments.GreenFragment;
import com.example.fragmentexercise1.fragments.RedFragment;
import com.example.fragmentexercise1.fragments.WhiteFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFrame, new WhiteFragment()).addToBackStack(null).commit();
        }


    }

    public void blueOnClick(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFrame, new BlueFragment()).addToBackStack(null).commit();

    }

    public void redOnClick(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFrame, new RedFragment()).addToBackStack(null).commit();

    }

    public void greenOnClick(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFrame, new GreenFragment()).addToBackStack(null).commit();

    }

    public void whiteOnClick(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFrame, new WhiteFragment()).addToBackStack(null).commit();

    }
}
