package com.group3.group3act1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SQLDBHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "myDatabase.db";
    private static int VERSION = 8;
    Context context;
    public SQLDBHelper( Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        final String CREATE_USER_TABLE = "CREATE TABLE '"+DB_Contract.tb_Account.ACCOUNT_TABLE+"' (" +
                "'"+DB_Contract.tb_Account.ACCOUNT_ID+"' INTEGER PRIMARY KEY," + //0
                "'"+DB_Contract.tb_Account.ACCOUNT_NAME+"' TEXT NOT NULL," + //1
                "'"+DB_Contract.tb_Account.ACCOUNT_USERNAME+"' TEXT NOT NULL," + //2
                "'"+DB_Contract.tb_Account.ACCOUNT_PASSWORD+"' TEXT NOT NULL," + //3
                "'"+DB_Contract.tb_Account.ACCOUNT_BIRTHDATE+"' TEXT NOT NULL," +
                "'"+DB_Contract.tb_Account.ACCOUNT_GENDER+"' TEXT NOT NULL," +
                "'"+DB_Contract.tb_Account.ACCOUNT_HOBBIES+"' TEXT NOT NULL," +
                "'"+DB_Contract.tb_Account.ACCOUNT_ADDRESS+"' TEXT NOT NULL," +
                "'"+DB_Contract.tb_Account.ACCOUNT_CONTACT+"' TEXT NOT NULL," +
                "'"+DB_Contract.tb_Account.ACCOUNT_SECURITY_1+"' TEXT NOT NULL," +
                "'"+DB_Contract.tb_Account.ACCOUNT_SECURITY_2+"' TEXT NOT NULL," +
                "'"+DB_Contract.tb_Account.ACCOUNT_SECURITY_3+"' TEXT NOT NULL," +
                "'"+DB_Contract.tb_Account.ACCOUNT_IMAGE_PATH+"' TEXT," +
                "UNIQUE ('"+DB_Contract.tb_Account.ACCOUNT_ID+"') ON CONFLICT ABORT);";



        final String CREATE_ENTRY_TABLE = "CREATE TABLE '"+DB_Contract.tb_Entry.ENTRY_TABLE+"' (" +
                "'"+DB_Contract.tb_Entry.ENTRY_ID+"' INTEGER PRIMARY KEY," + //0
                "'"+DB_Contract.tb_Entry.ACCOUNT_ID+"' INTEGER NOT NULL," + //1
                "'"+DB_Contract.tb_Entry.ENTRY_NAME+"' TEXT NOT NULL," + //2
                "'"+DB_Contract.tb_Entry.ENTRY_REMARK+"' TEXT NOT NULL," + //3
                "'"+DB_Contract.tb_Entry.ENTRY_BIRTHDAY+"' TEXT NOT NULL," + //4
                "'"+DB_Contract.tb_Entry.ENTRY_GENDER+"' TEXT NOT NULL," + //5
                "'"+DB_Contract.tb_Entry.ENTRY_CONTACT+"' TEXT NOT NULL," + //6
                "'"+DB_Contract.tb_Entry.ENTRY_HOBBIES+"' TEXT NOT NULL," + //7
                "'"+DB_Contract.tb_Entry.ENTRY_OTHERINFORMATION+"' TEXT ," + //8
                "'"+DB_Contract.tb_Entry.ENTRY_IMAGE+"' TEXT," + //9
                "'"+DB_Contract.tb_Entry.ENTRY_ADDRESS+"' TEXT," + //10
                "FOREIGN KEY ('"+DB_Contract.tb_Entry.ACCOUNT_ID+"') REFERENCES" +
                "'"+DB_Contract.tb_Account.ACCOUNT_TABLE+"'('"+DB_Contract.tb_Account.ACCOUNT_ID+"')," +
                "UNIQUE ('"+DB_Contract.tb_Entry.ENTRY_ID+"') ON CONFLICT ABORT);";


        try {
            db.execSQL(CREATE_USER_TABLE);
            db.execSQL(CREATE_ENTRY_TABLE);
            Toast.makeText(context, "Table Created", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(context, "Walang na create", Toast.LENGTH_SHORT).show();
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




    public boolean insertIntoEntryTable(int account_id,
                                        String entry_name,
                                        String entry_remark,
                                        String entry_bday,
                                        String entry_gender,
                                        String entry_contact,
                                        String entry_hobbies,
                                        String entry_other,
                                        String entry_image,
                                        String entry_address){

        ContentValues values = new ContentValues();

        values.put(DB_Contract.tb_Entry.ACCOUNT_ID, account_id);
        values.put(DB_Contract.tb_Entry.ENTRY_NAME, entry_name);
        values.put(DB_Contract.tb_Entry.ENTRY_REMARK, entry_remark);
        values.put(DB_Contract.tb_Entry.ENTRY_BIRTHDAY, entry_bday);
        values.put(DB_Contract.tb_Entry.ENTRY_GENDER, entry_gender);
        values.put(DB_Contract.tb_Entry.ENTRY_CONTACT, entry_contact);
        values.put(DB_Contract.tb_Entry.ENTRY_HOBBIES, entry_hobbies);
        values.put(DB_Contract.tb_Entry.ENTRY_OTHERINFORMATION, entry_other);
        values.put(DB_Contract.tb_Entry.ENTRY_IMAGE, entry_image);
        values.put(DB_Contract.tb_Entry.ENTRY_ADDRESS, entry_address);


        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(DB_Contract.tb_Entry.ENTRY_TABLE, null, values);

        if(result == -1){
            return false;

        }
        else{
            return true;

        }


    }

    public boolean updateIntoEntryTable(String entry_id,
                                        String entry_name,
                                        String entry_remark,
                                        String entry_bday,
                                        String entry_gender,
                                        String entry_contact,
                                        String entry_hobbies,
                                        String entry_other,
                                        String entry_image,
                                        String entry_address){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(DB_Contract.tb_Entry.ENTRY_NAME, entry_name);
        values.put(DB_Contract.tb_Entry.ENTRY_REMARK, entry_remark);
        values.put(DB_Contract.tb_Entry.ENTRY_BIRTHDAY, entry_bday);
        values.put(DB_Contract.tb_Entry.ENTRY_GENDER, entry_gender);
        values.put(DB_Contract.tb_Entry.ENTRY_CONTACT, entry_contact);
        values.put(DB_Contract.tb_Entry.ENTRY_HOBBIES, entry_hobbies);
        values.put(DB_Contract.tb_Entry.ENTRY_OTHERINFORMATION, entry_other);
        values.put(DB_Contract.tb_Entry.ENTRY_IMAGE, entry_image);
        values.put(DB_Contract.tb_Entry.ENTRY_ADDRESS, entry_address);


        String selection = DB_Contract.tb_Entry.ENTRY_ID + " = ?";
        String[] selectionArgs = {entry_id};
        int affected = db.update(DB_Contract.tb_Entry.ENTRY_TABLE, values, selection, selectionArgs);
        if(affected>0){
            return true;

        }
        else{
            return false;

        }




    }




    public Cursor selectAllEntry(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor result = db.query(DB_Contract.tb_Entry.ENTRY_TABLE, null, null, null, null, null,null);
        return result;

    }

    public Cursor selectAllEntryOrdered(){
        SQLiteDatabase db = getReadableDatabase();
        String orderBy = DB_Contract.tb_Entry.ACCOUNT_ID + " ASC ";
        Cursor result = db.query(DB_Contract.tb_Entry.ENTRY_TABLE, null, null, null, null, null,orderBy);
        return result;

    }

    public Cursor selectAllUsers(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor result = db.query(DB_Contract.tb_Account.ACCOUNT_TABLE, null, null, null, null, null,null);
        return result;

    }

    public boolean deleteEntry(String ID){
        SQLiteDatabase db = this.getWritableDatabase();

        String selection = DB_Contract.tb_Entry.ENTRY_ID + " = ? ";
        String [] selectionArgs = {ID};
        int affected = db.delete(DB_Contract.tb_Entry.ENTRY_TABLE,selection, selectionArgs );
        if(affected > 0){
            return true;

        }
        else{

            return false;
        }
    }


    public Cursor selectEntryByID(String accountID){
        SQLiteDatabase db = getReadableDatabase();

        String selection =  DB_Contract.tb_Entry.ACCOUNT_ID+" = ? ";

        String[] selectionArgs = {accountID};
        Cursor result = db.query(DB_Contract.tb_Entry.ENTRY_TABLE,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null);
        return result;

    }

    public Cursor selectEntryByName(String accountName){
        SQLiteDatabase db = getReadableDatabase();

        String selection =  DB_Contract.tb_Entry.ENTRY_NAME+" LIKE ? ";

        String[] selectionArgs = {"%"+accountName+"%"};
        Cursor result = db.query(DB_Contract.tb_Entry.ENTRY_TABLE,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null);
        return result;

    }








    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //drop table

        final String DROP_USER_TABLE  = "DROP TABLE IF EXISTS "+ DB_Contract.tb_Account.ACCOUNT_TABLE;
        final String DROP_ENTRY_TABLE  = "DROP TABLE IF EXISTS "+ DB_Contract.tb_Entry.ENTRY_TABLE;
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_ENTRY_TABLE);
        Toast.makeText(context, "nagdrop", Toast.LENGTH_SHORT).show();
    }
}
