package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.application.databinding.ActivityMainBinding;
import com.example.application.models.Data;
import com.example.application.models.pojos.Notes;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    Data  data = new Data();
    ArrayAdapter<Notes> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                data.notesArrayList
               );


        binding.listview.setAdapter(arrayAdapter);

        binding.fab.setOnClickListener(view1 -> {
            startActivity(new Intent(this,NoteDetail.class));
        });

    }
}