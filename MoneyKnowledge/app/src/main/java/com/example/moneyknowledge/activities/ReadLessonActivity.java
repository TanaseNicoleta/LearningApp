package com.example.moneyknowledge.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneyknowledge.R;
import com.example.moneyknowledge.dialogs.AddNoteDialog;
import com.example.moneyknowledge.dialogs.UpdateUserProfileDialog;
import com.example.moneyknowledge.model.Answers;
import com.example.moneyknowledge.model.Lesson;
import com.example.moneyknowledge.model.Question;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ReadLessonActivity extends AppCompatActivity{
    public static final String LESSON_ID = "lessonId";
    ImageView image;
    TextView content;
    Intent intent;
    String lessonId;
    FloatingActionButton openTest;
    Button notes;

    public static final String LESSONS = "lessons";
    final DatabaseReference database = FirebaseDatabase.getInstance().getReference(LESSONS);
    final DatabaseReference databaseI = FirebaseDatabase.getInstance().getReference("intrebari");
    final DatabaseReference databaseR = FirebaseDatabase.getInstance().getReference("raspunsuri");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_lesson);
        intent=getIntent();
        lessonId = intent.getStringExtra("id");

        initComponents();

//        ArrayList<String> answers = new ArrayList<>();
//        answers.add("Business");
//        answers.add("Activitate economică");
//        answers.add("Întreprindere");
//        answers.add("Сomerț");
//        Question q1 = new Question("question-5_"+lessonId,lessonId,
//                "Orice activitate de gestiune eficientă a resurselor economice este numită:", answers, "Activitate economică");
//
//        databaseI.child(q1.getId()).setValue(q1);
//
//        ArrayList<String> answers = new ArrayList<>();
//        answers.add("rentabilitate");
//        answers.add("suma totală a veniturilor din operațiunile comerciale efectuate de firmă, respectiv vânzarea de mărfuri și produse într-o perioadă de timp determinată");
//        answers.add("plasament de bani în valori mobiliare aducator de venit sub formă de dobandă, dividende sau câștiguri de capital și/sau achiziționare de mijloace de producție");
//        answers.add("este partea rămasă din venitul total ce revine întreprinzatorului după ce s-au scăzut toate cheltuielile aferente venitului respectiv");
//        answers.add("Activitate economică");
//
//        Answers answ = new Answers("answers-"+lessonId, lessonId, answers);
//        databaseR.child(answ.getId()).setValue(answ);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open gallery
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent, 1000);
            }
        });

        openTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TestActivity.class);
                intent.putExtra(LESSON_ID, lessonId);
                startActivity(intent);
            }
        });

        notes.setOnClickListener(openNotesDialog());
    }

    private AdapterView.OnClickListener openNotesDialog() {
        AdapterView.OnClickListener onClickListener = view -> {
            AddNoteDialog addNoteDialog = new AddNoteDialog();
            Bundle bundle = new Bundle();
            bundle.putString(LESSON_ID, lessonId);
            addNoteDialog.setArguments(bundle);
            addNoteDialog.show(getSupportFragmentManager(), String.valueOf(R.id.openNotes));
        };
        return onClickListener;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uriImage = data.getData();
                uploadImageToFirebase(uriImage);
            }
        }
    }

    private void uploadImageToFirebase(Uri imageUri) {
        StorageReference fileReference = storageReference.child("lessons/"+lessonId+"/banner.jpg");
        fileReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(image);
                    }
                });
                Toast.makeText(ReadLessonActivity.this, "Image successfully uploaded!", Toast.LENGTH_SHORT).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ReadLessonActivity.this, "Failed to upload!", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void initComponents() {
        image = findViewById(R.id.lessonImage);
        content = findViewById(R.id.lessonContent);
        openTest = findViewById(R.id.openTest);
        notes = findViewById(R.id.openNotes);

        database.child(lessonId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Lesson lesson = snapshot.getValue(Lesson.class);
                content.setText(lesson.getContent());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        StorageReference bannerReference = storageReference.child("lessons/"+lessonId+"/banner.jpg");
        bannerReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(image);
            }
        });

    }

}