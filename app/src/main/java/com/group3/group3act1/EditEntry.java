package com.group3.group3act1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class EditEntry extends AppCompatActivity {
    Entry tempEntry;
    int position;
    Context c = this;
    //TextViews
    EditText name, remark, birthday, address, contact, hobby, otherinfo;
    //Image View
    ImageView pfp;
    //Button
    Button sbmtBtn;

    //Radio
    RadioGroup rg_editEntry;
    RadioButton rb_Female, rb_Male, rb_Others;
    String gender = "";

    //Date
    DatePickerDialog datePickerDialog;

    //REQUEST CODE
    final int REQUEST_CODE_CAMERA_ADD_ENTRY = 12;

    //String to hold error message
    String errorMessage = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry);

        init();
        reg();

    }

    private void init() {


        Intent i = getIntent();
        Bundle extras = i.getExtras();
        position = extras.getInt("Position");
        tempEntry = extras.getParcelable("Entry");
        //Initialize TextViews
        name = (EditText) findViewById(R.id.editEntry_nameEdit);
        remark = (EditText) findViewById(R.id.editEntry_remarkEdit);
        birthday = (EditText) findViewById(R.id.editEntry_bdayEdit);
        address = (EditText) findViewById(R.id.editEntry_addressEdit);
        contact = (EditText) findViewById(R.id.editEntry_contactEdit);
        hobby = (EditText) findViewById(R.id.editEntry_hobbyEdit);
        otherinfo = (EditText) findViewById(R.id.editEntry_otherInfoEdit);

        //set edit tex to the selected entry's value
        name.setText(tempEntry.getEntryName());
        remark.setText(tempEntry.getEntryRemark());
        birthday.setText(tempEntry.getBirthdate());
        gender = tempEntry.getEntryGender();
        address.setText(tempEntry.getEntryAddress());
        contact.setText(tempEntry.getEntryContact());
        hobby.setText(tempEntry.getEntryHobbies());
        otherinfo.setText(tempEntry.getOtherInformation());


        //Radio
        rg_editEntry = (RadioGroup) findViewById(R.id.editEntry_rg);
        rb_Female = (RadioButton) findViewById(R.id.editEntry_rbFemale);
        rb_Male = (RadioButton) findViewById(R.id.editEntry_rbMale);
        rb_Others = (RadioButton) findViewById(R.id.editEntry_rbOthers);

        //set gender
        if(gender.equals("M")|| gender.equals("Male")){
         rg_editEntry.check(rb_Male.getId());

        }
        else if(gender.equals("F") || gender.equals("Female")){
            rg_editEntry.check(rb_Female.getId());

        }
        else{
            rg_editEntry.check(rb_Others.getId());

        }


        //date picker
        datePickerDialog = new DatePickerDialog(c, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                birthday.setText((month + 1) + "/" + (dayOfMonth) + "/" + (year));
            }
        }, 1990, 1, 1);

        //imageview
        pfp = (ImageView) findViewById(R.id.editEntry_imgView);

        //Button
        sbmtBtn = (Button) findViewById(R.id.editEntry_submitButton);


    }

    private void reg() {


        AlertDialog.Builder builder = new AlertDialog.Builder(c);

        //submit button
        sbmtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty(name)) {
                    errorMessage += "Name\n";

                }
                if (isEmpty(remark)) {
                    errorMessage += "Position\n";

                }
                if (isEmpty(birthday)) {
                    errorMessage += "Birthdate\n";

                }
                if (rg_editEntry.getCheckedRadioButtonId() == -1) {
                    errorMessage += "Gender\n";

                }
                if (isEmpty(hobby)) {
                    errorMessage += "Hobbies\n";

                }
                if (errorMessage.trim().length() > 0) {
                    builder.setTitle("Error")
                            .setMessage("Plese enter the following:\n" + errorMessage)
                            .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //code here
                                }
                            })
                            .setCancelable(true);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    errorMessage = "";


                } else {

                    Intent data = new Intent();
                    data.putExtra("Image", "");
                    data.putExtra("Name", name.getText().toString());
                    data.putExtra("Remark", remark.getText().toString());
                    data.putExtra("Birthday", birthday.getText().toString());
                    data.putExtra("Gender", gender);
                    data.putExtra("Address", address.getText().toString());
                    data.putExtra("Contact", contact.getText().toString());
                    data.putExtra("Hobbies", hobby.getText().toString());
                    data.putExtra("Other", otherinfo.getText().toString());
                    data.putExtra("Position", position);

                    errorMessage = "";
                    setResult(RESULT_OK, data);
                    finish();

                }
            }
        });


        pfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, REQUEST_CODE_CAMERA_ADD_ENTRY);
            }
        });

        //for gender
        rb_Female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "Female";
            }
        });
        rb_Male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "Male";
            }
        });
        rb_Others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "Others";
            }
        });


        //for bday
        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CAMERA_ADD_ENTRY && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap img = (Bitmap) extras.get("data");
            pfp.setImageBitmap(img);

        }
    }

    //to check if edit text is empty
    private boolean isEmpty(EditText etxt)
        {
            if (etxt.getText().toString().trim().length() > 0) {
                return false;
            }
        return true;

        }


}