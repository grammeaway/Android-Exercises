package com.example.fragmentexercise2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.fragmentexercise2.fragments.LyricsFragment;
import com.example.fragmentexercise2.fragments.TitlesFragment;

public class MainActivity extends AppCompatActivity implements TitlesFragment.OnTitleSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_titles);

        if(findViewById(R.id.fragment_container) != null) {
            if(savedInstanceState != null) {
                return;
            }

            TitlesFragment titleFragment = new TitlesFragment();

            titleFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, titleFragment).commit();
        }
    }

    @Override
    public void onSongSelected(int position) {
        LyricsFragment lyricsFragment = (LyricsFragment) getSupportFragmentManager().findFragmentById(R.id.lyrics_fragment);

        if(lyricsFragment != null) {
            lyricsFragment.updateLyricsView(position);
        }

        else {
            LyricsFragment newFragment = new LyricsFragment();
            Bundle args = new Bundle();
            args.putInt(LyricsFragment.ARG_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
