package com.huynhhn.huynhhnph35237_luyentap_crud.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.huynhhn.huynhhnph35237_luyentap_crud.DTO.DTO_lop;
import com.huynhhn.huynhhnph35237_luyentap_crud.database.DbData;

import java.util.ArrayList;
import java.util.List;

public class DAO_lop {
    DbData dbData;
    SQLiteDatabase db;

    public DAO_lop (Context context){
        dbData = new DbData(context);
        db = dbData.getWritableDatabase();
    }

    public long AddRow(DTO_lop lop){
        ContentValues values = new ContentValues();
        values.put("ten_lop",lop.getTenLop());
        values.put("si_so",lop.getSiSo());
        values.put("id_khoa",lop.getIdKhoa());
        return db.insert("lop",null,values);
    }
    public int Delete(DTO_lop lop){
        String [] index = new String[]{
                String.valueOf(lop.getId())
        };
        return db.delete("lop","id=?",index);
    }

    public int UơdateRow(DTO_lop lop){
        ContentValues values = new ContentValues();
        values.put("ten_lop",lop.getTenLop());
        values.put("si_so",lop.getSiSo());
        values.put("id_khoa",lop.getIdKhoa());

        String [] index = new String[]{
                String.valueOf(lop.getId())
        };
        return db.update("lop",values,"id=?",index);
    }

    public List<DTO_lop> getAll(){
        List<DTO_lop> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM lop INNER JOIN khoa on lop.id_khoa=khoa.id",null);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
            do{
                int id = c.getInt(0);
                String tenlop = c.getString(1);
                int siSo = c.getInt(2);
                int idKhoa = c.getInt(3);
                String tenKhoa = c.getString(5);
                DTO_lop lop = new DTO_lop(id,tenlop,siSo,idKhoa,tenKhoa);
                list.add(lop);
            }while (c.moveToNext());
        }
        return list;
    }
}
