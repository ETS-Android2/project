package com.example.application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.application.databinding.ActivityNoteDetailBinding;
import com.example.application.models.Data;
import com.example.application.models.pojos.CourseInfo;
import com.example.application.models.pojos.Notes;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class NoteDetail extends AppCompatActivity {

    ActivityNoteDetailBinding binding;
    Data data;
    List<CourseInfo> list = new ArrayList<>();

    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase  db = FirebaseDatabase.getInstance();

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference  databaseReference = db.getReference().child("Notes");

    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoteDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
         data = Data.getInstance();
        Intent intent = getIntent();
        int index = intent.getIntExtra("index",0);

        mDatabase = FirebaseDatabase.getInstance("https://application-ae48e-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Notes");

        if(index >0) {
            populate(index);
        }

        Collection<CourseInfo> getValues = Data.getInstance().courses.values();
        list.addAll(getValues);

        ArrayAdapter<CourseInfo> arrayAdapter = new ArrayAdapter<CourseInfo>(this,
                android.R.layout.simple_spinner_item,list );

        arrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);

        binding.spinner.setAdapter(arrayAdapter);


        binding.button.setOnClickListener(view1 -> {

            if(valid()){

                Log.i("Hello", "valid");
                String title = binding.head.getText().toString().trim();
                String content = binding.content.getText().toString().trim();
                String course = binding.spinner.getSelectedItem().toString();



                Notes noteDetail = new Notes(course,title,content);


                mDatabase.push().setValue(noteDetail);
                data.notesArrayList.clear();
                binding.head.getText().clear();
                binding.content.getText().clear();

                Toast.makeText(this,"Note has been successfully added",Toast.LENGTH_SHORT).show();



            }else {
                Log.i("Hello", "not valid");

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

        if(TextUtils.isEmpty( binding.head.getText().toString().trim())){
            binding.head.setError("This field is required"  );
            return  false;
        }
        if( TextUtils.isEmpty( binding.content.getText().toString().trim())){
            binding.content.setError("This field is required"  );
            return  false;

        }
        return true;
    }
}