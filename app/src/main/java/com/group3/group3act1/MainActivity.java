package com.group3.group3act1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Account> accountList = new ArrayList<>();
    EditText eTxt_username, eTxt_password;
    Button bt_Login, bt_Reg;
    Context c = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        bt_Login.setOnClickListener(bt_click);
        bt_Reg.setOnClickListener(bt_click);

    }

    private void init() {
        accountList.add(new Account("Anna", "13579abcdeA", "Anna Lisa"));
        accountList.add(new Account("Lorna", "Th3Q4ickBr0wnF0x", "Lorna Dee"));
        accountList.add(new Account("_Fe_", "p@zzW0rd", "Fe Rari"));
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

             }
             else {

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


            else if (v.getId() == R.id.registerBtn) {
                builder.setTitle("Alert")
                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(c, MainActivity2.class);
                                startActivity(i);
                            }
                        })
                        .setMessage("Going to registration page");
                AlertDialog dialog = builder.create();
                dialog.show();
            }


        }


    };
}



