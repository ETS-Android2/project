package com.example.application.models.pojos;

public class Notes {

    private int id;
    private String title;

    public Notes(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "title='" + title + '\'' +
                '}';
    }
}
