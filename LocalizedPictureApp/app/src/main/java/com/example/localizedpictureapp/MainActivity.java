package com.example.localizedpictureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private int[] imgPaths = {
            R.drawable.himouto_umaru_chan_telegram_clip_art_others,
            R.drawable.d9u31u1_2b850384_46b2_471d_935f_f9c25a4f2978};

    private ImageView imgView;

    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgView = findViewById(R.id.imageView3);

        index = 0;
        imgView.setImageResource(imgPaths[index]);

    }


    public void nextButtonOnClick(View view) {
        index++;
        if(index >= imgPaths.length) {
            index = 0;
        }
        imgView.setImageResource(imgPaths[index]);
    }

    public void previousButtonOnClick(View view) {
        index--;
        if(index < 0) {
            index = imgPaths.length -1;
        }
        imgView.setImageResource(imgPaths[index]);

    }
}
