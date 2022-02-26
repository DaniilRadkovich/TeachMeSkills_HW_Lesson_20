package com.teachmeskills.lesson_20.task_1.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Location {
    private int id;
    private String hometown;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }
}
