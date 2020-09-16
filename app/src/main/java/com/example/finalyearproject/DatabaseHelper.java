package com.example.finalyearproject;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import com.example.finalyearproject.User;


public class DatabaseHelper extends SQLiteOpenHelper{
    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ModerEatSystem";

    private static final String TABLE_USERS = "users";

    // Users Table - column names
    private static final String KEY_USERNAME = "username";//COLUMN user name
    private static final String KEY_PASSWORD = "password";//COLUMN password
    private static final String KEY_NAME = "name";//COLUMN name
    private static final String KEY_EMAIL = "email";//COLUMN email

    // Creating Users Table
    private static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS //SQL for creating users table
            + " ( "
            + KEY_USERNAME + " TEXT PRIMARY KEY, "
            + KEY_PASSWORD + " TEXT,"
            + KEY_NAME + " TEXT,"
            + KEY_EMAIL + " TEXT"
            + " ) ";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create Table when oncreate gets called
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
    }

    //Signing in
    public User loginUser(User user){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, //selecting the users table
                new String[] {KEY_USERNAME, KEY_PASSWORD},
                KEY_USERNAME + "=?",
                new String[]{user.getUsername()},//Where clause
                null,null,null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() >0 ){
            //if cursor has value then in user database there is user associated with this given username
            User user1 = new User(cursor.getString(0),cursor.getString(1));

            //Match both passwords check they are same or not
            if (user.getPassword().equalsIgnoreCase(user1.getPassword())) {
                return user1;
            }
        }
        //if user password does not matches or there is no record with that username then return
        return null;
    }

    // Adding new User Details
    public void AddUser(User user) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USERNAME, user.getUsername());
        contentValues.put(KEY_PASSWORD, user.getPassword());
        contentValues.put(KEY_NAME, user.getName());
        contentValues.put(KEY_EMAIL, user.getEmail());

        long newRowId = db.insert(TABLE_USERS, null, contentValues);
        db.close();
    }


}
