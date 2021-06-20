package com.example.moneyknowledge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

public class LessonsListAdapter extends ArrayAdapter<String> {
    private Context context;
    private int resource;
    private List<String> lessons;
    private LayoutInflater inflater;

    public LessonsListAdapter(@NonNull Context context, int resource, @NonNull List<String> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.lessons = objects;
        this.inflater = inflater;
    }


}
