package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.application.databinding.ActivityMainBinding;
import com.example.application.databinding.ActivityNoteDetailBinding;
import com.example.application.models.Data;
import com.example.application.models.pojos.CourseInfo;

public class NoteDetail extends AppCompatActivity {

    ActivityNoteDetailBinding binding;
    Data data = new Data();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoteDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ArrayAdapter<CourseInfo> arrayAdapter = new ArrayAdapter<CourseInfo>(this,
                android.R.layout.simple_spinner_item, (CourseInfo[]) data.courses.values().toArray());

        arrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);

        binding.spinner.setAdapter(arrayAdapter);


        binding.save.setOnClickListener(view1 -> {
            String title = binding.head.getText().toString().trim();
            String content = binding.content.getText().toString().trim();

        });


    }
}