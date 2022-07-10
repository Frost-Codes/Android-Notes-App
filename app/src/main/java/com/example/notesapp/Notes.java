package com.example.notesapp;

import io.realm.RealmObject;

public class Notes extends RealmObject {
    String title;
    String description;
    long createdTime;

    //Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    //Getters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public long getCreatedTime() {
        return createdTime;
    }


}
