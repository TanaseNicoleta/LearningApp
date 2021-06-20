package com.example.moneyknowledge.model;

import java.io.Serializable;
import java.util.Date;

public class LessonProgress implements Serializable {
    private String id, id_lesson, id_user;
    private int progress, grade;
    private Date date;          // get system current date for when the grade was recorded

    public LessonProgress() {
    }

    public LessonProgress(String id, String id_lesson, String id_user, int progress, int grade, Date date) {
        this.id = id;
        this.id_lesson = id_lesson;
        this.id_user = id_user;
        this.progress = progress;
        this.grade = grade;
        this.date = date;
    }

    public LessonProgress(String id, String id_lesson, String id_user, int progress, Date date) {
        this.id = id;
        this.id_lesson = id_lesson;
        this.id_user = id_user;
        this.progress = progress;
        this.grade = 0;
        this.date = date;
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

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "LessonProgress{" +
                "id='" + id + '\'' +
                ", id_lesson='" + id_lesson + '\'' +
                ", id_user='" + id_user + '\'' +
                ", progress=" + progress +
                ", grade=" + grade +
                ", date=" + date +
                '}';
    }
}
