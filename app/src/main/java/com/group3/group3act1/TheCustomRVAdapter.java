package com.group3.group3act1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TheCustomRVAdapter extends RecyclerView.Adapter<TheCustomRVAdapter.ViewHolder> {


    Context c;
    int layout;
    ArrayList<Entry> entryList;
    OnConvertViewOnClick listener;
    OnConvertViewOnEdit listenerEdit;
    OnIndividualScreen listenerIndividual;
    //from singe_row




    public TheCustomRVAdapter(Context c, int layout, ArrayList<Entry> entryList) {
        this.c = c;
        this.layout = layout;
        this.entryList = entryList;
    }

    public interface OnIndividualScreen{
        void convertViewOnIndividualScreen(int position);

    }
    public void setIndividualScreenListener(OnIndividualScreen listenerGalingSaIndividualScreen) {
        listenerIndividual = listenerGalingSaIndividualScreen;
    }

    public interface OnConvertViewOnEdit{
        void convertViewOnEditListener(int position);

    }
    public void setOnConvertViewForEditListener(OnConvertViewOnEdit listenerGalingsaEdit) {
        listenerEdit = listenerGalingsaEdit;
    }


    public interface OnConvertViewOnClick{
        void convertViewOnClickListener(int position);

    }
    public void setOnConvertViewOnClickLister(OnConvertViewOnClick listenerGalingSaActivity) {
        listener = listenerGalingSaActivity;

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
        Bitmap bitmap = BitmapFactory.decodeFile(entry.getEntryImage());
        holder.entryImage.setImageBitmap(bitmap);
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
        ImageView imgDel, imgEdit;




        public ViewHolder(View itemView) {
            super(itemView);
            this.entryImage = itemView.findViewById(R.id.sr_image);
            this.nameText = itemView.findViewById(R.id.sr_name);
            this.remarkText = itemView.findViewById(R.id.sr_remark);
            this.imgDel =  itemView.findViewById(R.id.sr_imgDelete);
            this.imgEdit = itemView.findViewById(R.id.sr_imgEdit);


            imgDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    listener.convertViewOnClickListener(position);
                }
            });

            imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    listenerEdit.convertViewOnEditListener(position);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    listenerIndividual.convertViewOnIndividualScreen(position);



                }
            });

            /*
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                        if(v.getId() == R.id.sr_imgDelete){
                            listener.convertViewOnClickListener(position);

                        }
                        if(v.getId() == R.id.sr_imgEdit){
                            listenerEdit.convertViewOnEditListener(position);

                        }

                }
            });*/



        }
    }
}
