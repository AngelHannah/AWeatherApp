package com.example.aweatherapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Fields - database name
    public static final String databaseName = "WeatherAppDB.db";



    //Constructor
    public DatabaseHelper(@Nullable Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDb) {
        //This executes a sql statement that creates a table called allusers with a text column for email and for password plus an id int
        myDb.execSQL("create Table allusers(id INTEGER primary key autoincrement, email TEXT , password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDb, int oldVersion, int newVersion) {
        myDb.execSQL("drop Table if exists allusers");
    }

    //CRUD - Create new user
    public Boolean addUser(String email, String password){
        //Open the database
        SQLiteDatabase myDb = this.getWritableDatabase();

        //Creating key value pairs so email and password can be processed
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);

        //Attempt to insert the values
        long result = myDb.insert("allusers", null, contentValues);

        //Close the db
        myDb.close();

        if(result == -1){
            return false;
        }
        else return true;
    }

    //This method is used to check for matching emails in the db
    public Boolean checkEmail(String email){
        //Open db connection
        SQLiteDatabase myDb = this.getWritableDatabase();

        //This is checking for the email thats being passed in to make sure it matches one in the db
        Cursor cursor = myDb.rawQuery("Select * from allusers where email = ?", new String[]{email});

        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    //This checks both email and password
    public Boolean checkEmailAndPassword(String email, String password){
        //Open db connection
        SQLiteDatabase myDb = this.getWritableDatabase();

        //This is checking for the email and password
        Cursor cursor = myDb.rawQuery("Select * from allusers where email = ? and password = ?", new String[]{email, password});

        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }
}
