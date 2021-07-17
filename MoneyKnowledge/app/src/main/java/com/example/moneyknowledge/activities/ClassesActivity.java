package com.example.moneyknowledge.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.moneyknowledge.R;
import com.example.moneyknowledge.adapter.BookAdapter;
import com.example.moneyknowledge.adapter.ClassAdapter;
import com.example.moneyknowledge.model.Book;
import com.example.moneyknowledge.model.Classes;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ClassesActivity extends AppCompatActivity {
    public static final String CURSURI = "cursuri";
    ListView lvCurs;
    List<Classes> cursuri = new ArrayList<>();
    ClassAdapter adapter;
    final DatabaseReference database = FirebaseDatabase.getInstance().getReference(CURSURI);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);

        lvCurs = findViewById(R.id.lv_curs);

        adapter = new ClassAdapter(getApplicationContext(), R.layout.lv_class, cursuri, getLayoutInflater());
        lvCurs.setAdapter(adapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cursuri.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Classes class1 = dataSnapshot.getValue(Classes.class);
                    cursuri.add(class1);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}