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
import android.widget.ListView;

import com.example.moneyknowledge.R;
import com.example.moneyknowledge.adapter.LessonsListAdapter;
import com.example.moneyknowledge.adapter.NoteAdapter;
import com.example.moneyknowledge.model.Lesson;
import com.example.moneyknowledge.model.Notes;
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

public class MyNotesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public static final String NOTES = "notes";
    DrawerLayout drawerLayout;
    NavigationView navView;
    Toolbar toolbar;

    ListView lvNotes;
    List<Notes> notes = new ArrayList<>();
    NoteAdapter adapter;
    final DatabaseReference database = FirebaseDatabase.getInstance().getReference(NOTES);
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    final String userId = user.getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);
        initMenuComponents();
        lvNotes = findViewById(R.id.lv_notes);

        adapter = new NoteAdapter(getApplicationContext(), R.layout.lv_note, notes, getLayoutInflater());
        lvNotes.setAdapter(adapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Notes note = dataSnapshot.getValue(Notes.class);
                    if(note.getId_user().equals(userId))
                        notes.add(note);
                }
                adapter.notifyDataSetChanged();
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
        navView.setCheckedItem(R.id.nav_notes);
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