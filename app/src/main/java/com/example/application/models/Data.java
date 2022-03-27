package com.example.application.models;

import com.example.application.models.pojos.CourseInfo;
import com.example.application.models.pojos.Notes;

import java.util.ArrayList;
import java.util.HashMap;

public  class Data {

    private static final Data instanse = new Data();

    public HashMap<String, CourseInfo> courses = new HashMap();
    public ArrayList<Notes> notesArrayList = new ArrayList<>();

    public  Data(){
        initializeCourse();
        initializeNotes();
    }
    public static Data getInstance() {


        return instanse;
    }

    public void initializeNotes() {

        Notes notes = new Notes("pre_prep","Making responsive layouts","Using divs to add responsiveness in layouts");

        notesArrayList.add(notes);

        notes = new Notes("pre_prep","Using jquery to handle html elements","Using jquery java script library to handle layouts ");

        notesArrayList.add(notes);
        notes = new Notes("prep","Bootstrap","Using boot strap to make responsive layouts");

        notesArrayList.add(notes);
        notes = new Notes("prep","Angular","Front end frame work fro angular");

        notesArrayList.add(notes);
        notes = new Notes("pre_core","One-way Dataflow","Making one way data flow");

        notesArrayList.add(notes);
        notes = new Notes("pre_core","Classes","How to make classes in java");

        notesArrayList.add(notes);
        notes = new Notes("core","Databases","How to make CRUD operation in database");

        notesArrayList.add(notes);


    }

    private void initializeCourse() {

        CourseInfo  Course = new CourseInfo("pre_prep","Intro to programming");

        courses.put(Course.getId(),Course);

        Course = new CourseInfo("prep","Intro to jquery and javascript ");

        courses.put(Course.getId(),Course);

        Course = new CourseInfo("pre_core","Angular programming");

        courses.put(Course.getId(),Course);

        Course = new CourseInfo("core","Java and Android");

        courses.put(Course.getId(),Course);

    }
}
