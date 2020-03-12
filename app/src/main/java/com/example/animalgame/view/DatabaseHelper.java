package com.example.animalgame.view;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {
    public  static final String DATABASE_NAME = "Record.db";
    public  static final String TABLE_NAME = "record_table";
    public  static final String COL_1 = "NAME";
    public  static final String COL_2 = "SCORE";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL("create table " + TABLE_NAME +" (NAME TEXT PRIMARY KEY, SCORE INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
onCreate(db);
    }

    public boolean insertData(String name, Integer score)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, name);
        contentValues.put(COL_2, score);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return  false;
        else
            return  true;



    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+ "    ORDER BY SCORE DESC" ,null);
        return res;
    }

}
