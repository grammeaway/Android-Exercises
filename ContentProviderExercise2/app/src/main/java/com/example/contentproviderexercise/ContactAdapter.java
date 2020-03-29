package com.example.contentproviderexercise;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private List<ContactModel> data;

    public ContactAdapter(List<ContactModel> data) {
        this.data = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView idTV;
        public TextView lookupTV;
        public TextView nameTV;
        public View frameLayout;

        public ViewHolder(View frameLayout, TextView idTV, TextView lookupTV, TextView nameTV) {
            super(frameLayout);
            this.idTV = idTV;
            this.lookupTV = lookupTV;
            this.nameTV = nameTV;
        }
    }


    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_view, parent, false);
        TextView tv1 = v.findViewById(R.id.idTV);
        TextView tv2 = v.findViewById(R.id.lookupKeyTV);
        TextView tv3 = v.findViewById(R.id.nameTV);
        //Terrible Naming Inc., I know
        final ContactAdapter.ViewHolder vh = new ViewHolder(v, tv1,tv2,tv3);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {
        ContactModel contact = data.get(position);
        holder.idTV.setText(contact.getId());
        holder.nameTV.setText(contact.getName());
        holder.lookupTV.setText(contact.getLookupKey());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}

