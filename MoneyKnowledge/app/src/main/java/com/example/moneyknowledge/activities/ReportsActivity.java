package com.example.moneyknowledge.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.moneyknowledge.R;
import com.example.moneyknowledge.model.LessonProgress;
import com.example.moneyknowledge.model.Question;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReportsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String NOTE = "note";
    DrawerLayout drawerLayout;
    NavigationView navView;
    Toolbar toolbar;

    final DatabaseReference database = FirebaseDatabase.getInstance().getReference(NOTE);
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    final String userId = user.getUid();
    int i = 0;

    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList barEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        initMenuComponents();
        initComponents();
    }


    public void initComponents() {
        barChart = findViewById(R.id.barChart);
        SetData();

    }

    private void SetData() {
        barEntries = new ArrayList();

        barEntries.add(new BarEntry(Float.parseFloat(Integer.toString(2)), 0));
        barEntries.add(new BarEntry(4f, 1));
        barEntries.add(new BarEntry(3f, 2));
        barEntries.add(new BarEntry(0f, 3));
        barEntries.add(new BarEntry(1f, 4));
        barEntries.add(new BarEntry(9f, 5));
        barEntries.add(new BarEntry(5f, 6));

        barDataSet = new BarDataSet(barEntries, "");
        barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        barDataSet.setValueTextColor(Color.WHITE);
        barDataSet.setValueTextSize(12f);

//        database.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    LessonProgress grade = dataSnapshot.getValue(LessonProgress.class);
//                    if(grade.getId_user().equals(userId) && grade.getGrade() > 0) {
//                        barEntries.add(new BarEntry(Float.parseFloat(Integer.toString(grade.getGrade())), i));
//                        i++;
//                    }
//
//                }
//                barDataSet = new BarDataSet(barEntries, "");
//                barData = new BarData(barDataSet);
//                barChart.setData(barData);
//                barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
//                barDataSet.setValueTextColor(Color.WHITE);
//                barDataSet.setValueTextSize(12f);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


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
        navView.setCheckedItem(R.id.nav_reports);
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