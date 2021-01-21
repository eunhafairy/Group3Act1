package com.group3.group3act1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.BitSet;

public class EntryListScreen extends AppCompatActivity {


    TheCustomRVAdapter theCustomRVAdapter;
    RecyclerView rv1;
    Context c = this;
    Button entry_btn1;
    ArrayList<Entry> entryList = new ArrayList<>();
    final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_list_screen);

        init();
        reg();
    }
    private void init(){


        /*

        set the initial list of recycler view


         */
        String imageUri_Faker = "drawable://fkr.png";
        String imageUri_Huni = "drawable://huni.png";
        String imageUri_Jkcylv = "drawable://jkcylv.png";
        Bitmap bitmap3 = BitmapFactory.decodeFile(imageUri_Jkcylv);
        Bitmap bitmap1 = BitmapFactory.decodeFile(imageUri_Faker);
        Bitmap bitmap2 = BitmapFactory.decodeFile(imageUri_Huni);
        entryList.add(new Entry(bitmap1, "Faker", "Mid Laner","march","M","South Korea",
                "09XXXXXXXXX","Playing Videogames","other information"));
        entryList.add(new Entry(bitmap2, "Huni", "Top Laner","april","M","South Korea",
                "09XXXXXXXXX","Playing Videogames","other information"));
        entryList.add(new Entry(bitmap3, "Jackeylove", "AD Carry","June","M","China",
                "09XXXXXXXXX","Playing Videogames","other information"));

    rv1 = (RecyclerView) findViewById(R.id.rv1);
    rv1.setLayoutManager(new LinearLayoutManager(c));
        theCustomRVAdapter = new TheCustomRVAdapter(c, R.layout.single_row, entryList);
        rv1.setAdapter(theCustomRVAdapter);

        //init button
    entry_btn1 = (Button) findViewById(R.id.entry_btn1);



    }
    private void reg(){

        entry_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(c, AddEntry.class);
                startActivityForResult(i, REQUEST_CODE);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){

            if(data.hasExtra("Entry")){
                Bundle extras = data.getExtras();
                if(extras!=null){

                    Entry _entry = extras.getParcelable("Entry");
                    entryList.add(_entry);
                    theCustomRVAdapter.notifyDataSetChanged();
                    Toast.makeText(c,"item added",Toast.LENGTH_LONG).show();

                }

            }



        }




        }


}