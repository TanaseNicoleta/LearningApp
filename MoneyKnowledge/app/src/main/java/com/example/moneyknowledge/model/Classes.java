package com.example.moneyknowledge.model;

import java.io.Serializable;

public class Classes implements Serializable {
    String id, title, professors;
    String academy;

    public Classes() {
    }

    public Classes(String id, String title, String professors, String academy) {
        this.id = id;
        this.title = title;
        this.professors = professors;
        this.academy = academy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProfessors() {
        return professors;
    }

    public void setProfessors(String professors) {
        this.professors = professors;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", professors='" + professors + '\'' +
                ", academy='" + academy + '\'' +
                '}';
    }
}
