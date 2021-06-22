package com.example.moneyknowledge.model;

import java.io.Serializable;
import java.util.List;

public class Answers implements Serializable {
    private String id, id_lesson;
    private List<String> answers;

    public Answers() {
    }

    public Answers(String id, String id_lesson, List<String> answers) {
        this.id = id;
        this.id_lesson = id_lesson;
        this.answers = answers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_lesson() {
        return id_lesson;
    }

    public void setId_lesson(String id_lesson) {
        this.id_lesson = id_lesson;
    }


    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Answers{" +
                "id='" + id + '\'' +
                ", id_lesson='" + id_lesson + '\'' +
                ", answers=" + answers +
                '}';
    }
}
