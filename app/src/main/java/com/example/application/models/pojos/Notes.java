package com.example.application.models.pojos;

public class Notes {

    private String courseInfo;
    private String title;
    private String text;

    public Notes(String courseInfo, String title, String text) {
        this.courseInfo = courseInfo;
        this.title = title;
        this.text = text;
    }

    public Notes() {

    }

    public String getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(String courseInfo) {
        this.courseInfo = courseInfo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "courseInfo='" + courseInfo + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
