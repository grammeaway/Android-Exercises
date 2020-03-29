package com.example.contentproviderexercise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private ListView contactListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactListView = findViewById(R.id.contactList);
        showContacts();
    }

    private void showContacts(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
        checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        }
        else {
            loadContacts();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode ==  PERMISSIONS_REQUEST_READ_CONTACTS) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showContacts();
            }
            else {
                Toast.makeText(this, "Cannot display contacts without permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void loadContacts() {
        String[] mProjection =
                {
                        ContactsContract.Contacts._ID,
                        ContactsContract.Contacts.LOOKUP_KEY,
                        ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
                };
        Cursor mCursor = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                mProjection,
                null, null, null);

        System.out.println("#### The cursor actually returns: " + mCursor);
        SimpleCursorAdapter ca = new SimpleCursorAdapter(this, R.layout.contact_view, mCursor, mProjection,
                new int[] {R.id.idTV, R.id.lookupKeyTV, R.id.nameTV}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        contactListView.setAdapter(ca);
    }




}
