package com.example.moneyknowledge.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneyknowledge.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity {
    public static final String SHARED_PREF_USER = "userSharedPred";
    public static final String SAVED = "saved";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";

    private EditText email, password;
    private TextView tvForgotPass;
    private Button btnConnect;
    private FirebaseAuth mAuth;
    private Switch switchBtn;
    private SharedPreferences preferences;

    //Implement Google and Facebook auth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        initComponents();
        mAuth = FirebaseAuth.getInstance();

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()) {
                    saveUserToSharePref();
                    String Email = email.getText().toString().trim();
                    String Password = password.getText().toString().trim();
                    mAuth.signInWithEmailAndPassword(Email, Password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        finish();
                                        Intent intent = new Intent(getApplicationContext(), LessonsActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(LogInActivity.this, R.string.auth_failed,
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }
        });

        tvForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initComponents() {
        tvForgotPass = findViewById(R.id.forgotPassword);
        email = findViewById(R.id.tietEmail);
        password = findViewById(R.id.tietPassword);
        btnConnect = findViewById(R.id.btnLogIn);
        switchBtn=findViewById(R.id.rememberMeSwitch);
        preferences=getSharedPreferences(SHARED_PREF_USER, MODE_PRIVATE);
        getUserDetailsFromSharedPref();
    }

    private boolean validate() {
        if(email == null || email.getText().toString().trim().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()) {
            email.setError(getString(R.string.err_email));
            return false;
        }
        if(password == null || password.getText().toString().trim().isEmpty() || password.getText().toString().trim().length()<8) {
            password.setError(getString(R.string.err_parola));
            return false;
        }

        return true;
    }

    private void saveUserToSharePref(){
        SharedPreferences.Editor editor = preferences.edit();
        if(switchBtn.isChecked()){
            editor.putBoolean(SAVED, true);
            String Email = email.getText().toString().trim();
            String Password = password.getText().toString().trim();
            editor.putString(EMAIL, Email);
            editor.putString(PASSWORD,Password);
            editor.apply();
        }else{
            editor.clear().apply();
        }
    }
    private void getUserDetailsFromSharedPref(){
        switchBtn.setChecked(preferences.getBoolean(SAVED,false));
        email.setText(preferences.getString(EMAIL,""));
        password.setText(preferences.getString(PASSWORD,""));
    }
}