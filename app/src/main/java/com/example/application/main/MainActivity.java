package com.example.application.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.application.NoteDetail;
import com.example.application.databinding.ActivityMainBinding;
import com.example.application.models.Data;
import com.example.application.models.pojos.Notes;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    DatabaseReference myRef;
    public ArrayList<Notes> notes;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://application-ae48e-default-rtdb.asia-southeast1.firebasedatabase.app/");
    NoteAdapter noteAdapter;
    Data data = Data.getInstance();
    Notes deleted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        myRef = database.getReference().child("Notes");
        notes = new ArrayList<>();

        noteAdapter = new NoteAdapter();
        Toast.makeText(MainActivity.this,"Swipe to delete",Toast.LENGTH_SHORT).show();
        addFromFireBase();

        binding.fab.setOnClickListener(view1 -> {
            startActivity(new Intent(this, NoteDetail.class));
            Toast.makeText(this, "Creating new Note ", Toast.LENGTH_SHORT).show();
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.recyclerview);
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @SuppressLint("ShowToast")
        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getAdapterPosition();

            switch (direction) {
                case ItemTouchHelper.LEFT:
                case ItemTouchHelper.RIGHT:
                    deleted = notes.get(position);
                    noteAdapter.remove(position);
                    noteAdapter.notifyItemChanged(position);

                    Snackbar.make(binding.recyclerview,"Note deleted",Snackbar.LENGTH_SHORT)
                            .setAction("Undo", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Log.d("TAG", "onClick: eached here");
                                    noteAdapter.add(position,deleted);
                                    noteAdapter.notifyItemChanged(position);
                                }
                            }).show();
                    break;

            }
        }
    };

    private void addFromFireBase() {

        ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {

                    data.notesArrayList.add(ds.getValue(Notes.class));
                    notes.add(ds.getValue(Notes.class));

                    noteAdapter.addNotes(notes);
                    noteAdapter.notifyDataSetChanged();

                    binding.recyclerview.setAdapter(noteAdapter);
                    binding.recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                    progress.dismiss();

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

    }
}