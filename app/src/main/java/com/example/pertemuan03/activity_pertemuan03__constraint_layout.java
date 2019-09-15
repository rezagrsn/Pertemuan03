package com.example.pertemuan03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_pertemuan03__constraint_layout extends AppCompatActivity {
    private Button btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pertemuan03__constraint_layout);

        btnAbout = findViewById(R.id.btnAbout);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityRelativeLayout();
            }
        });
    }

    public void openActivityRelativeLayout(){
        Intent intent = new Intent(this, activity_pertemuan03__relative_layout.class);
        startActivity(intent);

    }

    public void loadFragmentOne(View v) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment1 = fragmentManager.findFragmentByTag("fragOne");

        if (fragment1 == null) {
            fragment1 = new FragmentOne();
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mainFrag, fragment1, "fragOne");
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void loadFragmentTwo(View v) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment2 = fragmentManager.findFragmentByTag("fragTwo");

        if (fragment2 == null) {
            fragment2 = new FragmentTwo();
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mainFrag, fragment2, "fragTwo");
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
