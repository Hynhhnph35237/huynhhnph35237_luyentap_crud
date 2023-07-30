package com.huynhhn.huynhhnph35237_luyentap_crud.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.huynhhn.huynhhnph35237_luyentap_crud.DTO.DTO_khoa;
import com.huynhhn.huynhhnph35237_luyentap_crud.database.DbData;

import java.util.ArrayList;
import java.util.List;

public class DAO_khoa {
    DbData dbData ;
    SQLiteDatabase db;

    public DAO_khoa(Context context){
        dbData=new DbData(context);
        db=dbData.getWritableDatabase();
    }

    public long AddRow(DTO_khoa khoa){
        ContentValues values = new ContentValues();
        values.put("ten_khoa",khoa.getTenKhoa());
        return db.insert("khoa",null,values);
    }

    public int DeleteRow(DTO_khoa khoa){
        String [] index= new String[]{
                String.valueOf(khoa.getId())
        };
        return db.delete("khoa","id=?",index);
    }

    public int UpdateRow(DTO_khoa khoa){
        ContentValues values = new ContentValues();
        values.put("ten_khoa",khoa.getTenKhoa());

        String [] index= new String[]{
                String.valueOf(khoa.getId())
        };

        return db.update("khoa",values,"id=?",index);
    }

    public List<DTO_khoa> getAll(){
        List<DTO_khoa> list = new ArrayList<>();
        Cursor c = db.rawQuery("select * from khoa",null);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
            do{
                int id = c.getInt(0);
                String tenKhoa = c.getString(1);
                DTO_khoa khoa = new DTO_khoa(id,tenKhoa);
                list.add(khoa);
            }while (c.moveToNext());
        }
        return list;
    }
}
