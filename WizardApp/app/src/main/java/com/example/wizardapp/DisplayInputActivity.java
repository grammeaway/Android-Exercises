package com.example.wizardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.wizardapp.util.Constants;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DisplayInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(Constants.TAG, "Display onCreate!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_input);


        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String address = intent.getStringExtra(Constants.ADDRESS);
        String name = intent.getStringExtra(Constants.NAME);
        long birthday = intent.getLongExtra(Constants.BIRTHDAY,0);


        // Capture the layout's TextView and set the string as its text
        TextView addressText = findViewById(R.id.addressTextView);
        addressText.setText(address);

        TextView nameText = findViewById(R.id.nameTextView);
        nameText.setText(name);

        TextView birthText = findViewById(R.id.bithdayTextView);
        birthText.setText(getDateAsString(birthday));


    }
    @Override
    protected void onStart() {
        Log.i(Constants.TAG, "Display onStart!");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i(Constants.TAG, "Display onStop!");
        super.onStop();
    }

    @Override
    protected void onPause() {
        Log.i(Constants.TAG, "Display onPause!");
        super.onPause();

    }

    @Override
    protected void onResume() {
        Log.i(Constants.TAG, "Display onResume!");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.i(Constants.TAG, "Display onDestroy!");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.i(Constants.TAG, "Display onRestart!");
        super.onRestart();
    }





    private String getDateAsString(long date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return formatter.format(date);
    }
}
