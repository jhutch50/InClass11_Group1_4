package com.example.inclass11_group1_4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static androidx.recyclerview.widget.RecyclerView.*;

public class imageAdaptor extends RecyclerView.Adapter<imageAdaptor.ViewHolder> {
    ArrayList<String> imageList;
    public imageAdaptor(ArrayList<String> imageList) {
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public imageAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_list, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull imageAdaptor.ViewHolder holder, int position) {
        String urltoImage = imageList.get(position);
        Picasso.get().load(urltoImage).into(holder.imageView2);

    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView2 = itemView.findViewById(R.id.imageView);

        }
    }
}
