package com.androidsingh.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBaseHelperClass extends SQLiteOpenHelper {

    private static final String dbName="task.db";
    private static final String tableName="task_table";
    private static final String column1="ID";
    private static final String column2="TASK";
    private static final String column3="TIME";

    public MyDataBaseHelperClass(Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+tableName+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,TASK TEXT,TIME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+tableName);
        onCreate(db);
    }

    public boolean insertData(String task,String time) {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(column2,task);
        contentValues.put(column3,time);
        long result=sqLiteDatabase.insert(tableName,null,contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getAllData() {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor res=sqLiteDatabase.rawQuery("select*from "+tableName,null);
        return res;
    }

    public boolean updateData(String id,String task,String time) {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(column1,id);
        contentValues.put(column2,task);
        contentValues.put(column3,time);
        sqLiteDatabase.update(tableName,contentValues,"ID=?",new String[]{id});
        return true;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        return sqLiteDatabase.delete(tableName,"ID=?",new String[]{id});
    }
}
