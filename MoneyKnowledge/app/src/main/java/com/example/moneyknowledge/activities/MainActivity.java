package com.example.moneyknowledge.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.moneyknowledge.R;
import com.example.moneyknowledge.activities.register.LogInActivity;

public class MainActivity extends AppCompatActivity {

    Button btnConnect;
    ImageView logo;
    Animation topAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initComponents() {
        logo = findViewById(R.id.logo);
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        logo.setAnimation(topAnimation);
        btnConnect = findViewById(R.id.btn_connect);
    }
}