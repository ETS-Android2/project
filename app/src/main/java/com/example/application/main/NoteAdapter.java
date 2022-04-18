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

    public void addNotes(ArrayList<Notes> arrayList){
        this.arrayList = arrayList;
        notifyDataSetChanged();
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
        holder.binding.notetitle.setText("Title :"+note.getTitle());
        holder.binding.notebody.setText("Body :"+note.getText());
        holder.binding.courseinfo.setText("Course :"+note.getCourseInfo());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void remove(int position) {
            arrayList.remove(position);
            notifyItemRemoved(position);
    }

    public void add(int position, Notes deleted) {
        arrayList.add(position,deleted);
        notifyItemChanged(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder{

        ItemNoteBinding binding;

        public ViewHolder(@NonNull ItemNoteBinding itemView) {
            super(itemView.getRoot());

            this.binding = itemView;

        }

        @Override
        public void onItemSelected() {
            itemView.animate()
                    .alpha(0.7f)
                    .scaleX(0.9f)
                    .scaleY(0.9f)
                    .setDuration(500);
        }

        @Override
        public void onItemClear() {
            itemView.animate()
                    .alpha(1f)
                    .scaleX(1f)
                    .scaleY(1f);
        }
    }


    public interface ItemTouchHelperViewHolder {
        void onItemSelected();
        void onItemClear();
    }
}
