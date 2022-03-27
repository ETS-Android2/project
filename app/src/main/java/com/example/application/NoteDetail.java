package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.application.databinding.ActivityNoteDetailBinding;
import com.example.application.models.Data;
import com.example.application.models.pojos.CourseInfo;
import com.example.application.models.pojos.Notes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class NoteDetail extends AppCompatActivity {

    ActivityNoteDetailBinding binding;
    Data data = new Data()   ;
    List<CourseInfo> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoteDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        int index = intent.getIntExtra("index",0);

        if(this.getIntent().getData() == null){

        }else {
            populate(index);
        }

        Collection<CourseInfo> getValues = data.courses.values();
        list.addAll(getValues);

        ArrayAdapter<CourseInfo> arrayAdapter = new ArrayAdapter<CourseInfo>(this,
                android.R.layout.simple_spinner_item,list );

        arrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);

        binding.spinner.setAdapter(arrayAdapter);


        binding.save.setOnClickListener(view1 -> {

            if(valid()){
                String title = binding.head.getText().toString().trim();
                String content = binding.content.getText().toString().trim();
                String course = binding.spinner.getSelectedItem().toString();

                Notes noteDetail = new Notes(course,title,content);

                data.notesArrayList.add(noteDetail);

            }else {

                binding.head.getText().clear();
                binding.content.getText().clear();
            }

        });


    }

    private void populate(int i) {

    Notes notes = data.notesArrayList.get(i);

    int note  = list.indexOf(notes.getCourseInfo());
    binding.spinner.setSelection(note);
    binding.head.setText(notes.getTitle());
    binding.content.setText(notes.getText());

    }

    private boolean valid() {

        if(!TextUtils.isEmpty( binding.head.getText().toString().trim())){
            binding.head.setError("This field is required"  );
            return  false;
        }
        if(!TextUtils.isEmpty( binding.content.getText().toString().trim())){
            binding.content.setError("This field is required"  );
            return  false;

        }
        if(binding.spinner.getSelectedItem().toString().trim().equals("")){
            Toast.makeText(this,"Please select item from spinner",Toast.LENGTH_SHORT).show();
            return  false;

        }

        return true;
    }
}