package com.example.application.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.databinding.ItemNoteBinding;
import com.example.application.databinding.ItemPostBinding;
import com.example.application.listactivity.PostAdapter;
import com.example.application.models.pojos.Notes;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{

    ArrayList<Notes> arrayList;

    public NoteAdapter(ArrayList<Notes> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notes note = arrayList.get(position);
        holder.binding.notetitle.setText(note.getTitle());
        holder.binding.notebody.setText(note.getText());
        holder.binding.courseinfo.setText(note.getCourseInfo());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemNoteBinding binding;

        public ViewHolder(@NonNull ItemNoteBinding itemView) {
            super(itemView.getRoot());

            this.binding = itemView;

        }
    }
}
