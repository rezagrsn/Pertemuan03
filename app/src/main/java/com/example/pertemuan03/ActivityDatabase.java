package com.example.pertemuan03;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityDatabase extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_activity);

        setContentView(R.layout.fragment_database);

    }
}
