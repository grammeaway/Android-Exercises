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

import static com.example.wizardapp.util.Constants.ADDRESS;
import static com.example.wizardapp.util.Constants.BIRTHDAY;

public class AddressInputActivity extends AppCompatActivity {
    private Intent intent;

    private UserDTO savedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(Constants.TAG, "Address onCreate!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_input);
        intent = getIntent();

        savedUser = MainActivity.savedUser;

        if(savedUser != null) {
            initializeNameField(savedUser);
        }
    }


    private void initializeNameField(UserDTO user) {
        EditText editText = (EditText) findViewById(R.id.editText2);
        editText.setText(user.address);
    }



    @Override
    protected void onStart() {
        Log.i(Constants.TAG, "Address onStart!");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i(Constants.TAG, "Address onStop!");
        super.onStop();
    }

    @Override
    protected void onPause() {
        Log.i(Constants.TAG, "Address onPause!");
        super.onPause();

    }

    @Override
    protected void onResume() {
        Log.i(Constants.TAG, "Address onResume!");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.i(Constants.TAG, "Address onDestroy!");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.i(Constants.TAG, "Address onRestart!");
        super.onRestart();
    }

    public void onNextButtonClicked(View view) {
        intent.setClass(this,DisplayInputActivity.class);
        //Intent intent = new Intent(this, DisplayInputActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText2);
        String address = editText.getText().toString();
        intent.putExtra(ADDRESS, address);
        startActivity(intent);
    }
}
