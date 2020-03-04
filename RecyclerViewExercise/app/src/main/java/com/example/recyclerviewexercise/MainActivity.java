package com.example.recyclerviewexercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Integer> randomInts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.intList);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        randomInts = new ArrayList<>();
        generateInts(1000, 10000);
        adapter = new CustomAdapter(this.randomInts);
        recyclerView.setAdapter(adapter);
    }

    private void generateInts(int amount, int bound) {
        Random r = new Random();
        for(int i = 0; i < amount; i++) {
            randomInts.add(r.nextInt(bound));
        }
    }

}
