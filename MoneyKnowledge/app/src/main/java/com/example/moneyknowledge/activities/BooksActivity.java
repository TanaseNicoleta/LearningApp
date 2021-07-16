package com.example.moneyknowledge.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.moneyknowledge.R;
import com.example.moneyknowledge.adapter.BookAdapter;
import com.example.moneyknowledge.adapter.NoteAdapter;
import com.example.moneyknowledge.model.Book;
import com.example.moneyknowledge.model.Notes;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BooksActivity extends AppCompatActivity {
    public static final String CARTI = "carti";
    ListView lvBooks;
    List<Book> books = new ArrayList<>();
    BookAdapter adapter;
    final DatabaseReference database = FirebaseDatabase.getInstance().getReference(CARTI);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

       lvBooks = findViewById(R.id.lv_carti);

        adapter = new BookAdapter(getApplicationContext(), R.layout.lv_book, books, getLayoutInflater());
        lvBooks.setAdapter(adapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                books.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Book note = dataSnapshot.getValue(Book.class);
                    books.add(note);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}