package com.example.moneyknowledge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.moneyknowledge.R;
import com.example.moneyknowledge.model.Lesson;
import com.example.moneyknowledge.model.Notes;

import java.util.List;

public class NoteAdapter extends ArrayAdapter<Notes> {
    private Context context;
    private int resource;
    private List<Notes> notes;
    private LayoutInflater inflater;

    public NoteAdapter(@NonNull Context context, int resource, @NonNull List<Notes> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.notes = objects;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Notes note = notes.get(position);

        if(note != null) {
            TextView textView = view.findViewById(R.id.lesson_title);
            textView.setText(note.getId_lesson().toUpperCase());

            TextView textViewNote = view.findViewById(R.id.noteContent);
            textViewNote.setText(note.getNote());
        }

        return view;
    }

}
