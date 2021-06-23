package com.example.moneyknowledge.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moneyknowledge.R;
import com.example.moneyknowledge.activities.register.LogInActivity;

public class MainActivity extends AppCompatActivity {

    Button btnConnect;
    ImageView logo;
    TextView tvMoney, tvKnowledge;
    Animation topAnimation, leftRight, rightLeft;

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
                finish();
            }
        });
    }

    private void initComponents() {
        logo = findViewById(R.id.logo);
        tvMoney = findViewById(R.id.app_name);
        tvKnowledge = findViewById(R.id.app_name2);
        btnConnect = findViewById(R.id.btn_connect);

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        logo.setAnimation(topAnimation);

        leftRight = AnimationUtils.loadAnimation(this, R.anim.left_right);
        tvMoney.setAnimation(leftRight);

        rightLeft = AnimationUtils.loadAnimation(this, R.anim.right_left);
        tvKnowledge.setAnimation(rightLeft);

    }
}