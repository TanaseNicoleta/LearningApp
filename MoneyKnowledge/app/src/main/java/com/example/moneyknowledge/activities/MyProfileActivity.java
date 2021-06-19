package com.example.moneyknowledge.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneyknowledge.R;
import com.example.moneyknowledge.activities.dialogs.UpdateUserProfileDialog;
import com.example.moneyknowledge.model.User;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navView;
    Toolbar toolbar;
    private TextView tvNume, tvBirthDate, tvEmail, tvTelefon;
    private Button updateUser;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        intent=getIntent();
        initMenuComponents();
        initComponents();

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("users");
        final String userId = user.getUid();

        database.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User profile = snapshot.getValue(User.class);
                if(profile != null) {
                    tvNume.setText(profile.getName());
                    tvBirthDate.setText(profile.getBirthDate());
                    tvEmail.setText(profile.getEmail());
                    tvTelefon.setText(profile.getPhone());
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MyProfileActivity.this, R.string.msj_error, Toast.LENGTH_SHORT).show();
            }
        });

        updateUser.setOnClickListener(openUpdateProfileDialog());

    }

    private AdapterView.OnClickListener openUpdateProfileDialog() {
        AdapterView.OnClickListener onClickListener = view -> {
            UpdateUserProfileDialog updateProfileDialog = new UpdateUserProfileDialog();
            updateProfileDialog.show(getSupportFragmentManager(), String.valueOf(R.id.update_profile));
        };
        return onClickListener;
    }



    private void initComponents() {
        tvNume = findViewById(R.id.display_nume);
        tvBirthDate = findViewById(R.id.display_birth_date);
        tvEmail = findViewById(R.id.display_email);
        tvTelefon = findViewById(R.id.display_telefon);
        updateUser = findViewById(R.id.update_profile);
    }

    //Drawer Menu
    private void initMenuComponents() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //Navigation drawer menu
        navView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(this);
        navView.setCheckedItem(R.id.nav_profile);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_logOut:
                FirebaseAuth.getInstance().signOut();
                Intent intentMain = new Intent(this, MainActivity.class);
                startActivity(intentMain);
                break;
            case R.id.nav_profile:
                Intent intentProfile = new Intent(this, MyProfileActivity.class);
                startActivity(intentProfile);
                break;
            case R.id.nav_lessons:
                Intent intentLessons = new Intent(this, LessonsActivity.class);
                startActivity(intentLessons);
                break;
            case R.id.nav_reports:
                Intent intentReports = new Intent(this, ReportsActivity.class);
                startActivity(intentReports);
                break;
            case R.id.nav_contact:
                Intent intentMessages = new Intent(this, MessageActivity.class);
                startActivity(intentMessages);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}