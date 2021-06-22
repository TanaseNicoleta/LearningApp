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
import com.example.moneyknowledge.model.LessonProgress;

import java.util.List;

public class GradeAdapter extends ArrayAdapter<LessonProgress> {
    private Context context;
    private int resource;
    private List<LessonProgress> grades;
    private LayoutInflater inflater;

    public GradeAdapter(@NonNull Context context, int resource, @NonNull List<LessonProgress> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.grades = objects;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        LessonProgress grade = grades.get(position);

        if(grade != null) {
            TextView tv_lesson = view.findViewById(R.id.tv_lesson);
            tv_lesson.setText(grade.getId_lesson().toUpperCase());

            TextView tv_grade = view.findViewById(R.id.tv_grade);
            tv_grade.setText("Nota: " + Integer.toString(grade.getGrade()));

            TextView tv_date = view.findViewById(R.id.tv_date);
            tv_date.setText("Data: " + grade.getDate());
        }

        return view;
    }

}
