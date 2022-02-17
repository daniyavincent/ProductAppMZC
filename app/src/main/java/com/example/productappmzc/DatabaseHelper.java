package com.example.productappmzc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    static String DbName="Product.db";
    static String TableName="Products";
    static String col1="Id";
    static String col2="ProductCode";
    static String col3="ProductName";
    static String col4="Price";
    //constructor
    public DatabaseHelper(@Nullable Context context) {
        super(context, DbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table "+TableName+ "("+col1+" integer primary key autoincrement,"+
                col2+" text,"+
                col3+" text,"+
                col4+" text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean insertData(String pcode,String pname,String price)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(col2,pcode);
        content.put(col3,pname);
        content.put(col4,price);
        long status= db.insert(TableName,null,content);
        if (status==-1){
            return false;
        }else {
            return true;
        }
    }
    //code for retrive value from dataBase
    public Cursor searchData(String pcode)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String query="select * from "+TableName+" where "+col2+"='"+pcode+"'";
        Cursor c=db.rawQuery(query,null);
        return c;
    }
}
