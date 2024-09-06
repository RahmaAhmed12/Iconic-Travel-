package com.example.iconictravel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperP extends SQLiteOpenHelper {

    private static String databaseName = "Passenger";
    SQLiteDatabase Passenger;


    public DatabaseHelperP(Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table passenger (id integer primary key," +
                "first_name text not null, second_name text not null,phone_num text not null,NumOfTicket integer not null,pay_method text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists passenger");
        onCreate(db);
    }


    public void CreateNewpassenger(int FN, String f_name, String s_name, String ph, int ticket, String pay) {
        ContentValues row = new ContentValues();
        row.put("id", FN);
        row.put("first_name", f_name);
        row.put("second_name", s_name);
        row.put("phone_num", ph);
        row.put("NumOfTicket", ticket);
        row.put("pay_method", pay);
        Passenger = getWritableDatabase();
        Passenger.insert("passenger", null, row);
        Passenger.close();
    }


    public Cursor fetch() {
        Passenger = getReadableDatabase();
        String[] rowD = {"id", "first_name", "second_name", "phone_num", "NumOfTicket", "pay_method"};
        Cursor cursor = Passenger.query("passenger", rowD, null, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Passenger.close();
        return cursor;
    }


    public boolean Flight_Cancellation (String id)
    {
        Passenger = this.getWritableDatabase();
        id =id.substring(id.length()-1);

        Cursor cursor = Passenger.rawQuery("Select * from passenger where id = ?", new String[] {id});


        if (cursor.getCount() >0)
        {
            long result =Passenger.delete("passenger", "Id = ?", new String[] {id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    public Cursor getFlightInformation(SQLiteDatabase dp) {
        return dp.rawQuery("SELECT  id  FROM passenger", null);

    }

}
