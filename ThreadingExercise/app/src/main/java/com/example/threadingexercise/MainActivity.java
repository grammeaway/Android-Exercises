package com.example.threadingexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.example.threadingexercise.api.JokeService;
import com.example.threadingexercise.api.NorrisQuote;
import com.example.threadingexercise.api.RetrofitClient;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    volatile boolean running = true;
    private Thread t;
    private JokeService jokeClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);

        jokeClient = RetrofitClient.getInstance().create(JokeService.class);

        t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(running) {

                    final String displayedString = getChuckNorrisString();
                    //Uncomment below and comment above to change display from Chuck Norris joke to random string
                    //final String displayedString = generateRandomString(30);
                    tv.post(() -> {
                        tv.setText(displayedString);
                    });

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

    //Thank you Greg
    private String generateRandomString(int bound) {
        Random r = new Random();
        StringBuilder builder = new StringBuilder();

        char newChar;
        for(int i = 0; i < bound; i++) {
            newChar = (char) (r.nextInt(96) + 32);
            builder.append(newChar);
        }

        return builder.toString();
    }

    //Thank you Greg
    @Override
    protected void onDestroy() {
        running = false;
        super.onDestroy();
        try {
            t.join();
        }
        catch(InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private String getChuckNorrisString() {
        Call<NorrisQuote> joke = jokeClient.getJoke();
        String jokeAsText = "Error";

        try {
            System.out.println("############### Hello from the try");
            NorrisQuote fetchedJoke = joke.execute().body();
            System.out.println("Fetched joke: " + fetchedJoke);
            jokeAsText = fetchedJoke.getValue().getJoke();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }

        return jokeAsText;
    }
}
