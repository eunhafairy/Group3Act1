package com.group3.group3act1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE_WRITE_READ_PERMISSION = 3;
    final int REQUEST_CODE_FOR_REGISTRATION = 143;
    ArrayList<Account> accountList = new ArrayList<>();
    EditText eTxt_username, eTxt_password;
    Button bt_Login, bt_Reg;
    Context c = this;
    SQLDBHelper myDb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        bt_Login.setOnClickListener(bt_click);
        bt_Reg.setOnClickListener(bt_click);


    }

    private void init() {



        /*
        accountList.add(new Account("Anna", "13579abcdeA", "Anna Lisa"));
        accountList.add(new Account("Lorna", "Th3Q4ickBr0wnF0x", "Lorna Dee"));
        accountList.add(new Account("_Fe_", "p@zzW0rd", "Fe Rari"));

        */



        myDb = new SQLDBHelper(c);
        /*
        Cursor result = myDb.selectAllUsers();
        if(result.getCount() == 0){
            Toast.makeText(c, "No entries", Toast.LENGTH_SHORT).show();

        }
        else{
            while(result.moveToNext()){
                accountList.add(new Account(result.getString(2),
                        result.getString(3),
                        result.getString(1)));
            }
        }

        */

        accountList.add(new Account("debug", "", "Debug"));
        eTxt_username = (EditText) findViewById(R.id.uname);
        eTxt_password = (EditText) findViewById(R.id.pword);
        bt_Login = (Button) findViewById(R.id.loginBtn);
        bt_Reg = (Button) findViewById(R.id.registerBtn);
    }

    View.OnClickListener bt_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(c);


            if (v.getId() == R.id.loginBtn) {

                /*

             if (eTxt_username.getText().toString().equals(accountList.get(0).getUsername()) &&
                     eTxt_password.getText().toString().equals(accountList.get(0).getPassword()))
                      {

                 Toast.makeText(MainActivity.this, "login successful", Toast.LENGTH_SHORT).show();
                 Intent i = new Intent(c, EntryListScreen.class);
                 i.putExtra("Name", accountList.get(0).getName().toString());
                 startActivity(i);
             }
             else if (eTxt_username.getText().toString().equals(accountList.get(1).getUsername()) &&
                     eTxt_password.getText().toString().equals(accountList.get(1).getPassword())){
                 Toast.makeText(MainActivity.this, "login successful", Toast.LENGTH_SHORT).show();
                 Intent i = new Intent(c, EntryListScreen.class);
                 i.putExtra("Name", accountList.get(1).getName().toString());
                 startActivity(i);


                }

             else if(eTxt_username.getText().toString().equals(accountList.get(2).getUsername()) &&
                     eTxt_password.getText().toString().equals(accountList.get(2).getPassword())){
                 Toast.makeText(MainActivity.this, "login successful", Toast.LENGTH_SHORT).show();
                 Intent i = new Intent(c, EntryListScreen.class);
                 i.putExtra("Name", accountList.get(2).getName().toString());
                 startActivity(i);


                }
             else if(eTxt_username.getText().toString().equals(accountList.get(3).getUsername()) &&
                     eTxt_password.getText().toString().equals(accountList.get(3).getPassword())){

                 Toast.makeText(MainActivity.this, "login successful", Toast.LENGTH_SHORT).show();
                 Intent i = new Intent(c, EntryListScreen.class);
                 i.putExtra("Name", accountList.get(3).getName().toString());
                 startActivity(i);
*/

                /*
                for(int i = 0; i < accountList.size(); i++){

                    if(eTxt_username.getText().toString().equals(accountList.get(i).getUsername()) && eTxt_password.getText().toString().equals(accountList.get(i).getPassword())){
                        if(ContextCompat.checkSelfPermission(c, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED &&
                                ContextCompat.checkSelfPermission(c, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                            //ask permission
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                                    REQ_CODE_WRITE_READ_PERMISSION );

                        }
                        else{
                            Toast.makeText(MainActivity.this, "login successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(c, EntryListScreen.class);
                            intent.putExtra("Name", accountList.get(i).getName());
                            startActivity(intent);
                            return;
                        }


                    }
                    else if(i == (accountList.size()-1)){


                        builder.setTitle("Alert")
                                .setMessage("The username or password is incorrect.")
                                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //code
                                    }
                                })
                                .setCancelable(true);
                        AlertDialog dialog = builder.create();
                        dialog.show();

                    }

                }
                */

                Cursor result = myDb.selectAllUsers();
                if(result.getCount() == 0){
                    Toast.makeText(c, "No entries", Toast.LENGTH_SHORT).show();

                }
                else{
                    while(result.moveToNext()){
                      if(result.getString(2).equals(eTxt_username.getText().toString()) && result.getString(3).equals(eTxt_password.getText().toString())){

                          if(ContextCompat.checkSelfPermission(c, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED &&
                                  ContextCompat.checkSelfPermission(c, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                              //ask permission
                              ActivityCompat.requestPermissions(MainActivity.this,
                                      new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                                      REQ_CODE_WRITE_READ_PERMISSION );

                          }
                          else{


                              Toast.makeText(MainActivity.this, "login successful", Toast.LENGTH_SHORT).show();
                              Intent intent = new Intent(c, EntryListScreen.class);
                              intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                              intent.putExtra("Name", result.getString(1));
                              intent.putExtra("accountID", result.getInt(0));
                              startActivity(intent);
                              finish();

                          }


                      }
                      else if (result.isLast()){
                          builder.setTitle("Alert")
                                  .setMessage("The username or password is incorrect.")
                                  .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                      @Override
                                      public void onClick(DialogInterface dialog, int which) {
                                          //code
                                          eTxt_password.setText("");
                                          eTxt_username.setText("");
                                      }
                                  })
                                  .setCancelable(true);
                          AlertDialog dialog = builder.create();
                          dialog.show();

                      }
                    }
                }




             }

            else if (v.getId() == R.id.registerBtn) {




                builder.setTitle("Alert")
                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(c, MainActivity2.class);
                                if(ContextCompat.checkSelfPermission(c, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED &&
                                        ContextCompat.checkSelfPermission(c, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                                    //ask permission
                                    ActivityCompat.requestPermissions(MainActivity.this,
                                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                                            REQ_CODE_WRITE_READ_PERMISSION );

                                }
                                else{

                                    startActivityForResult(i,REQUEST_CODE_FOR_REGISTRATION);
                                }



                            }
                        })
                        .setMessage("Going to registration page");
                AlertDialog dialog = builder.create();
                dialog.show();
            }


        }


    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_FOR_REGISTRATION && resultCode == RESULT_OK){

            /*
            Bundle extras = data.getExtras();
            String _username = extras.getString("Username");
            String _password = extras.getString("Password");
            String _name = extras.getString("Name");
            Account _account = new Account(_username, _password, _name);
            accountList.add(_account);
            */


        }
    }
}



