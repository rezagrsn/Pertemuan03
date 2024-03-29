package com.example.pertemuan03;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Timer;
import java.util.TimerTask;

public class activity_pertemuan03__constraint_layout extends AppCompatActivity {
    private Button btnAbout;
    private TextView txtUsername;
    private WifiManager WifiManager;
    private static final String TAG = "MainActivity";
    public static final long INTERVAL = 3000;
    private Handler mHandler = new Handler();
    private Timer mTimer = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pertemuan03__constraint_layout);

        txtUsername = findViewById((R.id.txtUsername));
        btnAbout = findViewById(R.id.btnAbout);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityRelativeLayout();
            }
        });

        WifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if(getIntent().getExtras()!=null){
            Bundle bundle = getIntent().getExtras();
            txtUsername.setText(bundle.getString("infoUsername"));
        }else{
            txtUsername.setText(getIntent().getStringExtra("infoUsername"));
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(android.net.wifi.WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(wifiStateReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(wifiStateReceiver);
    }

    private BroadcastReceiver wifiStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int wifiStateExtra = intent.getIntExtra(android.net.wifi.WifiManager.EXTRA_WIFI_STATE, android.net.wifi.WifiManager.WIFI_STATE_UNKNOWN);

            switch (wifiStateExtra){
                case android.net.wifi.WifiManager.WIFI_STATE_ENABLED:
                    setNotification(context, "Connected");
                    break;
                case android.net.wifi.WifiManager.WIFI_STATE_DISABLED:
                    setNotification(context, "Disconnected");
                    break;
            }
        }
    };

    @SuppressWarnings("deprecation")
    public void setNotification(Context context, String txt){
        NotificationCompat.Builder builder;
        builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.ic_priority_high_black_24dp);
        builder.setContentTitle("Notification!");
        builder.setContentText(txt);
        builder.setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        assert notificationManager != null;
        notificationManager.notify(0, builder.build());
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void scheduleJob(View v) {
        @SuppressLint("JobSchedulerService") ComponentName componentName = new ComponentName(this, MyJobService.class);
        Builder builder = new Builder(123, componentName);
        builder.setPersisted(true);
        builder.setPeriodic(15 * 60 * 1000);
        JobInfo info;
        info = builder
                .build();

        JobScheduler scheduler;
        scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        assert scheduler != null;
        int resultCode;
        resultCode = scheduler.schedule(info);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job scheduled");
        } else {
            Log.d(TAG, "Job scheduling failed");
        }
        if (mTimer!=null) {
            mTimer.cancel();
        }
        else
            mTimer = new Timer();

        mTimer.scheduleAtFixedRate(new TimeDisplayToast(),0,INTERVAL);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void cancelJob(View v) {
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        assert scheduler != null;
        scheduler.cancel(123);
        Log.d(TAG, "Job cancelled");
    }

    private class TimeDisplayToast extends TimerTask {

        @Override
        public void run() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),"Ini Toast setiap 3 detik!", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
