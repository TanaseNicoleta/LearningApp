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
import android.widget.Button;

import com.example.moneyknowledge.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class LessonsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String ECONOMIE = "Economie";
    public static final String CATEGORY = "Category";
    public static final String CONTABILITATE = "Contabilitate";
    public static final String FINANTE = "Finante";
    DrawerLayout drawerLayout;
    NavigationView navView;
    Toolbar toolbar;
    Button btnEconomy, btnAccounting, btnFinance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);
        initMenuComponents();
        initComponents();
    }

    private void initComponents() {
        btnEconomy = findViewById(R.id.btnEconomie);
        btnAccounting = findViewById(R.id.btnContabilitate);
        btnFinance = findViewById(R.id.btnFinante);

        btnEconomy.setOnClickListener(openEconomyList());
        btnAccounting.setOnClickListener(openAccountingList());
        btnFinance.setOnClickListener(openFinanceList());
    }

    private View.OnClickListener openFinanceList() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LessonListActivity.class);
                intent.putExtra(CATEGORY, FINANTE);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener openAccountingList() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LessonListActivity.class);
                intent.putExtra(CATEGORY, CONTABILITATE);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener openEconomyList() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LessonListActivity.class);
                intent.putExtra(CATEGORY, ECONOMIE);
                startActivity(intent);
            }
        };
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
        navView.setCheckedItem(R.id.nav_lessons);
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
            case R.id.nav_grades:
                Intent intentGrades = new Intent(this, GradesListActivity.class);
                startActivity(intentGrades);
                break;
            case R.id.nav_notes:
                Intent intentNotes = new Intent(this, MyNotesActivity.class);
                startActivity(intentNotes);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}