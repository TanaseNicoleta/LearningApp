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
import android.util.Log;
import android.view.MenuItem;

import com.example.moneyknowledge.R;
import com.example.moneyknowledge.model.Lesson;
import com.example.moneyknowledge.model.LessonProgress;
import com.example.moneyknowledge.model.Question;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
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
import java.util.List;

public class ReportsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String NOTE = "note";
    DrawerLayout drawerLayout;
    NavigationView navView;
    Toolbar toolbar;

    final DatabaseReference database = FirebaseDatabase.getInstance().getReference(NOTE);
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    final String userId = user.getUid();
    int i = 0;

    HorizontalBarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList<BarEntry> barEntries = new ArrayList<>();
    ArrayList<String> labels = new ArrayList<>();


    PieChart pieChart;
    PieData pieData;
    PieDataSet pieDataSet;
    ArrayList pieEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        initMenuComponents();
        barChart = findViewById(R.id.barChart);
        pieChart = findViewById(R.id.pieChart);
        SetData();
        dataValues();

    }

    private void SetData() {
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    LessonProgress grade = dataSnapshot.getValue(LessonProgress.class);
                    barEntries.add(new BarEntry(i, Float.parseFloat(Integer.toString(grade.getGrade())), grade.getId_lesson()));
                    labels.add(grade.getId_lesson());
                    i++;
                }
                Log.i("mesaj", barEntries.toString());
                List<IBarDataSet> dataSets = new ArrayList<>();
                barDataSet = new BarDataSet(barEntries, "");
                barData = new BarData(barDataSet);
                String[] xAxisLabels = labels.toArray(new String[0]);

                barDataSet.setStackLabels(xAxisLabels);
                barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));

                barChart.setData(barData);
                dataSets.add(barDataSet);
                barData.addDataSet(barDataSet);
                barData.setDrawValues(true);
                barData.setBarWidth(0.4f);
                barChart.getAxisLeft().setAxisMaximum(10);
                barChart.getAxisLeft().setAxisMinimum(1);

                barChart.setFitBars(true);
                barChart.setData(barData);

                barChart.setDrawValueAboveBar(false);
                barChart.setDescription(null);
                barDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
                barDataSet.setValueTextColor(Color.WHITE);
                barDataSet.setValueTextSize(12f);
                barChart.setPinchZoom(true);
                barChart.getLegend().setEnabled(false);

                barChart.invalidate();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void dataValues() {
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                pieEntries = new ArrayList();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    LessonProgress grade = dataSnapshot.getValue(LessonProgress.class);
                    if(grade.getId_user().equals(userId) && grade.getGrade() > 0) {
                        pieEntries.add(new PieEntry(Float.parseFloat(Integer.toString(grade.getGrade())), grade.getId_lesson()));
                    }
                }
                Log.i("mesaj2", pieEntries.toString());
                pieDataSet = new PieDataSet(pieEntries, "");
                pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                pieDataSet.setValueTextSize(12f);
                pieData = new PieData(pieDataSet);
                pieChart.setData(pieData);
                pieChart.getLegend().setEnabled(false);
                pieChart.setDescription(null);
                pieChart.invalidate();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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