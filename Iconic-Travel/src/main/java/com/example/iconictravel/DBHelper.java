package com.example.iconictravel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.CancellationSignal;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME="reg.db";

    public DBHelper(@Nullable Context context) {
        super(context, "reg.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table passengers(username TEXT , password TEXT primary key ) ");
        //db.execSQL("insert  into passengers values ('admin','admin')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists passengers ");

    }

    public Boolean insertdata (String username , String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("username",username);
        values.put("password", password);

        long result = db.insert("passengers", null , values);
        if(result==-1)
            return false;
        else return true;
    }
    public boolean checkpassword(String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from passengers where password=?" , new String[]{password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }
    public boolean checkpasswordandname(String username, String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from passengers where  username=? and password=?"  , new String[]{username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }
    public boolean checkadmin (String user , String pass)
    {

        if(user.equals("admin") && pass.equals("1"))

            return true;
        else
            return false;

    }

}

