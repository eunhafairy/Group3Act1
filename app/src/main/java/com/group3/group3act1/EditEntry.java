package com.group3.group3act1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditEntry extends AppCompatActivity {
    private static final int REQ_CODE_WRITE_READ_PERMISSION = 123;
    private static final int REQ_CODE_CAMERA = 124;
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

    //Strings and bool
    String errorMessage = "";
    Uri mCurrentPhotoUri;
    String mCurrentPhotoPath = "";
    boolean flag = false;

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
        pfp = (ImageView) findViewById(R.id.editEntry_imgView);
        name = (EditText) findViewById(R.id.editEntry_nameEdit);
        remark = (EditText) findViewById(R.id.editEntry_remarkEdit);
        birthday = (EditText) findViewById(R.id.editEntry_bdayEdit);
        address = (EditText) findViewById(R.id.editEntry_addressEdit);
        contact = (EditText) findViewById(R.id.editEntry_contactEdit);
        hobby = (EditText) findViewById(R.id.editEntry_hobbyEdit);
        otherinfo = (EditText) findViewById(R.id.editEntry_otherInfoEdit);

        //set edit tex to the selected entry's value
        pfp.setImageBitmap(BitmapFactory.decodeFile(tempEntry.getEntryImage()));
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
                    if (flag == false){
                    data.putExtra("Image", tempEntry.getEntryImage());

                    }
                    else{
                        data.putExtra("Image", mCurrentPhotoPath);

                    }




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
                flag = true;
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                File tempImage = null;
                try {
                    tempImage = createImage();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (tempImage != null) {
                    Uri uriImage = FileProvider.getUriForFile(c,
                            "com.group3.group3act1.fileprovider",
                            tempImage);
                    mCurrentPhotoUri = uriImage;
                    camera.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);

                    if (ContextCompat.checkSelfPermission(c, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(EditEntry.this, new String[]{Manifest.permission.CAMERA}, REQ_CODE_CAMERA);


                    } else {

                        startActivityForResult(camera, REQUEST_CODE_CAMERA_ADD_ENTRY);
                    }

                }
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
            pfp.setImageBitmap(BitmapFactory.decodeFile(mCurrentPhotoPath));
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
        private File createImage() throws Exception {
            File tempPhoto = null;

            if(ContextCompat.checkSelfPermission(c, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(c, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                //ask permission
                ActivityCompat.requestPermissions(EditEntry.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQ_CODE_WRITE_READ_PERMISSION );

            }
            else{

                String timeStamp = new SimpleDateFormat("yyyyMMDD_HHmmss").format(new Date());
                String fileName = "IMG_" + timeStamp +"_";
                File fileDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

                tempPhoto = File.createTempFile(fileName,".jpg",fileDir);
                mCurrentPhotoPath = tempPhoto.getAbsolutePath();


            }
            return tempPhoto;

        }


}