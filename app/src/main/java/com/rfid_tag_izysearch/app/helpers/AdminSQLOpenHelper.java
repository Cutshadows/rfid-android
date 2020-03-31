package com.rfid_tag_izysearch.app.helpers;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "IZYSEARCH";
    private static final int DATABASE_VERSION=1;


    public AdminSQLOpenHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public AdminSQLOpenHelper(Context context, int version) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF  NOT EXISTS Tags(id_rfid TEXT UNIQUE, descripcion TEXT, state BOOLEAN)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Tags");
    }
}
