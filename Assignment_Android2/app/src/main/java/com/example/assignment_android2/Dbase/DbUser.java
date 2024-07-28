package com.example.assignment_android2.Dbase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbUser extends SQLiteOpenHelper {
    public DbUser( Context context) {
        super(context, "User.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CrTbUser = "CREATE TABLE User (Id INTEGER PRIMARY KEY AUTOINCREMENT, UserName TEXT UNIQUE NOT NULL, PassWord TEXT NOT NULL)";
        db.execSQL(CrTbUser);
        String CrTbQLHDTH = "CREATE TABLE QLHDTH (Id INTEGER PRIMARY KEY AUTOINCREMENT, Tilte TEXT NOT NULL, Content TEXT NOT NULL, Date TEXT)";
        db.execSQL(CrTbQLHDTH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS User");
            db.execSQL("DROP TABLE IF EXISTS QLHDTH");
            onCreate(db);
        }
    }
}
