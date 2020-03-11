package com.example.randompictureapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    List<String> imgUrlList;


    public ImageAdapter(int pictureAmount) {
        imgUrlList = initUrlList(pictureAmount);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgView;
        public View frameLayout;

        public ViewHolder(View frameLayout, ImageView v) {
            super(frameLayout);
            imgView = v;
        }
    }

    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_imgview, parent, false);
        ImageView tv = v.findViewById(R.id.imgView);
        final ImageAdapter.ViewHolder vh = new ViewHolder(v, tv);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder holder, int position) {
        String urlString = imgUrlList.get(position);
        ImageView imgView = holder.imgView;

        Glide.with(imgView).load(urlString) .into(imgView);
    }

    @Override
    public int getItemCount() {
        return imgUrlList.size();
    }


    private List<String> initUrlList(int amount) {
        String baseString = "https://picsum.photos/500/500?image=";
        List<String> urlList = new ArrayList<>();

        for (int i = 1; i <= amount; i++) {
            urlList.add(baseString + i);
        }
        return urlList;
    }
}
