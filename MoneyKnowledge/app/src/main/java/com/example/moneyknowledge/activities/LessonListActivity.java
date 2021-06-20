package com.example.moneyknowledge.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneyknowledge.R;
import com.example.moneyknowledge.activities.register.RegisterActivity;
import com.example.moneyknowledge.adapter.LessonsListAdapter;
import com.example.moneyknowledge.model.Lesson;
import com.example.moneyknowledge.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    List<String> lessons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_list);
        intent = getIntent();
        category = intent.getStringExtra(CATEGORY);
        initMenuComponents();
        initComponents();

        //Fa onItemClickListener sa deschida Activitate cu lectia
       // lvBank.setOnItemClickListener(openEditBankDialog());

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

        for(int i = 1; i <= 5; i++) {
            lessons.add(category + " " + i);
        }
         adapter = new LessonsListAdapter(getApplicationContext(), R.layout.lv_lessons, lessons, getLayoutInflater());
        lvLessons.setAdapter(adapter);

//        implementeaza onitemclicklistener
//        lvLessons.setOnItemClickListener(openEditBankDialog());

    }

    private void notifyAdapter() {
        ArrayAdapter adapter = (LessonsListAdapter) lvLessons.getAdapter();
        adapter.notifyDataSetChanged();
    }

//    private AdapterView.OnItemClickListener openEditBankDialog() {
//        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Bank clickedBanca = (Bank) adapterView.getItemAtPosition(i);
//                EditBankDialog editBankDialog = new EditBankDialog();
//                editBankDialog.show(getSupportFragmentManager(), getString(R.string.dialog_edit_commission));
//                clickedPreferences = getSharedPreferences(UPDATED_BANKS, MODE_PRIVATE);
//                SharedPreferences.Editor e = clickedPreferences.edit();
//                e.clear();
//                e.putString(String.valueOf(i), clickedBanca.toString());
//                e.commit();
//            }
//        };
//        return onItemClickListener;
//
//    }

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