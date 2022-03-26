package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.application.databinding.ActivityMainBinding;
import com.example.application.models.Data;
import com.example.application.models.pojos.CourseInfo;
import com.example.application.models.pojos.Notes;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    Data  data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        data = new Data();

        ArrayAdapter<Notes> arrayAdapter = new ArrayAdapter<Notes>(this,
                android.R.layout.simple_list_item_1,
                data.notesArrayList);


        binding.listview.setAdapter(arrayAdapter);

        binding.floatingActionButton4.setOnClickListener(view1 -> {
            startActivity(new Intent(this,NoteDetail.class));
        });

    }
}