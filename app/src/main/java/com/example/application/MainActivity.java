package com.example.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.application.databinding.ActivityMainBinding;
import com.example.application.models.Data;
import com.example.application.models.pojos.Notes;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    ArrayAdapter<Notes> arrayAdapter;
    DatabaseReference myRef;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://application-ae48e-default-rtdb.asia-southeast1.firebasedatabase.app/");

    Data data  = Data.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        myRef = database.getReference().child("Notes");

        arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                Data.getInstance().notesArrayList
               );

        addFromFireBase();

        binding.listview.setAdapter(arrayAdapter);

        binding.listview.setOnItemClickListener((adapterView, view1, i, l) -> {
            Toast.makeText(this,"Editing existing  Note ",Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this,NoteDetail.class);
            intent.putExtra("index",i);
            startActivity(intent);
        });

        binding.fab.setOnClickListener(view1 -> {
            startActivity(new Intent(this,NoteDetail.class));
            Toast.makeText(this,"Creating new Note ",Toast.LENGTH_SHORT).show();
        });

    }

    private void addFromFireBase() {


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {

                    data.notesArrayList.add(ds.getValue(Notes.class));
                    arrayAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        arrayAdapter.notifyDataSetChanged();
    }
}