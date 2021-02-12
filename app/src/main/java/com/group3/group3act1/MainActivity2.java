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
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.media.MediaActionSound;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.JsonReader;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity {


    //database
    SQLDBHelper db;

    //request code
    final int REQUEST_CODE_CAMERA = 2;
    //arrays

    String errorMessage="", registrationMessage = "", gender ="", hobbies = "";
    String imagePath="";
    String _brgys[] = {"Sta. Barbara", "Tarcan", "Makinabang", "Concepcion", "Poblacion", "Pinagbarilan", "Matang Tubig","Barangay 143","Tumana","San Jose"};
    String _municipalities[] = {"Baliwag", "Plaridel", "Pulilan", "Sta. Maria", "Bustos", "San Rafael", "San Ildefonso","Cainta","Kawit","Meycauayan"};
    String _province[] = {"Bulacan", "Pampanga", "Nueva Ecija", "Tarlac", "Aurora", "Ilocos Sur", "Ilocos Norte","Metro Manila","Quezon City","Davao"};
    String securityQuestions[] = {"---","What is the first name of your mother?", "What is the name of your first dog?", "What is the name of your elementary school?", "What is your bestfriend's name?", "What is your favourite colour?", };
    String _CurrentPhotoPath;
    Uri _CurrentPhotoUri;
    //datepicker
    DatePickerDialog datePickerDialog;


    //context
    Context c = this;

    //spinner
    Spinner spnr1, spnr2, spnr3;

    //button
    Button registerButton,backButton;

    //ImageView
    ImageView imgBack, imgpfp;
    //radio
    RadioGroup rb_group;
    RadioButton rb_male, rb_female, rb_others;

    //edit text
    EditText eTxtnum,eTxt_un, eTxt_pw,eTxt_cpw, eTxt_firstname, eTxt_mname, eTxt_lname, editText_bday, spnr1_eText,spnr2_eText,spnr3_eText, other_eTxt;

    //autocomplete view
    AutoCompleteTextView house, street, brgy, municipality, province;

    //checkboxes
    CheckBox cBox_dance, cBox_sing,cBox_games,cBox_sports,cBox_draw,cBox_paint,cBox_cook,cBox_study,cBox_design,cBox_code;

    //permission
    final int REQ_CODE_WRITE_READ_PERMISSION = 1010;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
        regList();
    }


    /*
    *
    * FOR INITIALIZATION
    *
    *
    */

    private void init(){
        //radio group and button
        rb_group = (RadioGroup) findViewById(R.id.rb_g);
        rb_female = (RadioButton) findViewById(R.id.rb_f);
        rb_male = (RadioButton) findViewById(R.id.rb_m);
        rb_others = (RadioButton) findViewById(R.id.rb_o);

        //database
        db = new SQLDBHelper(c);

        //edit texts
        eTxt_un = (EditText) findViewById(R.id.eText_uname);
        eTxt_pw = (EditText) findViewById(R.id.eText_pword);
        eTxt_cpw = (EditText) findViewById(R.id.eText_cpword);
        eTxt_firstname = (EditText) findViewById(R.id.eText_fname);
        eTxt_mname = (EditText) findViewById(R.id.eText_mname);
        eTxt_lname = (EditText) findViewById(R.id.eText_lname);
        other_eTxt = (EditText) findViewById(R.id.rb_other_eTxt);
        eTxtnum = (EditText) findViewById(R.id.eTxt_contact);

        spnr1_eText = (EditText) findViewById(R.id.spnr1_eTxt);
        spnr2_eText = (EditText) findViewById(R.id.spnr2_eTxt);
        spnr3_eText = (EditText) findViewById(R.id.spnr3_eTxt);

        //autocomplete textview
        house = (AutoCompleteTextView) findViewById(R.id.atv_house);
        street = (AutoCompleteTextView) findViewById(R.id.atv_Street);
        brgy = (AutoCompleteTextView) findViewById(R.id.atv_brgy);
        municipality = (AutoCompleteTextView) findViewById(R.id.atv_muni);
        province = (AutoCompleteTextView) findViewById(R.id.atv_prov);

        //spinners
        spnr1 = (Spinner) findViewById(R.id.spnr_1);
        spnr2 = (Spinner) findViewById(R.id.spnr_2);
        spnr3 = (Spinner) findViewById(R.id.spnr_3);

        //checkboxes
        cBox_dance = (CheckBox) findViewById(R.id.cb_dance);
        cBox_sing = (CheckBox) findViewById(R.id.cb_sing);
        cBox_games = (CheckBox) findViewById(R.id.cb_Games);
        cBox_sports = (CheckBox) findViewById(R.id.cb_sports);
        cBox_draw = (CheckBox) findViewById(R.id.cb_draw);
        cBox_paint = (CheckBox) findViewById(R.id.cb_paint);
        cBox_code = (CheckBox) findViewById(R.id.cb_code);
        cBox_design = (CheckBox) findViewById(R.id.cb_design);
        cBox_cook = (CheckBox) findViewById(R.id.cb_cook);
        cBox_study = (CheckBox) findViewById(R.id.cb_study);




        //button
        registerButton = (Button) findViewById(R.id.reg_btn);
        backButton = (Button) findViewById(R.id.back_btn);

        //imageview
        imgBack = (ImageView) findViewById(R.id.imgBack);
        imgpfp = (ImageView) findViewById(R.id.imgpfp);

        //adapters
            //for autocomplete
            ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(c, android.R.layout.simple_list_item_1, _brgys);
            brgy.setAdapter(brgyAdapter);
            ArrayAdapter<String> muniAdapter = new ArrayAdapter<>(c, android.R.layout.simple_list_item_1, _municipalities);
            municipality.setAdapter(muniAdapter);
            ArrayAdapter<String> provinceAdapter = new ArrayAdapter<>(c, android.R.layout.simple_list_item_1, _province);
            province.setAdapter(provinceAdapter);

            //for spinner
            ArrayAdapter<String> securityQuestion = new ArrayAdapter<>(c, android.R.layout.simple_spinner_item, securityQuestions);
            securityQuestion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnr1.setAdapter(securityQuestion);
            spnr1.setSelection(0);
            spnr2.setAdapter(securityQuestion);
            spnr1.setSelection(0);
            spnr3.setAdapter(securityQuestion);
            spnr1.setSelection(0);

        editText_bday =(EditText) findViewById(R.id.bday);
        datePickerDialog = new DatePickerDialog(c, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                editText_bday.setText((month+1)+"/"+(dayOfMonth)+"/"+(year));
            }
        },1990,1,1);
    }

    /*
    *
    *   EVENT LISTENERS
    *
    * */

    //for registering listeners
    private void regList(){
    AlertDialog.Builder builder = new AlertDialog.Builder(c);

        //register button
       registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmpty(eTxt_un)){
                   errorMessage += "Username\n";
                }
               if(isEmpty(eTxt_pw)){
                    errorMessage += "Password\n";

                }
                if(isEmpty(eTxt_cpw)){
                    errorMessage += "Confirm your password\n";


                }
                if(isEmpty(eTxt_firstname)){
                    errorMessage += "Your first name\n";

                }
                if(isEmpty(eTxt_lname)){
                    errorMessage += "Your last name\n";


                }
                if(isEmpty(editText_bday)){
                    errorMessage += "Birthdate\n";

                }
                if(rb_group.getCheckedRadioButtonId() == -1){
                    errorMessage += "Gender\n";


                }
                if(isEmpty(house)){
                    errorMessage += "House number\n";

                }
                if(isEmpty(street)){
                    errorMessage += "Street\n";


                }
                if(isEmpty(brgy)){
                    errorMessage += "Barangay\n";

                }
                if(isEmpty(municipality)){
                    errorMessage += "Municipality\n";

                }
                if(isEmpty(province)){
                    errorMessage += "Province:\n";

                }
                if(isEmpty(eTxtnum)){
                    errorMessage += "Contact number:\n";

                }
                if(!cBox_dance.isChecked()&&!cBox_sing.isChecked()&&!cBox_draw.isChecked()&&!cBox_games.isChecked()&&!cBox_sports.isChecked()&&
                   !cBox_paint.isChecked()&&!cBox_code.isChecked()&&!cBox_study.isChecked()&&!cBox_cook.isChecked()&&!cBox_design.isChecked()){

                    errorMessage += "Hobby/Hobbies\n";

                }
                if(isEmpty(spnr1_eText)){
                    errorMessage += "Security Question 1\n";

                }
                if(isEmpty(spnr2_eText)){
                    errorMessage += "Security Question 2\n";

                }
                if(isEmpty(spnr3_eText)){
                    errorMessage += "Security Question 3\n";

                }

                if(!eTxt_pw.getText().toString().equals(eTxt_cpw.getText().toString())){
                    errorMessage="";
                    builder.setTitle("Error")
                            .setMessage("Your password does not match!")
                            .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //code here
                                }
                            })
                            .setCancelable(true);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return;


                }
                if(errorMessage.trim().length() > 0){
                    builder.setTitle("Error")
                            .setMessage("Plese enter the following:\n"+errorMessage)
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



                }
                else{

                    hobbies="";
                    if(cBox_design.isChecked()){
                        hobbies+=cBox_design.getText().toString()+" ";

                    }
                    if(cBox_study.isChecked()){
                        hobbies+=cBox_study.getText().toString()+" ";

                    }
                    if(cBox_paint.isChecked()){
                        hobbies+=cBox_paint.getText().toString()+" ";

                    }
                    if(cBox_sports.isChecked()){
                        hobbies+=cBox_sports.getText().toString()+" ";

                    }
                    if(cBox_games.isChecked()){
                        hobbies+=cBox_games.getText().toString()+" ";

                    }
                    if(cBox_dance.isChecked()){
                        hobbies+=cBox_dance.getText().toString()+" ";

                    }
                    if(cBox_code.isChecked()){
                        hobbies+=cBox_code.getText().toString()+" ";

                    }
                    if(cBox_cook.isChecked()){

                        hobbies+=cBox_cook.getText().toString()+" ";

                    }
                    if(cBox_draw.isChecked()){
                        hobbies+=cBox_draw.getText().toString()+" ";

                    }
                    if(cBox_sing.isChecked()){
                        hobbies+=cBox_sing.getText().toString()+" ";

                    }

                    if(rb_group.getCheckedRadioButtonId()==R.id.rb_o){
                        gender=other_eTxt.getText().toString();
                    }

                    //INSERT INTO DATABASE
                    if(db.insertIntoUserTable(eTxt_firstname.getText().toString(),
                            eTxt_un.getText().toString(),
                            eTxt_pw.getText().toString(),
                            editText_bday.getText().toString(),
                            house.getText().toString()+" "+street.getText().toString()+", "+brgy.getText().toString()+", "+municipality.getText().toString()+", "
                                    +province.getText().toString(),
                            gender,
                            hobbies,
                            eTxtnum.getText().toString(),
                            spnr1_eText.getText().toString(),
                            spnr2_eText.getText().toString(),
                            spnr3_eText.getText().toString(),
                            _CurrentPhotoPath)){

                        Toast.makeText(c,"Registration Successful",Toast.LENGTH_LONG).show();
                        registrationMessage = "Username: "+eTxt_un.getText().toString()+"\nFullname: "+eTxt_firstname.getText().toString()+" "+eTxt_mname.getText().toString()+" "
                                +eTxt_lname.getText().toString()+"\nBirthdate: "+editText_bday.getText().toString()+"\nGender: "+gender+"\nComplete Address: "
                                +house.getText().toString()+" "+street.getText().toString()+", "+brgy.getText().toString()+", "+municipality.getText().toString()+", "
                                +province.getText().toString()+"\nContact Number: "+eTxtnum.getText().toString()+"\nHobbies: "+hobbies+"\n--Security Questions--\n"+"1. "+spnr1.getSelectedItem().toString()+
                                "\nAnswer: "+spnr1_eText.getText().toString()+"\n2. "+spnr2.getSelectedItem().toString()+"\nAnswer: "+spnr2_eText.getText().toString()+
                                "\n3. "+spnr3.getSelectedItem().toString()+"\nAnswer: "+spnr3_eText.getText().toString();




                        builder.setTitle("Successful")
                                .setMessage(registrationMessage)
                                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        Intent data = new Intent();
                                        errorMessage = "";
                                        setResult(RESULT_OK, data);
                                        finish();

                                    }
                                })
                                .setCancelable(true);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        errorMessage = "";



                    }






                }

            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(c, MainActivity.class);
                startActivity(i);
            }
        });

       //image view
       imgBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(c, MainActivity.class);
               startActivity(i);
           }
       });
       imgpfp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if(ContextCompat.checkSelfPermission(c, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                   ActivityCompat.requestPermissions(MainActivity2.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_CAMERA);


               }
               else{
                   Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                   File tempImage = null;
                   try {
                       tempImage = createImage();
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
                   if(tempImage != null) {
                       Uri uriImage = FileProvider.getUriForFile(c,
                               "com.group3.group3act1.fileprovider",
                               tempImage);
                       _CurrentPhotoUri = uriImage;
                       camera.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
                       startActivityForResult(camera, REQUEST_CODE_CAMERA);


                   }
      }

           }
       });


        //spinner
        spnr1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView selected = (TextView) view;
                if(spnr1.getSelectedItemPosition()==0){
                    spnr1_eText.setEnabled(false);
                }
               else if(spnr1.getSelectedItemPosition()!=0&&(spnr2.getSelectedItem().toString().equals(selected.getText())||spnr3.getSelectedItem().toString().equals(selected.getText()))){

                    builder.setTitle("Same Question")
                           .setMessage("Please choose another question")
                           .setCancelable(true)
                           .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   //code here
                               }
                           });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    spnr3_eText.setText("");
                    spnr1.setSelection(0);

                }
               else{

                   spnr1_eText.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnr2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView selected = (TextView) view;
                if(spnr2.getSelectedItemPosition()==0){
                    spnr2_eText.setEnabled(false);
                }
                else if(spnr2.getSelectedItemPosition()!=0&&(spnr1.getSelectedItem().toString().equals(selected.getText())||spnr3.getSelectedItem().toString().equals(selected.getText()))){

                    builder.setTitle("Same Question")
                            .setMessage("Please choose another question")
                            .setCancelable(true)
                            .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //code here
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    spnr2_eText.setText("");
                    spnr2.setSelection(0);

                }
                else{
                    spnr2_eText.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnr3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView selected = (TextView) view;

                if(spnr3.getSelectedItemPosition()==0){
                    spnr3_eText.setEnabled(false);
                }
                else if(spnr3.getSelectedItemPosition()!=0&&(spnr1.getSelectedItem().toString().equals(selected.getText())||
                        spnr2.getSelectedItem().toString().equals(selected.getText()))){

                    builder.setTitle("Same Question")
                            .setMessage("Please choose another question")
                            .setCancelable(true)
                            .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //code here
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    spnr3.setSelection(0);
                    spnr3_eText.setText("");
                    spnr3_eText.setEnabled(false);


                }
                else{

                    spnr3_eText.setEnabled(true);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        //radio group
        rb_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                other_eTxt.setText("");
                other_eTxt.setEnabled(false);
                gender = rb_male.getText().toString();
            }
        });
        rb_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                other_eTxt.setText("");
                other_eTxt.setEnabled(false);
                gender = rb_female.getText().toString();
            }
        });
        rb_others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = other_eTxt.getText().toString();
              other_eTxt.setEnabled(true);
            }
        });

        //datepicker
        editText_bday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

    }





    private boolean isEmpty(EditText etxt){
        {
            if(etxt.getText().toString().trim().length()>0)
            {
                return false;
            }


            return true;


        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK){
          imgpfp.setImageBitmap(BitmapFactory.decodeFile(_CurrentPhotoPath));

        }
    }




    private File createImage() throws Exception {
        File tempPhoto = null;

        if(ContextCompat.checkSelfPermission(c, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(c, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            //ask permission
            ActivityCompat.requestPermissions(MainActivity2.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQ_CODE_WRITE_READ_PERMISSION );

        }
        else{

            String timeStamp = new SimpleDateFormat("yyyyMMDD_HHmmss").format(new Date());
            String fileName = "IMG_" + timeStamp +"_";
            File fileDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

            tempPhoto = File.createTempFile(fileName,".jpg",fileDir);
            _CurrentPhotoPath = tempPhoto.getAbsolutePath();


        }
        return tempPhoto;

    }

}
