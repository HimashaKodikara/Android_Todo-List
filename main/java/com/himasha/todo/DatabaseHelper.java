package com.himasha.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAMEE= "TodoD.db";
    public static final String Table_Name="TodoD_table";
    public static final String Col_1 ="ID";
    public static final String Col_2 = "Date";
    public static final String Col_3="Description";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAMEE,null,1);
        SQLiteDatabase db =this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + Table_Name +"(ID INTEGER PRIMARY KEY AUTOINCREMENT," + "Date TEXT,Description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+Table_Name);
        onCreate(db);
    }
    public boolean insertData(String Date,String Description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2,Date);
        contentValues.put(Col_3,Description);
        long results = db.insert(Table_Name,null,contentValues);
        if(results == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getAllDat(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor results = db.rawQuery(" select * from "+ Table_Name,null);
        return results;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Table_Name,"ID = ?",new String[] {id});
    }

    public boolean updateData(String id,String date,String Description){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_1,id);
        contentValues.put(Col_2,date);
        contentValues.put(Col_3,Description);
        db.update(Table_Name,contentValues,"id = ?",new String[]{id});
        return true;
    }
}
