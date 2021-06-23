package com.example.moneyknowledge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.moneyknowledge.R;
import com.example.moneyknowledge.model.Lesson;
import com.example.moneyknowledge.model.LessonProgress;
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

public class LessonsListAdapter extends ArrayAdapter<Lesson> {
    public static final String NOTE = "note";
    private Context context;
    private int resource;
    private List<Lesson> lessons;
    private LayoutInflater inflater;

    final DatabaseReference database = FirebaseDatabase.getInstance().getReference(NOTE);
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    final String userId = user.getUid();


    public LessonsListAdapter(@NonNull Context context, int resource, @NonNull List<Lesson> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.lessons = objects;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Lesson lesson = lessons.get(position);
        TextView textView = view.findViewById(R.id.lesson_title);
        populateTV(lesson.getTitle(), textView);
        SeekBar sb = view.findViewById(R.id.setProgressSB);

        TextView tvProgres = view.findViewById(R.id.tvProgres);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    LessonProgress grade = dataSnapshot.getValue(LessonProgress.class);
                    if(grade.getId_user().equals(userId) && grade.getId_lesson().equals(lesson.getId())) {
                        sb.setProgress(grade.getProgress());
                        tvProgres.setText("Progres: " + Integer.toString(grade.getProgress()) + "%");
                    } else {
                        sb.setProgress(0);
                        tvProgres.setText("Progres: 0%");
                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        sb.setEnabled(false);

        return view;
    }

    private void populateTV(String name, TextView textView) {
        if (name!=null && !name.isEmpty()) {
            textView.setText(name);
        } else {
            textView.setText(R.string.msj_error);
        }
    }
}
