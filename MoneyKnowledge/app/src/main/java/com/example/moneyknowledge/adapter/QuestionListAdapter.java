package com.example.moneyknowledge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.moneyknowledge.R;
import com.example.moneyknowledge.model.Lesson;
import com.example.moneyknowledge.model.Question;

import java.util.List;

public class QuestionListAdapter  extends ArrayAdapter<Question> {
    private Context context;
    private int resource;
    private List<Question> questions;
    private LayoutInflater inflater;

    TextView textView;
    Button answ1, answ2, answ3, answ4;

    public QuestionListAdapter(@NonNull Context context, int resource, @NonNull List<Question> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.questions = objects;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Question question = questions.get(position);

        if(question != null) {
            textView = view.findViewById(R.id.questions);
            textView.setText(question.getQuestion());

            answ1 = view.findViewById(R.id.answ1);
            answ2 = view.findViewById(R.id.answ2);
            answ3 = view.findViewById(R.id.answ3);
            answ4 = view.findViewById(R.id.answ4);

            answ1.setText(question.getAnswers().get(0));
            answ2.setText(question.getAnswers().get(1));
            answ3.setText(question.getAnswers().get(2));
            answ4.setText(question.getAnswers().get(3));

            //check selected answer is correct
        }

        return view;
    }


}

