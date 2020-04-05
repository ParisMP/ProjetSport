package com.example.projectas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase; //library to use SQLite
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    //declaring variable to which we assign database name
    public static final String DATABASE_NAME = "Student1.db";

    //declaring variable to which we assign table name, Also declaring column names
    public static final String TABLE_NAME = "student_table";

    //declaring column names
    public static final String COL_1 = "ID"; //primary key
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "WINNER";
    public static final String COL_5 = "SET1J1";
    public static final String COL_6 = "SET2J1";
    public static final String COL_7 = "SET3J1";
    public static final String COL_8 = "SET1J2";
    public static final String COL_9 = "SET2J2";
    public static final String COL_10 = "SET3J2";




    //whenever the constructor is called, database is created
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase(); //going to create database in table
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,WINNER TEXT,SET1J1 TEXT,SET2J1 TEXT,SET3J1 TEXT,SET1J2 TEXT, SET2J2 TEXT, SET3J2 TEXT)");//executes whatever query we pass inside the method as an argument
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);//drop our table in case modified
        onCreate(db); //to create the table
    }

    //method to insert data, return boolean
    public boolean insertData(String name, String surname, String winner, String set1j1, String set2j1, String set3j1, String set1j2, String set2j2, String set3j2){
        SQLiteDatabase db = this.getWritableDatabase(); //going to create database in table
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);//column name in which you want to insert data, name itself
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,winner);
        contentValues.put(COL_5,set1j1);
        contentValues.put(COL_6,set2j1);
        contentValues.put(COL_7,set3j1);
        contentValues.put(COL_8,set1j2);
        contentValues.put(COL_9,set2j2);
        contentValues.put(COL_10,set3j2);
        long result = db.insert(TABLE_NAME,null,contentValues); //insert our data
        if (result == -1){
            return false;
        }
        else
            return true;
    }

    //Cursor class is an interface that provides random read-write access to the result
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME,null); // we are quering our database, storing the result in the Cursor instance res, for query * = all
        return res; //returning instance of the cursor
    }

    //to update the data
    public boolean updateData(String id,String name, String surname, String winner, String set1j1, String set2j1, String set3j1, String set1j2, String set2j2, String set3j2){
        SQLiteDatabase db = this.getWritableDatabase(); //going to create database in table
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id); //adding the id column
        contentValues.put(COL_2,name);//column name in which you want to insert data, name itself
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,winner);
        contentValues.put(COL_5,set1j1);
        contentValues.put(COL_6,set2j1);
        contentValues.put(COL_7,set3j1);
        contentValues.put(COL_8,set1j2);
        contentValues.put(COL_9,set2j2);
        contentValues.put(COL_10,set3j2);
        db.update(TABLE_NAME, contentValues,"ID = ?",new String[] { id });//update updates whatever argument is passed, fourth argument is string array
        return true; //to see if data is really updated or not
    }

    //to delete data
    public Integer deleteData (String id){
        SQLiteDatabase db = this.getWritableDatabase();//going to create database in table
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
        //name of table, whereClause, whereArgs (value we want to pass as a String array), in return it returns the number of rows that are deleted, else returns 0
    }
}
