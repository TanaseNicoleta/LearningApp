package com.example.moneyknowledge.model;

import java.io.Serializable;

public class Notes implements Serializable {
    String id, id_lesson, id_user, note;

    public Notes() {
    }

    public Notes(String id, String id_lesson, String id_user, String note) {
        this.id = id;
        this.id_lesson = id_lesson;
        this.id_user = id_user;
        this.note = note;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "id='" + id + '\'' +
                ", id_lesson='" + id_lesson + '\'' +
                ", id_user='" + id_user + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
