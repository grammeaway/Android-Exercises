package com.example.contentproviderexercise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private RecyclerView contactRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ContactsRetriever contactsRetriever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactRecyclerView = findViewById(R.id.contactList);
        layoutManager = new LinearLayoutManager(this);
        contactRecyclerView.setLayoutManager(layoutManager);
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
        stopContactsRetriever();
        contactsRetriever = new ContactsRetriever();
        contactsRetriever.execute();
    }

    private void stopContactsRetriever(){
        if(contactsRetriever != null){
            contactsRetriever.cancel(false);
        }
    }

    class ContactsRetriever extends AsyncTask<Void, Void, List<ContactModel>> {
        @Override
        protected List<ContactModel> doInBackground(Void... voids) {
            List<ContactModel> contactLst  = new ArrayList<>();
            Cursor mCursor;
            ContentResolver cr = getContentResolver();

            String[] mProjection =
                    {
                            ContactsContract.Contacts._ID,
                            ContactsContract.Contacts.LOOKUP_KEY,
                            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
                    };

            Uri uri = ContactsContract.Contacts.CONTENT_URI;


            mCursor = cr.query(uri, mProjection, null, null, null);




            while (mCursor.moveToNext()){
                ContactModel person = new ContactModel();
                person.setId(mCursor.getString(0));
                person.setLookupKey(mCursor.getString(1));
                person.setName(mCursor.getString(2));
                contactLst.add(person);
            }

            return contactLst;
        }

        @Override
        protected void onPostExecute(List<ContactModel> contactPeople) {
            contactRecyclerView.setAdapter(new ContactAdapter(contactPeople));
        }
    }




}
