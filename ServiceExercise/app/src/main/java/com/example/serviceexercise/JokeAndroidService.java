package com.example.serviceexercise;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.serviceexercise.api.JokeService;
import com.example.serviceexercise.api.NorrisQuote;
import com.example.serviceexercise.api.RetrofitClient;

import java.io.IOException;

import retrofit2.Call;


/**
 * Thank you Greg
 * https://github.com/bongo1986/sdu_android_programming/blob/master/ServiceExercise_1/app/src/main/java/com/programming/android/sdu/serviceexercise/JokeAndroidService.java
 */
public class JokeAndroidService extends Service {


    private Thread t;
    private JokeService jokeClient;
    private volatile boolean running;
    private long jokeCounter;
    private String newestJoke;
    // Binder given to clients
    private final IBinder binder = new JokeAndroidServiceBinder();

    @Override
    public void onCreate() {

        jokeClient = RetrofitClient.getInstance().create(JokeService.class);

        System.out.println("ON CREATE SERVICE, CLIENT: " + jokeClient);

        running = true;
        jokeCounter = 0;

        t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(running) {
                    newestJoke = getChuckNorrisString();
                    jokeCounter++;
                    try {
                        Thread.sleep(5000);
                    }
                    catch(InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        t.start();
    }

    public String getNewestJoke(){
        System.out.println("##### Service getNewestJoke called: " + newestJoke);
        return newestJoke;
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

    public Long getJokeCounter(){
        return jokeCounter;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class JokeAndroidServiceBinder extends Binder {
        JokeAndroidService getService() {
            // Return this instance of LocalService so clients can call public methods
            return JokeAndroidService.this;
        }
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
