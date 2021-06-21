package com.example.moneyknowledge.model;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {
    private String id, id_lesson, question;
    private List<String> answers;
    private String correctAnswer;

    public Question() {
    }

    public Question(String id, String id_lesson, String question, List<String> answers, String correctAnswer) {
        this.id = id;
        this.id_lesson = id_lesson;
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
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
