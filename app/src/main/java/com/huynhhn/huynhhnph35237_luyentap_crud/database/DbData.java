package com.huynhhn.huynhhnph35237_luyentap_crud.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbData extends SQLiteOpenHelper {
    static String nameDB = "Data";
    static int verDB = 1;

    public DbData(Context context) {
        super(context, nameDB, null, verDB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String khoa = "CREATE TABLE khoa (     id       INTEGER PRIMARY KEY AUTOINCREMENT,     ten_khoa TEXT    UNIQUE );";
        String lop = "CREATE TABLE lop (     id      INTEGER PRIMARY KEY AUTOINCREMENT,     ten_lop TEXT    UNIQUE,     si_so   INTEGER,     id_khoa INTEGER REFERENCES khoa (id) ON DELETE NO ACTION                                          ON UPDATE NO ACTION );";

        db.execSQL(khoa);
        db.execSQL(lop);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
