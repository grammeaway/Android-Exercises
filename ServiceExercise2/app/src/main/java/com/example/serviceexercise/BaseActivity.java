package com.example.serviceexercise;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class BaseActivity extends AppCompatActivity {
    TextView jokeTV;
    TextView tvJokeCounter;


    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                String joke = bundle.getString(JokeAndroidService.JOKE_TEXT);
                long jokeCounter = bundle.getLong(JokeAndroidService.JOKE_COUNTER);
                jokeTV.setText(joke);
                tvJokeCounter.setText(String.valueOf(jokeCounter));
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, JokeAndroidService.class);
        startService(intent);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter(
                JokeAndroidService.NOTIFICATION));
    }

    protected void initViews(){
        jokeTV = findViewById(R.id.jokeView);
        tvJokeCounter = findViewById(R.id.jokeCounter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

}
