package com.group3.group3act1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class EntryListScreen extends AppCompatActivity {

    TheCustomRVAdapter theCustomRVAdapter;
    RecyclerView rv1;
    Context c = this;
    Button entry_btn1;
    ArrayList<Entry> entryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_list_screen);

        init();
    }
    private void init(){
        entryList.add(new Entry(R.drawable.fkr, "Faker", "Mid Laner","M","South Korea",
                "09XXXXXXXXX","Playing Videogames","other information"));
        entryList.add(new Entry(R.drawable.huni, "Huni", "Top Laner","M","South Korea",
                "09XXXXXXXXX","Playing Videogames","other information"));
        entryList.add(new Entry(R.drawable.jkcylv, "Jackeylove", "AD Carry","M","China",
                "09XXXXXXXXX","Playing Videogames","other information"));

    rv1 = (RecyclerView) findViewById(R.id.rv1);
    rv1.setLayoutManager(new LinearLayoutManager(c));
        theCustomRVAdapter = new TheCustomRVAdapter(c, R.layout.single_row, entryList);
        rv1.setAdapter(theCustomRVAdapter);
    entry_btn1 = (Button) findViewById(R.id.entry_btn1);



    }
}