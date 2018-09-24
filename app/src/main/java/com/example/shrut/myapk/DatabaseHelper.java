package com.example.shrut.myapk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root1 on 21/9/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase Db;

    public static final String DATABASE_NAME = "info.db";
    public static final String TABLE_NAME = "userinfo";
    // public static final String col_1="id";
    public static final String col_1 = "Contact";
    public static final String col_2 = "Name";
    public static final String col_3 = "Email";
    public static final String col_4 = "Password";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase Db) {
        Db.execSQL("create table " + TABLE_NAME + " (Contact INTEGER PRIMARY KEY ,Name TEXT NOT NULL,Email TEXT,Password TEXT NOT NULL )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase Db, int i, int i1) {
        Db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(Db);

    }

    public boolean insertdata(String contact, String name, String email, String Pass) {
        SQLiteDatabase Db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1, contact);
        contentValues.put(col_2, name);
        contentValues.put(col_3, email);
        contentValues.put(col_4, Pass);
        long result = Db.insert(TABLE_NAME, null, contentValues);
        //Db.close();
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Cursor getData() {
        Db = this.getWritableDatabase();
        Cursor res = Db.rawQuery("select * from " + TABLE_NAME, null);
        return res;


    }

    public String searchLog(String contact) {
        Db = this.getReadableDatabase();
        Cursor cursor = Db.rawQuery("select Contact,Password from " + TABLE_NAME, null);
        String a, b;
        b = "false";


        if (cursor.moveToFirst()) {

            do {
                a = cursor.getString(0);


                if (a.equals(contact)) {
                    b = cursor.getString(1);
                    break;
                }

            } while (cursor.moveToNext());
        }

        return b;

    }

    public String searchSign(String contact) {
        Db = this.getReadableDatabase();
        Cursor cursor = Db.rawQuery("select Contact,Password from " + TABLE_NAME, null);
        String a;
        String b = "false";
        if (cursor.moveToFirst()) {

            do {
                a = cursor.getString(0);

                if (a.equals(contact)) {
                    b = "true";
                    break;
                }

            } while (cursor.moveToNext());
        }

        return b;
    }

}
