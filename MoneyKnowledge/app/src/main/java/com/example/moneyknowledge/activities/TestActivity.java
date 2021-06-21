package com.example.moneyknowledge.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.moneyknowledge.R;
import com.example.moneyknowledge.adapter.LessonsListAdapter;
import com.example.moneyknowledge.adapter.QuestionListAdapter;
import com.example.moneyknowledge.model.Lesson;
import com.example.moneyknowledge.model.Question;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    public static final String LESSON_ID = "lessonId";
    public static final String INTREBARI = "intrebari";
    Intent intent;
    String lessonId;
    private static QuestionListAdapter adapter;
    List<Question> questions = new ArrayList<>();

    ListView lvQuestion;
    FloatingActionButton btnNext;
    final DatabaseReference databaseIntreb = FirebaseDatabase.getInstance().getReference(INTREBARI);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        intent = getIntent();
        lessonId = intent.getStringExtra(LESSON_ID);

        initComponents();

    }

    private void initComponents() {
        btnNext = findViewById(R.id.nextBtn);
        lvQuestion = findViewById(R.id.lv_questions);

        adapter = new QuestionListAdapter(getApplicationContext(), R.layout.lv_question, questions, getLayoutInflater());
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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}