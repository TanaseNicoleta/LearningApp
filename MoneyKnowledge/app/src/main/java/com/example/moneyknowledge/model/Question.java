package com.example.moneyknowledge.model;

import java.io.Serializable;

public class Question implements Serializable {
    private String id, id_lesson, question;

    public Question() {
    }

    public Question(String id, String id_lesson, String question) {
        this.id = id;
        this.id_lesson = id_lesson;
        this.question = question;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", id_lesson='" + id_lesson + '\'' +
                ", question='" + question + '\'' +
                '}';
    }
}
