package com.example.inclass11_group1_4;

import android.app.ProgressDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static androidx.recyclerview.widget.RecyclerView.*;

public class imageAdaptor extends RecyclerView.Adapter<imageAdaptor.ViewHolder> {
    ArrayList<String> imageList;
    private OnImageClickListener onImageClickListener;
    public imageAdaptor(ArrayList<String> imageList,OnImageClickListener onImageClickListener) {
        this.imageList = imageList;
        this.onImageClickListener = onImageClickListener;
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
    public void onBindViewHolder(@NonNull final imageAdaptor.ViewHolder holder, final int position) {
        String[] arrOfStr = imageList.get(position).split("_");
        holder.pos = arrOfStr[0];
        String urltoImage = arrOfStr[1];
        Picasso.get().load(urltoImage).into(holder.imageView2);
        holder.imageView2.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onImageClickListener.onImageClick(holder.pos,position);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView2;
        String pos;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView2 = itemView.findViewById(R.id.imageView);
        }
    }

    public static interface OnImageClickListener{
        void onImageClick(String name,int position);
    }
}
