package com.example.recyclerviewexercise;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<Integer> data;

    public CustomAdapter(List<Integer> data) {
        this.data = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public View frameLayout;

        public ViewHolder(View frameLayout, TextView v) {
            super(frameLayout);
            textView = v;
        }
    }


    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_textview, parent, false);
        TextView tv = v.findViewById(R.id.test_tv);
        final CustomAdapter.ViewHolder vh = new ViewHolder(v, tv);
        vh.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(vh.getAdapterPosition(),v);
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        holder.textView.setText(data.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void deleteItem(final int position, View v){
        String message = String.format("Removing item at position: %s with value: %s",position,data.get(position));
        final int number = data.get(position);
        Snackbar snack = Snackbar.make(v,message, BaseTransientBottomBar.LENGTH_LONG);
        snack.setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(position,number);
                notifyDataSetChanged();
            }
        });
        snack.show();
        data.remove(position);
        notifyDataSetChanged();
    }

}
