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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.moneyknowledge.R;
import com.example.moneyknowledge.adapter.LessonsListAdapter;
import com.example.moneyknowledge.model.Lesson;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LessonListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String ECONOMIE = "Economie";
    public static final String CATEGORY = "Category";
    public static final String CONTABILITATE = "Contabilitate";
    public static final String FINANTE = "Finante";
    public static final String LESSONS = "lessons";
    final DatabaseReference database = FirebaseDatabase.getInstance().getReference(LESSONS);
    private static LessonsListAdapter adapter;

    DrawerLayout drawerLayout;
    NavigationView navView;
    Toolbar toolbar;
    Intent intent;
    String category;
    ListView lvLessons;
    TextView tvTitle;
    ImageView ivLessonIcon;
    List<Lesson> lessons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_list);
        intent = getIntent();
        category = intent.getStringExtra(CATEGORY);
        initMenuComponents();
        initComponents();

        lvLessons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Lesson lesson = (Lesson) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), ReadLessonActivity.class);
                intent.putExtra("id", lesson.getId());
                startActivity(intent);
            }
        });
    }

    private void initComponents() {
        lvLessons = findViewById(R.id.lv_lessonList);
        tvTitle = findViewById(R.id.titleLesson);
        tvTitle.setText(category);
        ivLessonIcon = findViewById(R.id.imageLesson);
        switch (category){
            case CONTABILITATE:
                ivLessonIcon.setImageDrawable(getResources().getDrawable(R.drawable.contabilitate_student));
                break;
            case ECONOMIE:
                ivLessonIcon.setImageDrawable(getResources().getDrawable(R.drawable.econ_student));
                break;
            case FINANTE:
                ivLessonIcon.setImageDrawable(getResources().getDrawable(R.drawable.finante_student));
                break;
            default:
                ivLessonIcon.setImageDrawable(getResources().getDrawable(R.drawable.education_002));
        }

        adapter = new LessonsListAdapter(getApplicationContext(), R.layout.lv_lessons, lessons, getLayoutInflater());
        lvLessons.setAdapter(adapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Lesson lesson = dataSnapshot.getValue(Lesson.class);
                    if(lesson.getCategory().equals(category))
                        lessons.add(lesson);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void notifyAdapter() {
        ArrayAdapter adapter = (LessonsListAdapter) lvLessons.getAdapter();
        adapter.notifyDataSetChanged();
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
        navView.setCheckedItem(R.id.nav_contact);
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