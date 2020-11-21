package com.example.finalyearproject;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
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

    private static final String TABLE_CHALLENGES = "challenges";

    // Users Table - column names
    private static final String KEY_USERNAME = "username";//COLUMN user name
    private static final String KEY_PASSWORD = "password";//COLUMN password
    private static final String KEY_NAME = "name";//COLUMN name
    private static final String KEY_EMAIL = "email";//COLUMN email

    //Challenge Table - column names
    private static final String KEY_CHALLENGE = "challenge";//COLUMN challenge
    private static final String KEY_PROGRESS = "progress";//COLUMN progress
    private static final String KEY_DURATION = "duration";//COLUMN duration

    // Creating Users Table
    private static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS //SQL for creating users table
            + " ( "
            + KEY_USERNAME + " TEXT PRIMARY KEY, "
            + KEY_PASSWORD + " TEXT,"
            + KEY_NAME + " TEXT,"
            + KEY_EMAIL + " TEXT"
            + " ) ";

    // Creating Challenge Table
    private static final String SQL_TABLE_CHALLENGES = " CREATE TABLE " + TABLE_CHALLENGES //SQL for creating users table
            + " ( "
            + KEY_CHALLENGE + " TEXT , "
            + KEY_PROGRESS + " INT,"
            + KEY_DURATION + " INT"
            + " ) ";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create Table when oncreate gets called
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);
        sqLiteDatabase.execSQL(SQL_TABLE_CHALLENGES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_CHALLENGES);
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

    // Adding new Challenges Details
    public void addChallenge(Challenge challenge){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_CHALLENGE, challenge.getChallenge());
        contentValues.put(KEY_PROGRESS, challenge.getProgress());
        contentValues.put(KEY_DURATION, challenge.getDuration());

        long newRowId = db.insert(TABLE_CHALLENGES, null, contentValues);
        db.close();
    }

    public Cursor getChallenges() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =db.query(TABLE_CHALLENGES,
                new String[]{KEY_CHALLENGE,
                        KEY_PROGRESS,
                        KEY_DURATION},
                null,null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }


}
