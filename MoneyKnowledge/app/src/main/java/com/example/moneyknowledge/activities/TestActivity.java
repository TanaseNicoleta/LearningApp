package com.example.moneyknowledge.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneyknowledge.R;
import com.example.moneyknowledge.model.Answers;
import com.example.moneyknowledge.model.LessonProgress;
import com.example.moneyknowledge.model.Question;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TestActivity extends AppCompatActivity {
    public static final String LESSON_ID = "lessonId";
    public static final String INTREBARI = "intrebari";
    public static final String RASPUNSURI = "raspunsuri";
    public static final String NOTA = "nota";
    public static final String NOTE = "note";
    LessonProgress lessonProgress;
    Intent intent;
    String lessonId;
    int nota = 0;
    int i = 1;

    final DatabaseReference databaseIntreb = FirebaseDatabase.getInstance().getReference(INTREBARI);
    final DatabaseReference database = FirebaseDatabase.getInstance().getReference(RASPUNSURI);
    final DatabaseReference databaseGrades = FirebaseDatabase.getInstance().getReference(NOTE);
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    final String userId = user.getUid();

    TextView tv_question, tv_nota;
    Button answ1, answ2, answ3, answ4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        intent = getIntent();
        lessonId = intent.getStringExtra(LESSON_ID);
        initComponents();
    }

    private void initComponents() {
        tv_question = findViewById(R.id.questions);
        tv_nota = findViewById(R.id.nota);
        answ1 = findViewById(R.id.answ1);
        answ2 = findViewById(R.id.answ2);
        answ3 = findViewById(R.id.answ3);
        answ4 = findViewById(R.id.answ4);
        readQuestions(1);
        setAnswerClicks();

    }

    private void setAnswerClicks() {
        database.child("answers-"+lessonId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Answers answers = snapshot.getValue(Answers.class);
                answ1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(i<4) {
                            if (answ1.getText().equals(answers.getAnswers().get(0))) {
                                nota++;
                            }
                            i++;
                            readQuestions(i);
                        } else {
                            if (answ1.getText().equals(answers.getAnswers().get(0))) {
                                nota++;
                            }
                            openFinishActivity();
                        }

                    }

                });

                answ2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(i<4) {
                            if (answ2.getText().equals(answers.getAnswers().get(1))) {
                                nota++;
                            }
                            i++;
                            readQuestions(i);
                        } else {
                            if (answ2.getText().equals(answers.getAnswers().get(1))) {
                                nota++;
                            }
                            openFinishActivity();
                        }

                    }
                });

                answ3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(i<4) {
                            if (answ3.getText().equals(answers.getAnswers().get(2))) {
                                nota++;
                            }
                            i++;
                            readQuestions(i);
                        } else {
                            if (answ3.getText().equals(answers.getAnswers().get(2))) {
                                nota++;
                            }
                            openFinishActivity();
                        }

                    }
                });

                answ4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(i<4) {
                            if (answ4.getText().equals(answers.getAnswers().get(3))) {
                                nota++;
                            }
                            i++;
                            readQuestions(i);
                        } else {
                            if (answ4.getText().equals(answers.getAnswers().get(3))) {
                                nota++;
                            }
                            openFinishActivity();
                        }

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void readQuestions(int i) {
        databaseIntreb.child("question-" + i + "_" + lessonId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Question question = snapshot.getValue(Question.class);
                populateView(question.getQuestion(), question.getAnswers());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void openFinishActivity() {
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        lessonProgress = new LessonProgress("grade_"+userId+"_"+lessonId, lessonId, userId, nota*10, nota, currentDate);
        databaseGrades.child(lessonProgress.getId()).setValue(lessonProgress);
        if (nota >= 2) {
            Toast.makeText(this, "Bravo! Ai trecut testul. Iti poti vedea notele in zona de Note.", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Mai incearca. Iti poti vedea notele in zona de Note.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void populateView(String question, List<String > answers) {
        tv_question.setText(question);
        answ1.setText(answers.get(0));
        answ2.setText(answers.get(1));
        answ3.setText(answers.get(2));
        answ4.setText(answers.get(3));
        tv_nota.setText("Nota: " + nota);
    }

}