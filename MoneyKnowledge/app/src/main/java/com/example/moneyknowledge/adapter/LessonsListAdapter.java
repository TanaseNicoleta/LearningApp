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

import java.util.List;

public class LessonsListAdapter extends ArrayAdapter<Lesson> {
    private Context context;
    private int resource;
    private List<Lesson> lessons;
    private LayoutInflater inflater;

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

        //Seteaza si progresul automat din firebase
        if(lesson != null) {
            addLessonName(view, lesson.getTitle());
        }

        return view;
    }

    private void addLessonName(View view, String name) {
        TextView textView = view.findViewById(R.id.lesson_title);
        populateTV(name, textView);
    }

    private void populateTV(String name, TextView textView) {
        if (name!=null && !name.isEmpty()) {
            textView.setText(name);
        } else {
            textView.setText(R.string.msj_error);
        }
    }
}
