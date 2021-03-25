package com.example.moneyknowledge.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.moneyknowledge.R;

public class LogInActivity extends AppCompatActivity {
    private EditText email, password;
    Switch remember;
    TextView forgotPassword;
    Button btnConnect, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        initComponents();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }

    private void initComponents() {
        email = findViewById(R.id.tietEmail);
        password = findViewById(R.id.tietPassword);
        remember = findViewById(R.id.rememberMeSwitch);
        forgotPassword = findViewById(R.id.forgotPassword);
        btnConnect = findViewById(R.id.btnLogIn);
        btnRegister = findViewById(R.id.btnRegister);
    }
}