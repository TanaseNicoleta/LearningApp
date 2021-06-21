package com.example.moneyknowledge.model;

import java.io.Serializable;
import java.util.Date;

public class Grade implements Serializable {
    private int grade;
    private Date date_recorded;

    public Grade() {
    }

    public Grade(int grade, Date date_recorded) {
        this.grade = grade;
        this.date_recorded = date_recorded;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Date getDate_recorded() {
        return date_recorded;
    }

    public void setDate_recorded(Date date_recorded) {
        this.date_recorded = date_recorded;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "grade=" + grade +
                ", date_recorded=" + date_recorded +
                '}';
    }
}
