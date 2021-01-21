package com.group3.group3act1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AddEntry extends AppCompatActivity {


    Context c = this;
    //button
    Button submitBtn;
    //edit text
    EditText name, position, birthDate, address, contactInfo, hobbies, otherInfo;
    RadioGroup gender;
    RadioButton male, female, other;

    //datepicker
    DatePickerDialog datePickerDialog;

    //imageview
    ImageView imgView;
    Bitmap img;
    //request code
    final int REQUEST_CODE_CAMERA_ADD_ENTRY = 3;

    //string
    String genderHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        init();
        reg();

    }
    private void init(){

        //init button
        submitBtn = (Button) findViewById(R.id.addEntry_submitButton);

        //init edit text
        name = (EditText) findViewById(R.id.addEntry_nameEdit);
        position = (EditText) findViewById(R.id.addEntry_remarkEdit);
        birthDate = (EditText) findViewById(R.id.addEntry_bdayEdit);
        address = (EditText) findViewById(R.id.addEntry_addressEdit);
        contactInfo = (EditText) findViewById(R.id.addEntry_contactEdit);
        hobbies = (EditText) findViewById(R.id.addEntry_hobbyEdit);
        otherInfo = (EditText) findViewById(R.id.addEntry_otherInfoEdit);

        //init radio group and buttons
        gender = (RadioGroup) findViewById(R.id.addentry_rg);
        male = (RadioButton) findViewById(R.id.addEntry_rbMale);
        female = (RadioButton) findViewById(R.id.addEntry_rbFemale);
        other = (RadioButton) findViewById(R.id.addEntry_rbOthers);

        //image view
        imgView = (ImageView) findViewById(R.id.addEntry_imgView);
        img = BitmapFactory.decodeFile("/");

        //for bday datepicker

        datePickerDialog = new DatePickerDialog(c, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                birthDate.setText((month+1)+"/"+(dayOfMonth)+"/"+(year));
            }
        },1990,1,1);

    }
    private  void reg(){


        //profile pic
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, REQUEST_CODE_CAMERA_ADD_ENTRY);
            }
        });

        //submit button
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Entry newEntry = new Entry(img, name.getText().toString(),position.getText().toString(), birthDate.getText().toString(),
                        genderHolder, address.getText().toString(), contactInfo.getText().toString(), hobbies.getText().toString(),
                        otherInfo.getText().toString());
                Intent data = new Intent();
                data.putExtra("image", img);
                data.putExtra("Entry", (Parcelable) newEntry);
                setResult(RESULT_OK, data);
                finish();


                }


        });


        //radio group
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genderHolder = male.getText().toString();
            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genderHolder = female.getText().toString();
            }
        });
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genderHolder = other.getText().toString();
            }
        });

        //datepickerdialog show
        birthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_CAMERA_ADD_ENTRY && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            img = (Bitmap) extras.get("data");
            imgView.setImageBitmap(img);


        }
    }
}