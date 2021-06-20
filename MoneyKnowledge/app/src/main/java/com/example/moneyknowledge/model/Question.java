package com.example.moneyknowledge.model;

import java.io.Serializable;

public class Question implements Serializable {
    private String id, id_test, question;

    public Question() {
    }

    public Question(String id, String id_test, String question) {
        this.id = id;
        this.id_test = id_test;
        this.question = question;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_test() {
        return id_test;
    }

    public void setId_test(String id_test) {
        this.id_test = id_test;
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
                ", id_test='" + id_test + '\'' +
                ", question='" + question + '\'' +
                '}';
    }
}
