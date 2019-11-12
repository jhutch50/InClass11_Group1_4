package com.example.inclass11_group1_4;

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
        String[] arrOfStr = imageList.get(position).split("_");
        holder.pos = arrOfStr[0];
        String urltoImage = arrOfStr[1];
        Picasso.get().load(urltoImage).into(holder.imageView2);

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
            imageView2.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseStorage firebaseStorage = FirebaseStorage.getInstance("gs://inclass11-cc638.appspot.com");
                    StorageReference storageReference = firebaseStorage.getReference();
                    StorageReference desertRef = storageReference.child("images/"+pos);
                    Log.d("demo",pos);
                    desertRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            imageView2.setImageDrawable(null);
                            // File deleted successfully
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Uh-oh, an error occurred!
                        }
                    });
                }
            });
        }
    }
}
