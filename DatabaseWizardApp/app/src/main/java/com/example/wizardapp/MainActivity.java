package com.example.wizardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.wizardapp.data.AppDatabase;
import com.example.wizardapp.data.UserDTO;
import com.example.wizardapp.util.Constants;

import static com.example.wizardapp.util.Constants.NAME;

public class MainActivity extends AppCompatActivity {

    private AppDatabase db;

    public static UserDTO savedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(Constants.TAG, "Main onCreate!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = AppDatabase.getAppDatabase(this);
        savedUser = db.userDao().getUser();

        if(savedUser != null) {
            System.out.println("Should work");
            System.out.println(savedUser);
            initializeNameField(savedUser);
        }
    }


    private void initializeNameField(UserDTO user) {
        EditText editText = (EditText) findViewById(R.id.editText);
        editText.setText(user.name);
    }

    @Override
    protected void onStart() {
        Log.i(Constants.TAG, "Main onStart!");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i(Constants.TAG, "Main onStop!");
        super.onStop();
    }

    @Override
    protected void onPause() {
        Log.i(Constants.TAG, "Main onPause!");
        super.onPause();

    }

    @Override
    protected void onResume() {
        Log.i(Constants.TAG, "Main onResume!");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.i(Constants.TAG, "Main onDestroy!");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.i(Constants.TAG, "Main onRestart!");
        super.onRestart();
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(Constants.TAG, "Main onSaveInstanceState ");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(Constants.TAG, "Main onRestoreInstanceState ");
    }

    public void onNextButtonClicked(View view) {
        Intent intent = new Intent(this, DateInputActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String name = editText.getText().toString();
        intent.putExtra(NAME, name);
        startActivity(intent);
    }
}
