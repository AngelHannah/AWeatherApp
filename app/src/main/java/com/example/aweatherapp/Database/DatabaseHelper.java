package com.example.aweatherapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.aweatherapp.Domains.User;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Fields - database name
    public static final String databaseName = "WeatherAppDB.db";
    public static final int databaseVersion = 1;
    public static final String tableName = "allusers";
    public static final String emailCol = "email";
    public static final String passwordCol = "password";



    //Constructor
    public DatabaseHelper(@Nullable Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase myDb) {
        //This executes a sql statement that creates a table called allusers with a text column for email and for password plus an id int that autoincrements
        myDb.execSQL("create Table allusers(id INTEGER primary key autoincrement, email TEXT , password TEXT)");

        //Seed data - insert data to the table so theres something to display
        myDb.execSQL("insert into allusers (email, password) values('doglover13@gmail.com', 'dogloverforever14')");
        myDb.execSQL("insert into allusers (email, password) values('tictactoe32@yahoo.com', 'tictac123')");
        myDb.execSQL("insert into allusers (email, password) values('blurblurb57@gmail.com', 'blurbblurb')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDb, int oldVersion, int newVersion) {
        myDb.execSQL("drop Table if exists allusers");
        onCreate(myDb);
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

        //Close the db connection 
        myDb.close();

        if(result == -1){
            return false;
        }
        else return true;
    }

    //CRUD - Reads the list of Users
    //Some of this code comes from the SQLite tutorial we did in week 3
    public ArrayList<User> displayUsers(){
        //Open db connection
        SQLiteDatabase myDb = this.getReadableDatabase();

        //make a cursor so we can read the data in the db
        Cursor cursorAllUsers = myDb.rawQuery("Select * From allusers", null);

        //make a new arraylist
        ArrayList<User> allUsersList = new ArrayList<>();

        //Start the reading
        if(cursorAllUsers.moveToFirst()){
            //Do While to work through the db and make the list
            do{

                allUsersList.add(new User(
                        cursorAllUsers.getInt(0),
                        cursorAllUsers.getString(1),
                        cursorAllUsers.getString(2)));
            }
            while(cursorAllUsers.moveToNext());
        }

        //Close out the cursor
        cursorAllUsers.close();

        //Return the list to be displayed
        return allUsersList;

    }

    //CRUD - Method to update a user record
    //This method needs to take in all the data being updated plus the id of the record the user wants to update
    public Boolean updateUser(String originalEmail, String email, String password){
        //Get the writable
        SQLiteDatabase myDb = this.getWritableDatabase();
        //Create an object for the content values
        ContentValues values = new ContentValues();

        //Take data thats passed in and store it in the contentvalues object
        values.put(emailCol, email);
        values.put(passwordCol, password);

        //Call the update method and pass the data - update returns the number of rows affected so thats whats going into updated
        int updated = myDb.update(tableName, values, emailCol + " = ?", new String[]{originalEmail});

        //Logcat message to let me know the update went through to the db
        Log.d("DatabaseHelper", "updateUser: " + updated + " rows updated.");

        //Close the db out
        myDb.close();

        if(updated > 0){
            return true;
        }
        else return false;
    }

    //CRUD - Deletes the selected user record
    //This method just needs the email of the user to be deleted (probably terrible planning but its happening)
    public boolean deleteUser(String userEmail){
        //Get a writable connection
        SQLiteDatabase db = this.getWritableDatabase();

        //try the delete - delete returns the number of rows affected so we're storing that
        int rowsDeleted = db.delete(tableName, emailCol + " = ?", new String[]{userEmail});

        //This is a nifty thing that will print to logcat and let me know if the action is actually hitting the db or not - thanks ChatGPT
        Log.d("DatabaseHelper", "deleteUser: " + rowsDeleted + " rows deleted.");

        if(rowsDeleted > 0){
            return true;
        }
        else return false;

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

    //This checks the email and password combo entered
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

    //This method checks to see if the credentials used are the admins
    public boolean checkAdmin(String email, String password){
        if(email.equals("Admin") && password.equals("Admin")){
            return true;
        }
        else return false;
    }
}
