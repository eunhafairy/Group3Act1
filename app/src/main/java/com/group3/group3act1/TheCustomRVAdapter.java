package com.group3.group3act1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TheCustomRVAdapter extends RecyclerView.Adapter<TheCustomRVAdapter.ViewHolder> {


    Context c;
    int layout;
    ArrayList<Entry> entryList;

    public TheCustomRVAdapter(Context c, int layout, ArrayList<Entry> entryList) {
        this.c = c;
        this.layout = layout;
        this.entryList = entryList;
    }

    @NonNull
    @Override
    public TheCustomRVAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(c);
        View convertView = inflater.inflate(layout, parent, false);
        ViewHolder vh = new ViewHolder(convertView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Entry entry = entryList.get(position);
        holder.entryImage.setImageBitmap(entry.getEntryImage());
        holder.nameText.setText(entry.getEntryName());
        holder.remarkText.setText(entry.getEntryRemark());


    }

    @Override
    public int getItemCount() {
        return entryList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView entryImage;
        TextView nameText;
        TextView remarkText;
        public ViewHolder(View itemView) {
            super(itemView);
            this.entryImage = itemView.findViewById(R.id.sr_image);
            this.nameText = itemView.findViewById(R.id.sr_name);
            this.remarkText = itemView.findViewById(R.id.sr_remark);
        }
    }
}
