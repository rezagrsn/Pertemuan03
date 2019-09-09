package com.example.pertemuan03;

import androidx.appcompat.app.AppCompatActivity;

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
}
