package com.example.pertemuan03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button LoginBtn;
    private EditText Email;
    private EditText Pass;
    String email_pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String emailKey = "emailKey";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email = findViewById(R.id.Email);
        Pass = findViewById(R.id.Pass);
        LoginBtn = findViewById(R.id.LoginBtn);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        System.out.println("avaiable LOGIN Email : "+sharedpreferences.getString(emailKey, new String()));

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Email.getText().toString().trim().matches(email_pattern)){
                    if (Pass.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(),"Password is empty !",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(emailKey, Email.getText().toString());
                        editor.commit();
                        openActivityConstraintLayout();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Incorrect email and password !",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void openActivityConstraintLayout() {
        Bundle bundle = new Bundle();
        bundle.putString("infoUsername", Email.getText().toString());
        Intent intent = new Intent(this, activity_pertemuan03__constraint_layout.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
