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
import android.graphics.text.LineBreaker;
import android.net.Uri;
import android.os.Build;
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
    public static final String ANSWERS_TEST = "answers-test";
    ImageView image;
    TextView content, raport;
    Intent intent;
    String lessonId;
    FloatingActionButton openTest;
    Button notes;

    public static final String LESSONS = "lessons";
    final DatabaseReference database = FirebaseDatabase.getInstance().getReference(LESSONS);
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_lesson);
        intent=getIntent();
        lessonId = intent.getStringExtra("id");
        initComponents();

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

        raport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TestReportActivity.class);
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
        raport = findViewById(R.id.testReport);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            content.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
        }

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