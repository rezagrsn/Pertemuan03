package com.example.pertemuan03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ActivityDatabase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_activity);

        new FragmentDatabase();

        setContentView(R.layout.fragment_database);

    }
}
