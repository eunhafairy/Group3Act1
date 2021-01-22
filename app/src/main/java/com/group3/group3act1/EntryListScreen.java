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
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.BitSet;

public class EntryListScreen extends AppCompatActivity {


    TheCustomRVAdapter theCustomRVAdapter;
    RecyclerView rv1;
    Context c = this;
    Button entry_btn1;
    ArrayList<Entry> entryList = new ArrayList<>();
    final int REQUEST_CODE = 1, REQUEST_CODE_FOR_EDIT = 2;
    TextView entryList_title;
    ImageView edtBtn, delBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_list_screen);

        init();
        reg();
    }
    private void init(){

        //init imgview/button from inflater
        edtBtn = (ImageView) findViewById(R.id.sr_imgEdit);
        delBtn = (ImageView) findViewById(R.id.sr_imgDelete);

        //get intent
        Intent i = getIntent();
        String name = i.getStringExtra("Name");
        entryList_title = (TextView) findViewById(R.id.entryList_titleView);
        entryList_title.setText(name);

        /*

        set the initial list of recycler view


         */
        String imageUri_Faker = "drawable://fkr.png";
        String imageUri_Huni = "drawable://huni.png";
        String imageUri_Jkcylv = "drawable://jkcylv.png";

        entryList.add(new Entry(imageUri_Faker, "Faker", "Mid Laner","march","M","South Korea",
                "09XXXXXXXXX","Playing Videogames","other information"));
        entryList.add(new Entry(imageUri_Huni, "Huni", "Top Laner","april","M","South Korea",
                "09XXXXXXXXX","Playing Videogames","other information"));
        entryList.add(new Entry(imageUri_Jkcylv, "Jackeylove", "AD Carry","June","M","China",
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

        //for deleting

      theCustomRVAdapter.setIndividualScreenListener(new TheCustomRVAdapter.OnIndividualScreen() {
          @Override
          public void convertViewOnIndividualScreen(int position) {
              Entry _entry = entryList.get(position);
              Intent i = new Intent(c, IndividualEntry.class);
              i.putExtra("Entry",(Parcelable) _entry);
              startActivity(i);

          }
      });

        theCustomRVAdapter.setOnConvertViewOnClickLister(new TheCustomRVAdapter.OnConvertViewOnClick() {
            @Override
            public void convertViewOnClickListener(int position) {

                Toast.makeText(c,"Idedelete "+position, Toast.LENGTH_LONG).show();
                entryList.remove(position);
                theCustomRVAdapter.notifyItemRemoved(position);
            }
        });


        //for editing
        theCustomRVAdapter.setOnConvertViewForEditListener(new TheCustomRVAdapter.OnConvertViewOnEdit() {
            @Override
            public void convertViewOnEditListener(int position) {
                Intent i = new Intent(c, EditEntry.class);

                Entry tempEntry = entryList.get(position);
                i.putExtra("Entry",(Parcelable) tempEntry);
                i.putExtra("Position", position);
                startActivityForResult(i, REQUEST_CODE_FOR_EDIT);
                Toast.makeText(c, "ieedit " +position, Toast.LENGTH_LONG).show();
            }
        });





    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){

            String _image = data.getStringExtra("Image");
            String _name = data.getStringExtra("Name");
            String _remark = data.getStringExtra("Position");
            String _bday = data.getStringExtra("Birthday");
            String _gender = data.getStringExtra("Gender");
            String _address = data.getStringExtra("Address");
            String _contact = data.getStringExtra("Contact");
            String _hobbies = data.getStringExtra("Hobbies");
            String _otherInfo = data.getStringExtra("Other");


            Entry newEntry = new Entry(_image,_name,_remark,_bday,_gender,_address,_contact,_hobbies,_otherInfo);


                    Toast.makeText(c,"Successful",Toast.LENGTH_LONG).show();
                    entryList.add(newEntry);
                    theCustomRVAdapter.notifyDataSetChanged();


                }
        else if (requestCode == REQUEST_CODE_FOR_EDIT && resultCode == RESULT_OK){
                Toast.makeText(c, "Edit successful", Toast.LENGTH_LONG).show();
                Bundle extras = data.getExtras();
                String name = extras.getString("Name");
                int position = extras.getInt("Position");
                entryList.get(position).setEntryName(name);
                theCustomRVAdapter.notifyDataSetChanged();

        }

            }



        }
