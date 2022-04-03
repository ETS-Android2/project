package com.example.application.listactivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.databinding.ItemPostBinding;
import com.example.application.models.pojos.Post;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>  {


    ArrayList<Post> list = new ArrayList<Post>();
    private OnItemClickListener listener;

    public PostAdapter(ArrayList<Post> postList) {
        this.list = postList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new ViewHolder(ItemPostBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = list.get(position);
        holder.binding.title.setText("Title :"+post.getTitle());
        holder.binding.body.setText("Body :"+post.getBody());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemPostBinding binding;

        public ViewHolder(@NonNull ItemPostBinding itemView) {
            super(itemView.getRoot());
            this.binding =itemView;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(list.get(position));
                    }
                }
            });
        }
    }

    public  interface OnItemClickListener{
        void onItemClick(Post post);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}
