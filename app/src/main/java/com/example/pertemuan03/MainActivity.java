package com.example.pertemuan03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button LoginBtn;
    private EditText Email;
    private EditText Pass;
    String email_pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email = findViewById(R.id.Email);
        Pass = findViewById(R.id.Pass);
        LoginBtn = findViewById(R.id.LoginBtn);

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Email.getText().toString().trim().matches(email_pattern)){
                    if (Pass.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(),"Password kosong",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        openActivityConstraintLayout();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Masukkan email and password!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void openActivityConstraintLayout() {
        Intent intent = new Intent(this, activity_pertemuan03__constraint_layout.class);
        startActivity(intent);
    }
}
