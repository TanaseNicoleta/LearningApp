package com.example.moneyknowledge.activities.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moneyknowledge.R;
import com.example.moneyknowledge.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private EditText name, email, phone, birthDate, password;
    private Button btnRegister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initComponents();

        final DatabaseReference database = FirebaseDatabase.getInstance().getReference("users");
        mAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    String id = null;
                    final String Name = name.getText().toString().trim();
                    final String Email = email.getText().toString().trim();
                    final String Phone = phone.getText().toString().trim();
                    final String Password = password.getText().toString().trim();
                    final String BirthDate = birthDate.getText().toString().trim();

                    mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                User user = new User(null, Name, Email, Phone, BirthDate, Password);
                                database.child(mAuth.getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()) {
                                            Toast.makeText(RegisterActivity.this, R.string.msj_welcome, Toast.LENGTH_SHORT).show();
                                            finish();
                                        } else
                                            Toast.makeText(RegisterActivity.this, R.string.msj_error, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    });


                } else
                    Toast.makeText(RegisterActivity.this, R.string.msj_error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initComponents() {
        name = findViewById(R.id.tietName);
        email = findViewById(R.id.tietEmail);
        phone = findViewById(R.id.tietPhone);
        birthDate = findViewById(R.id.tietBirthDate);
        password = findViewById(R.id.tietPassword);
        btnRegister = findViewById(R.id.btnRegister);
    }

    private boolean validate() {
        if(name == null || name.getText().toString().trim().isEmpty()) {
            name.setError(getString(R.string.err_name));
            return false;
        }
        
        if(phone == null || phone.getText().toString().trim().isEmpty() || !Patterns.PHONE.matcher(phone.getText()).matches()) {
            phone.setError(getString(R.string.err_telefon));
            return false;
        }
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
}