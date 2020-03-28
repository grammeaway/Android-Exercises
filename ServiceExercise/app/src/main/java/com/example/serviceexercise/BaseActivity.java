package com.example.serviceexercise;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    TextView jokeTV;
    TextView tvJokeCounter;

    protected JokeAndroidService jokeAndroidService;
    protected boolean mBound;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, JokeAndroidService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    protected void initViews(){
        jokeTV = findViewById(R.id.jokeView);
        tvJokeCounter = findViewById(R.id.jokeCounter);

        findViewById(R.id.updateCounterButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBound){
                    tvJokeCounter.setText(String.valueOf(jokeAndroidService.getJokeCounter()));
                }
            }
        });
        findViewById(R.id.updateJokeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBound){
                    jokeTV.setText(jokeAndroidService.getNewestJoke());
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            Log.i("service_exercise",  "ServiceConnection onServiceConnected- Current Thread ID- " + Thread.currentThread().getId() + " For Thread- " + Thread.currentThread().getName());

            // We've bound to LocalService, cast the IBinder and get LocalService instance
            JokeAndroidService.JokeAndroidServiceBinder binder = (JokeAndroidService.JokeAndroidServiceBinder) service;
            jokeAndroidService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };


    public void updateCounterClicked(){
        if(mBound){
            tvJokeCounter.setText(String.valueOf(jokeAndroidService.getJokeCounter()));
        }
    }

    public void updateJokeClicked(){
        if(mBound){
            jokeTV.setText(jokeAndroidService.getNewestJoke());
        }
    }


}
