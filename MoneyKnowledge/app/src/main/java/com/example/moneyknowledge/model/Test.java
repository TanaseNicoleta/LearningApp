package com.example.moneyknowledge.model;

import java.io.Serializable;

public class Test implements Serializable {
    private String id, id_lesson;

    public Test() {
    }

    public Test(String id, String id_lesson) {
        this.id = id;
        this.id_lesson = id_lesson;
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

    @Override
    public String toString() {
        return "Test{" +
                "id='" + id + '\'' +
                ", id_lesson='" + id_lesson + '\'' +
                '}';
    }
}
