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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
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
    int sumEc = 0;
    int sumFin = 0;
    int sumAcc = 0;

    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList<BarEntry> barEntries = new ArrayList<>();
    ArrayList<String> labels = new ArrayList<>();

   ArrayList<Integer> economyGrades = new ArrayList<>();
   ArrayList<Integer> financeGrades = new ArrayList<>();
   ArrayList<Integer> accountigGrades = new ArrayList<>();

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
                barEntries = new ArrayList();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    LessonProgress grade = dataSnapshot.getValue(LessonProgress.class);
                    if(grade.getId_user().equals(userId) && grade.getGrade() > 0) {
                        barEntries.add(new BarEntry(i, Float.parseFloat(Integer.toString(grade.getGrade()))));
                        i++;
                    }
                }

                barDataSet = new BarDataSet(barEntries, "");
                barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                barDataSet.setValueTextSize(12f);
                barData = new BarData(barDataSet);
                barChart.setData(barData);
                barChart.getLegend().setEnabled(false);
                barChart.setDescription(null);
                pieChart.invalidate();
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
                    if(grade.getId_lesson().contains("economie") && grade.getGrade() > 0) {
                        economyGrades.add(grade.getGrade());
                    }
                    if(grade.getId_lesson().contains("finante") && grade.getGrade() > 0) {
                        financeGrades.add(grade.getGrade());
                    }
                    if(grade.getId_lesson().contains("contabilitate") && grade.getGrade() > 0) {
                        accountigGrades.add(grade.getGrade());
                    }
                }
                Log.i("mesaj2", accountigGrades.toString());
                Log.i("mesaj21", financeGrades.toString());
                Log.i("mesaj22", economyGrades.toString());

                for (int i=0 ; i<economyGrades.size(); i++) {
                    sumEc+=economyGrades.get(i);
                }
                for (int i=0 ; i<financeGrades.size(); i++) {
                    sumFin+=financeGrades.get(i);
                }
                for (int i=0 ; i<accountigGrades.size(); i++) {
                    sumAcc+=accountigGrades.get(i);
                }

                if (economyGrades.size()>0)
                    pieEntries.add(new PieEntry(sumEc/economyGrades.size(), "Economie"));
                if (financeGrades.size()>0)
                    pieEntries.add(new PieEntry(sumFin/financeGrades.size(), "Finante"));
                if (accountigGrades.size()>0)
                    pieEntries.add(new PieEntry(sumAcc/accountigGrades.size(), "Contabilitate"));


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
            case R.id.nav_more:
                Intent intentMore = new Intent(this, MoreActivity.class);
                startActivity(intentMore);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}