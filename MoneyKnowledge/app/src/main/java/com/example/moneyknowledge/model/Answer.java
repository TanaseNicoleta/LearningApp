package com.example.moneyknowledge.model;

import java.io.Serializable;

public class Answer implements Serializable {
    private String id, id_question, answer;
    private boolean status;

    public Answer() {
    }

    public Answer(String id, String id_question, String answer, boolean status) {
        this.id = id;
        this.id_question = id_question;
        this.answer = answer;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_question() {
        return id_question;
    }

    public void setId_question(String id_question) {
        this.id_question = id_question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id='" + id + '\'' +
                ", id_question='" + id_question + '\'' +
                ", answer='" + answer + '\'' +
                ", status=" + status +
                '}';
    }
}
