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
import android.widget.Toast;

import com.example.moneyknowledge.R;
import com.example.moneyknowledge.adapter.NoteAdapter;
import com.example.moneyknowledge.adapter.ReportAdapter;
import com.example.moneyknowledge.model.Notes;
import com.example.moneyknowledge.model.Question;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

import static com.example.moneyknowledge.activities.TestActivity.INTREBARI;

public class TestReportActivity extends AppCompatActivity {
    public static final String LESSON_ID = "lessonId";
    public static final String INTREBARI = "intrebari";
    Intent intent;
    String lessonId;
    private static ReportAdapter adapter;
    List<Question> questions = new ArrayList<>();

    ListView lvQuestion;
    final DatabaseReference databaseIntreb = FirebaseDatabase.getInstance().getReference(INTREBARI);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_report);
        intent = getIntent();
        lessonId = intent.getStringExtra(LESSON_ID);

        initComponents();
    }

    private void initComponents() {
        lvQuestion = findViewById(R.id.lv_questions);
        adapter = new ReportAdapter(getApplicationContext(), R.layout.lv_report_question, questions, getLayoutInflater());
        lvQuestion.setAdapter(adapter);

        databaseIntreb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Question question = dataSnapshot.getValue(Question.class);
                    if(question.getId_lesson().equals(lessonId))
                        questions.add(question);
                }
                adapter.notifyDataSetChanged();

                if (questions.isEmpty()) {
                    Toast.makeText(TestReportActivity.this, "Se pare ca nu exista un raport pentru acest test. Ne pare rau.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}