package com.example.wizardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;

import com.example.wizardapp.util.Constants;

import java.util.Calendar;

import static com.example.wizardapp.util.Constants.BIRTHDAY;
import static com.example.wizardapp.util.Constants.NAME;

/**
 * The calender iew doesn't work as intended, since it doesn't register when
 * the date is changed... CalenderView seems somewhat poorly designed
 */
public class DateInputActivity extends AppCompatActivity {
    private Intent intent;
    private CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(Constants.TAG, "Date onCreate!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_input);
        calendar = findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {

            }
        });
        intent = getIntent();
    }
    @Override
    protected void onStart() {
        Log.i(Constants.TAG, "Date onStart!");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i(Constants.TAG, "Date onStop!");
        super.onStop();
    }

    @Override
    protected void onPause() {
        Log.i(Constants.TAG, "Date onPause!");
        super.onPause();

    }

    @Override
    protected void onResume() {
        Log.i(Constants.TAG, "Date onResume!");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.i(Constants.TAG, "Date onDestroy!");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.i(Constants.TAG, "Date onRestart!");
        super.onRestart();
    }



    public void onNextButtonClicked(View view) {
        intent.setClass(this,AddressInputActivity.class);
        //Intent intent = new Intent(this, AddressInputActivity.class);
        long birthday = calendar.getDate();
        intent.putExtra(BIRTHDAY, birthday);
        startActivity(intent);
    }
}

