package com.group3.group3act1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Date;

public class EntryListScreen extends AppCompatActivity {


    TheCustomRVAdapter theCustomRVAdapter;
    RecyclerView rv1;
    Context c = this;
    Button entry_btn1;
    ArrayList<Entry> entryList = new ArrayList<>();
    final int REQUEST_CODE = 1, REQUEST_CODE_FOR_EDIT = 2;
    TextView entryList_title;
    ImageView edtBtn, delBtn, logoff;

    String mCurrentPhotoPath;
    Uri mCurrentPhotoUri;

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
        logoff = (ImageView) findViewById(R.id.logoutImgview);
         //get intent
        Intent i = getIntent();
        String name = i.getStringExtra("Name");
        entryList_title = (TextView) findViewById(R.id.entryList_titleView);
        entryList_title.setText(name);

        /*

        set the initial list of recycler view


         */






//Given samples
        entryList.add(new Entry("res/drawable/fkr.png",
                "Faker",
                "Mid Laner",
                "02/04/1999",
                "M",
                "South Korea",
                "09XXXXXXXXX",
                "Playing Videogames",
                "other information"));








        entryList.add(new Entry("", "Huni", "Top Laner","12/25/1995","M","South Korea",
                "09XXXXXXXXX","Playing Videogames","other information"));
        entryList.add(new Entry("", "Jackeylove", "AD Carry","07/23/2000","M","China",
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

        //for logging out
        logoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                builder.setTitle("Log Out").setMessage("Are you sure you want to log out?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //do nothing
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

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

                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                builder.setTitle("Confirm Delete").setMessage("Are you sure you want to delete "+ entryList.get(position).getEntryName()+"?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //do nothing
                    }
                }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(c,"Successfully deleted: "+entryList.get(position).getEntryName(), Toast.LENGTH_LONG).show();
                        entryList.remove(position);
                        theCustomRVAdapter.notifyItemRemoved(position);
                    }
                }).setCancelable(true);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();


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
                Toast.makeText(c, "To Edit: " +entryList.get(position).getEntryName(), Toast.LENGTH_LONG).show();
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


                    Toast.makeText(c,"SuccessfulH",Toast.LENGTH_LONG).show();
                    entryList.add(newEntry);
                    theCustomRVAdapter.notifyDataSetChanged();


                }
        else if (requestCode == REQUEST_CODE_FOR_EDIT && resultCode == RESULT_OK){
                Toast.makeText(c, "Edit successful", Toast.LENGTH_LONG).show();
                Bundle extras = data.getExtras();
                String _imageEdit = extras.getString("Image");
                String _nameEdit = extras.getString("Name");
                String _remarkEdt = extras.getString("Remark");
                String  _birthdateEdit = extras.getString("Birthday");
                String _genderEdit = extras.getString("Gender");
                String _addressEdit = extras.getString("Address");
                String _contactEdit = extras.getString("Contact");
                String _hobbiessEdit = extras.getString("Hobbies");
                String _otherEdit = extras.getString("Other");
                int position = extras.getInt("Position");
                entryList.get(position).setEntryImage(_imageEdit);
                entryList.get(position).setEntryName(_nameEdit);
                entryList.get(position).setEntryRemark(_remarkEdt);
                entryList.get(position).setBirthdate(_birthdateEdit);
                entryList.get(position).setEntryGender(_genderEdit);
                entryList.get(position).setEntryAddress(_addressEdit);
                entryList.get(position).setEntryContact(_contactEdit);
                entryList.get(position).setEntryHobbies(_hobbiessEdit);
                entryList.get(position).setOtherInformation(_otherEdit);

                theCustomRVAdapter.notifyDataSetChanged();

        }

            }

    private File createImage() throws Exception {
        File tempPhoto = null;

        String timeStamp = new SimpleDateFormat("yyyyMMDD_HHmmss").format(new Date());
        String fileName = "IMG_" + timeStamp +"_";
        File fileDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        tempPhoto = File.createTempFile(fileName,".png",fileDir);
        mCurrentPhotoPath = tempPhoto.getAbsolutePath();

        return tempPhoto;

    }




}
