package com.group3.group3act1;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SQLDBHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "myDatabase.db";
    private static int VERSION = 1;
    Context context;
    public SQLDBHelper( Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        final String CREATE_USER_TABLE = "CREATE TABLE '"+DB_Contract.tb_Account.ACCOUNT_TABLE+"' (" +
                "'"+DB_Contract.tb_Account.ACCOUNT_ID+"' INTEGER PRIMARY KEY," +
                "'"+DB_Contract.tb_Account.ACCOUNT_NAME+"' TEXT NOT NULL," +
                "'"+DB_Contract.tb_Account.ACCOUNT_USERNAME+"' TEXT NOT NULL," +
                "'"+DB_Contract.tb_Account.ACCOUNT_PASSWORD+"' TEXT NOT NULL," +
                "'"+DB_Contract.tb_Account.ACCOUNT_BIRTHDATE+"' TEXT NOT NULL," +
                "'"+DB_Contract.tb_Account.ACCOUNT_GENDER+"' TEXT NOT NULL," +
                "'"+DB_Contract.tb_Account.ACCOUNT_HOBBIES+"' TEXT NOT NULL," +
                "'"+DB_Contract.tb_Account.ACCOUNT_ADDRESS+"' TEXT NOT NULL," +
                "'"+DB_Contract.tb_Account.ACCOUNT_CONTACT+"' TEXT NOT NULL," +
                "'"+DB_Contract.tb_Account.ACCOUNT_SECURITY_1+"' TEXT NOT NULL," +
                "'"+DB_Contract.tb_Account.ACCOUNT_SECURITY_2+"' TEXT NOT NULL," +
                "'"+DB_Contract.tb_Account.ACCOUNT_SECURITY_3+"' TEXT NOT NULL," +
                "'"+DB_Contract.tb_Account.ACCOUNT_IMAGE_PATH+"' TEXT NOT NULL," +

                "UNIQUE ('"+DB_Contract.tb_Account.ACCOUNT_ID+"') ON CONFLICT ABORT);";

        final String CREATE_ENTRY_TABLE = "CREATE TABLE '"+DB_Contract.tb_Entry.ENTRY_TABLE+"' (" +
                "'"+DB_Contract.tb_Entry.ENTRY_ID+"' INTEGER PRIMARY KEY," +
                "'"+DB_Contract.tb_Entry.ACCOUNT_ID+"' INTEGER NOT NULL," +
                "'"+DB_Contract.tb_Entry.ENTRY_NAME+"' TEXT NOT NULL," +
                "'"+DB_Contract.tb_Entry.ENTRY_REMARK+"' TEXT NOT NULL," +
                "'"+DB_Contract.tb_Entry.ENTRY_BIRTHDAY+"' TEXT NOT NULL," +
                "'"+DB_Contract.tb_Entry.ENTRY_GENDER+"' TEXT NOT NULL," +
                "'"+DB_Contract.tb_Entry.ENTRY_CONTACT+"' TEXT NOT NULL," +
                "'"+DB_Contract.tb_Entry.ENTRY_HOBBIES+"' TEXT NOT NULL," +
                "'"+DB_Contract.tb_Entry.ENTRY_OTHERINFORMATION+"' TEXT NOT NULL," +
                "FOREIGN KEY ('"+DB_Contract.tb_Entry.ACCOUNT_ID+"') REFERENCES" +
                "'"+DB_Contract.tb_Account.ACCOUNT_TABLE+"'('"+DB_Contract.tb_Account.ACCOUNT_ID+"')," +
                "UNIQUE ('"+DB_Contract.tb_Entry.ENTRY_ID+"') ON CONFLICT ABORT);";


        try {
            db.execSQL(CREATE_USER_TABLE);
            db.execSQL(CREATE_ENTRY_TABLE);
            Toast.makeText(context, "Table Created", Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean insertIntoUserTable(String name, String username, String password,
                                       String birthdate, String address, String gender,
                                       String hobby, String contact, String sec1,
                                       String sec2, String sec3, String imagePath){

        ContentValues values = new ContentValues();
        values.put(DB_Contract.tb_Account.ACCOUNT_NAME, name);
        values.put(DB_Contract.tb_Account.ACCOUNT_USERNAME, username);
        values.put(DB_Contract.tb_Account.ACCOUNT_PASSWORD, password);
        values.put(DB_Contract.tb_Account.ACCOUNT_BIRTHDATE,birthdate);
        values.put(DB_Contract.tb_Account.ACCOUNT_ADDRESS, address);
        values.put(DB_Contract.tb_Account.ACCOUNT_GENDER, gender);
        values.put(DB_Contract.tb_Account.ACCOUNT_HOBBIES, hobby);
        values.put(DB_Contract.tb_Account.ACCOUNT_CONTACT, contact);
        values.put(DB_Contract.tb_Account.ACCOUNT_SECURITY_1, sec1);
        values.put(DB_Contract.tb_Account.ACCOUNT_SECURITY_2, sec2);
        values.put(DB_Contract.tb_Account.ACCOUNT_SECURITY_3, sec3);
        values.put(DB_Contract.tb_Account.ACCOUNT_IMAGE_PATH, imagePath);
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(DB_Contract.tb_Account.ACCOUNT_TABLE, null, values);

        if(result == -1){
    return false;

        }
       else{
            return true;

        }


    }


//unedited

    public boolean insertIntoEntryTable(String name, String username, String password,
                                       String birthdate, String address, String gender,
                                       String hobby, String contact, String sec1,
                                       String sec2, String sec3, String imagePath){

        ContentValues values = new ContentValues();
        values.put(DB_Contract.tb_Account.ACCOUNT_NAME, name);
        values.put(DB_Contract.tb_Account.ACCOUNT_USERNAME, username);
        values.put(DB_Contract.tb_Account.ACCOUNT_PASSWORD, password);
        values.put(DB_Contract.tb_Account.ACCOUNT_BIRTHDATE,birthdate);
        values.put(DB_Contract.tb_Account.ACCOUNT_ADDRESS, address);
        values.put(DB_Contract.tb_Account.ACCOUNT_GENDER, gender);
        values.put(DB_Contract.tb_Account.ACCOUNT_HOBBIES, hobby);
        values.put(DB_Contract.tb_Account.ACCOUNT_CONTACT, contact);
        values.put(DB_Contract.tb_Account.ACCOUNT_SECURITY_1, sec1);
        values.put(DB_Contract.tb_Account.ACCOUNT_SECURITY_2, sec2);
        values.put(DB_Contract.tb_Account.ACCOUNT_SECURITY_3, sec3);
        values.put(DB_Contract.tb_Account.ACCOUNT_IMAGE_PATH, imagePath);
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(DB_Contract.tb_Account.ACCOUNT_TABLE, null, values);

        if(result == -1){
            return false;

        }
        else{
            return true;

        }


    }






    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //drop table

        final String DROP_USER_TABLE  = "DROP TABLE IF EXISTS "+ DB_Contract.tb_Account.ACCOUNT_TABLE;
        final String DROP_ENTRY_TABLE  = "DROP TABLE IF EXISTS "+ DB_Contract.tb_Entry.ENTRY_TABLE;
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_ENTRY_TABLE);
    }
}
