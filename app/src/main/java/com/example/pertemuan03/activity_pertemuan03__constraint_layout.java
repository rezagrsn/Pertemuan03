package com.example.pertemuan03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class activity_pertemuan03__constraint_layout extends AppCompatActivity {
    private Button btnAbout;
    private Switch wifiSwitch;
    private WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pertemuan03__constraint_layout);
        String username = getIntent().getStringExtra("Username");

        TextView tv = findViewById(R.id.txtWelcome);
        tv.setText(username);

        wifiSwitch = findViewById(R.id.txtState);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        wifiSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    wifiManager.setWifiEnabled(true);
                    wifiSwitch.setText("WiFi is ON");
                } else {
                    wifiManager.setWifiEnabled(false);
                    wifiSwitch.setText("WiFi is OFF");
                }
            }
        });

        if (wifiManager.isWifiEnabled()) {
            wifiSwitch.setChecked(true);
            wifiSwitch.setText("WiFi is ON");
        } else {
            wifiSwitch.setChecked(false);
            wifiSwitch.setText("WiFi is OFF");
        }

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
            fragment1 = new fragment_simple();
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
            fragment2 = new fragment_simple_2();
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mainFrag, fragment2, "fragTwo");
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
