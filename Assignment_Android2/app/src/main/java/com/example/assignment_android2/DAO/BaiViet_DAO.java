package com.example.assignment_android2.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assignment_android2.Dbase.DbUser;
import com.example.assignment_android2.Model.BaiViet;

import java.util.ArrayList;
import java.util.List;

public class BaiViet_DAO {
    DbUser dbUser;
    SQLiteDatabase sqLiteDatabase;

    public BaiViet_DAO(Context context) {
        dbUser = new DbUser(context);
        sqLiteDatabase = dbUser.getWritableDatabase();
    }

    public boolean Insert(BaiViet model){

        ContentValues values = new ContentValues();
        values.put("Tilte", model.getTilte());
        values.put("Content", model.getContent());
        values.put("Date", model.getDate());

        long result = sqLiteDatabase.insert("QLHDTH", null, values);
        return result > 0;
    }

    public ArrayList<BaiViet> getAll(){
        ArrayList<BaiViet> list = new ArrayList<BaiViet>();
        sqLiteDatabase = dbUser.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM QLHDTH", null);

        while (cursor.moveToNext()){
            Integer id = cursor.getInt(0);
            String Tilte = cursor.getString(1);
            String Content = cursor.getString(2);
            String date = cursor.getString(3);

            list.add(new BaiViet(id, Tilte, Content, date));
        }

        return list;
    }

    public boolean Update (BaiViet baiViet){

        ContentValues values = new ContentValues();
        values.put("Tilte", baiViet.getTilte());
        values.put("Content", baiViet.getContent());
        values.put("Date", baiViet.getDate());

        String[] dieu_kien = {String.valueOf(baiViet.getId())};
        long row = sqLiteDatabase.update("QLHDTH", values, "Id = ?", dieu_kien);

        return row > 0;
    }

    public boolean delete(BaiViet baiViet){
        String[] dieu_kien = {String.valueOf(baiViet.getId())};
        long row = sqLiteDatabase.delete("QLHDTH", "Id = ?", dieu_kien);
        return row > 0;
    }
}
