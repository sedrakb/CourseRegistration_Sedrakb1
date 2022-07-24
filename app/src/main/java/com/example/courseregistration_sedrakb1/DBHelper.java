/*
Bichoy Sedrak - CSIT551 Mobile Computing Summer 2022
Creating Course registration application
 */
package com.example.courseregistration_sedrakb1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputLayout;

    // Creating the Database Helper Class for the SQL code
    public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Studentdata.db", null, 1);
    }

    //Creating the Table
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("Create Table Studentdetails(name Text primary key, ID Text, year Text)");
    }

    // to drop the table if already exists.
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists Studentdetails");
    }

    // to insert the entered data in the table.
    public Boolean insertstudentdata (TextInputLayout name, TextInputLayout id, Spinner year){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", String.valueOf(name));
        contentValues.put("ID", String.valueOf(id));
        contentValues.put("year", String.valueOf(year));

        long result = DB.insert("Studentdetails", null, contentValues);
        return result != -1;
    }

    // to display the data
    public Cursor getData()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("Select * from Studentdetails", null);
    }

}


// end Bichoy Sedrak