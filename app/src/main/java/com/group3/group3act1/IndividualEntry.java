package com.group3.group3act1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class IndividualEntry extends AppCompatActivity {

    //textview
    TextView nameText, positionText, birthDateText, genderText, addressText, contactText, hobbyText, otherinfoText;

    //button
    Button backButton;

    //imageview
    ImageView pfpImg, backImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_entry);
        init();
        reg();

    }
    private void init(){
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        Entry individualEntry = extras.getParcelable("Entry");
        //initialize edit text
        nameText = (TextView) findViewById(R.id.i_textName);
        positionText = (TextView) findViewById(R.id.i_textPos);
        birthDateText = (TextView) findViewById(R.id.i_textBday);
        genderText = (TextView) findViewById(R.id.i_textGender);
        addressText = (TextView) findViewById(R.id.i_textAddress);
        contactText = (TextView) findViewById(R.id.i_textContact);
        hobbyText = (TextView) findViewById(R.id.i_textHobbies);
        otherinfoText = (TextView) findViewById(R.id.i_textOther);

        //set text
        nameText.setText(individualEntry.getEntryName().toString());
        positionText.setText(individualEntry.getEntryRemark().toString());
        birthDateText.setText(individualEntry.getBirthdate().toString());
        genderText.setText(individualEntry.getEntryGender().toString());
        addressText.setText(individualEntry.getEntryAddress().toString());
        contactText.setText(individualEntry.getEntryContact().toString());
        hobbyText.setText(individualEntry.getEntryHobbies().toString());
        otherinfoText.setText(individualEntry.getOtherInformation().toString());

        //initialize button
        backButton = (Button) findViewById(R.id.i_btnBack);

        //initialize imageviews
        pfpImg = (ImageView) findViewById(R.id.i_img);
        backImage = (ImageView) findViewById(R.id.i_imgBack);



    }
    private void reg(){

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}