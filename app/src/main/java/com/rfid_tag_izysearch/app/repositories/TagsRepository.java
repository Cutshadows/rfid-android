package com.rfid_tag_izysearch.app.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rfid_tag_izysearch.app.helpers.AdminSQLOpenHelper;

import java.net.ConnectException;
import java.util.ArrayList;

public class TagsRepository {

    private Context context;

    public TagsRepository(Context context) {
        this.context = context;
    }

    public void InsertTag(String id, String desc, Boolean state) {
        AdminSQLOpenHelper admin = new AdminSQLOpenHelper(context);
        SQLiteDatabase db = admin.getWritableDatabase();

        ContentValues reg = new ContentValues();

        reg.put("id_rfid", id);
        reg.put("descripcion", desc);
        reg.put("state", state);

        db.insert("Tags", null, reg);

        db.close();

    }

    public String ViewTags(){
        AdminSQLOpenHelper admin = new AdminSQLOpenHelper(context);
        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor read= db.rawQuery("SELECT * FROM Tags", null);
        String datos = "";

        if (read.moveToFirst()) {
            do {
                datos = (read.getString(0));

            } while (read.moveToNext());
        }

        return datos;
    }


    public ArrayList ViewAllTags(){
        ArrayList<String> datos=new ArrayList<>();
        AdminSQLOpenHelper admin = new AdminSQLOpenHelper(context);
        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor read= db.rawQuery("SELECT * FROM Tags", null);

        if (read.moveToFirst()) {
            do {
                datos.add(read.getString(0));
                datos.add(read.getString(1));
                datos.add(read.getString(2));

            } while (read.moveToNext());
        }
        return datos;
    }

}
