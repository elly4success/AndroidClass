package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import java.util.HashMap;

public class SQLiteHandler extends SQLiteOpenHelper {

    //Database name
    static final String dbname = "zalego_db";
    //Database version
    static final int dbversion = 1;
    //Creating context for Toast
    Context context;
    //Database table
    static final String TABLE_USER = "table_user";

    //Database rows
    static final String key_id = "id";
    static final String key_name = "name";
    static final String key_email = "email";
    static final String key_phone = "phone";
    static final String key_password = "password";
    static final String key_gender = "gender";

    public SQLiteHandler(Context context) {
        super(context, dbname, null, dbversion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = " CREATE TABLE " + TABLE_USER + "("
                + key_id + " INTEGER PRIMARY KEY, "
                + key_name + " TEXT, "
                + key_email + " TEXT UNIQUE, "
                + key_phone + " INTEGER, "
                + key_password + " TEXT, "
                + key_gender + " TEXT " + ")";

        db.execSQL(CREATE_USER_TABLE);

        Toast.makeText(context,
                "Table created",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop older table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        //Recreate the tables
        onCreate(db);
    }

    //Add users to table
    public void AddUser(String name, String email, String phone, String password, String gender ){
        //Initializing and opening our db
        SQLiteDatabase db =  this.getWritableDatabase();
        //Initializing our content values AKA rows
        ContentValues values = new ContentValues();
        values.put(key_name,name);
        values.put(key_email,email);
        values.put(key_phone,phone);
        values.put(key_password,password);
        values.put(key_gender,gender);

        //Inserting row into db
        long id = db.insert(TABLE_USER,null,values);
        //Close db connection
        db.close();

        Toast.makeText(context,
                "User inserted",
                Toast.LENGTH_SHORT).show();
    }

    //Update user data
    public void UpdateUser(String userid, String name, String email, String phone, String password, String gender){
        //Open the database
        SQLiteDatabase db = this.getWritableDatabase();
        //Initialize content values
        ContentValues values = new ContentValues();
        values.put(key_name,name);
        values.put(key_email,email);
        values.put(key_phone,phone);
        values.put(key_password,password);
        values.put(key_gender,gender);
        //Update row
        long id = db.update(TABLE_USER,values,"id = ?",
                new String[] {userid});
        //Close db
        db.close();

        Toast.makeText(context,
                "User updated",
                Toast.LENGTH_SHORT).show();
    }
    
    //Login method
    public boolean Login(String email, String password){
        String selectquery = "SELECT * FROM " + TABLE_USER + " WHERE email = ? AND password = ? ";
        //Open db
        SQLiteDatabase db =  this.getWritableDatabase();
        ////Store query results in cursor
        Cursor cursor  = db.rawQuery(selectquery,new String[]{email,password});
        //Move the cursor to the first row
        cursor.moveToFirst();
        //Boolean for returning whether user is there or not
        boolean status;
        if(cursor.getCount() > 0){
           status = true;
        }else{
           status = false;
        }
        cursor.close();
        db.close();

        return status;
    }

    //Get all users
    public HashMap<String,String> GetUsers(){
        HashMap<String,String> user = new HashMap<>();
        String selectquery = "SELECT * FROM " + TABLE_USER;

        SQLiteDatabase db =  this.getWritableDatabase();
        //Store query results in cursor
        Cursor cursor = db.rawQuery(selectquery,null);
        //Move the cursor to the first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            user.put(key_id,cursor.getString(0));
            user.put(key_name,cursor.getString(1));
            user.put(key_email,cursor.getString(2));
            user.put(key_phone,cursor.getString(3));
            user.put(key_password,cursor.getString(4));
            user.put(key_gender,cursor.getString(5));
        }
        cursor.close();
        db.close();

        Toast.makeText(context,
                "User fetched",
                Toast.LENGTH_SHORT).show();

        return user;
    }

    //Delete all users from table
    public void DeleteAllUsers(){
        //Initializing and opening our db
        SQLiteDatabase db = this.getWritableDatabase();
        //Delete all users from db
        db.delete(TABLE_USER, null,null);
        //Close db connection
        db.close();
        Toast.makeText(context,
                "All users deleted",
                Toast.LENGTH_SHORT).show();
    }
}
