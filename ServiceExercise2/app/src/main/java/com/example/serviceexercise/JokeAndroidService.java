package com.example.serviceexercise;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.serviceexercise.api.JokeService;
import com.example.serviceexercise.api.NorrisQuote;
import com.example.serviceexercise.api.RetrofitClient;

import java.io.IOException;

import retrofit2.Call;


/**
 * Thank you Greg
 * https://github.com/bongo1986/sdu_android_programming/blob/master/ServiceExercise_2/app/src/main/java/com/programming/android/sdu/serviceexercise/JokeAndroidService.java
 */
public class JokeAndroidService extends Service {

    public static final String NOTIFICATION = "com.example.serviceexercise.receiver";
    public static final String JOKE_TEXT = "JOKE_TEXT_KEY";
    public static final String JOKE_COUNTER = "JOKE_COUNTER_KEY";


    private Thread t;
    private JokeService jokeClient;
    private volatile boolean running;
    private long jokeCounter;
    private String newestJoke;


    @Override
    public void onCreate() {
        super.onCreate();
        jokeCounter = 0;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        jokeClient = RetrofitClient.getInstance().create(JokeService.class);

        if (!running) {
            running = true;
                t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (running) {
                            newestJoke = getChuckNorrisString();
                            publishResults(newestJoke,++jokeCounter);
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });

            t.start();
        }
        return START_STICKY;
    }

    private void publishResults(String jokeText, long jokeCounter) {
        Intent intent = new Intent(NOTIFICATION);
        intent.putExtra(JOKE_TEXT, jokeText);
        intent.putExtra(JOKE_COUNTER, jokeCounter);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }


    private String getChuckNorrisString() {
        Call<NorrisQuote> joke = jokeClient.getJoke();
        String jokeAsText = "Error";

        try {
            NorrisQuote fetchedJoke = joke.execute().body();
            jokeAsText = fetchedJoke.getValue().getJoke();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }

        return jokeAsText;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onDestroy() {
        running = false;
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}
