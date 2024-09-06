package com.example.iconictravel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

import android.widget.ListView;
public class DatabaseTrip extends SQLiteOpenHelper {

    SQLiteDatabase tripDatabase;
    DatabaseTrip databaseTrip ;

    ArrayAdapter<StringBuffer> tripsAdapter ;
    String[] nameFeild = {"Flight Number : ", "Airport : ", "Destination : ", "Time Of Travel : ", "Date : ",
            "Number Of Seats : ", "Ticket Price : ", "Weight Of Bags : "};

    public DatabaseTrip( Context context) {
        super(context,"trip",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table trip (id integer primary key AUTOINCREMENT,"+
                "airport text not null, destination text not null , timeOfTravel text not null , data text  not null , numberOfSeats integer ,ticketPrice integer not null, " +
                "weightOfBags integer not null  )" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists trip");
        onCreate(db);

    }

    public boolean CreateNewTrip ( String airport ,String destination,String timeOfTravel,int numberOfSeats , int ticketPrice ,int  weightOfBags, String data  ){
        ContentValues row = new  ContentValues();

        row.put("airport",airport);
        row.put("destination",destination);
        row.put("timeOfTravel",timeOfTravel);
        row.put("data",data);
        row.put("numberOfSeats",numberOfSeats);
        row.put("ticketPrice",ticketPrice);
        row.put("weightOfBags",weightOfBags);


        tripDatabase = getWritableDatabase();
        long result =tripDatabase.insert("trip",null,row);
        //if date as inserted incorrectly it will return -1
        tripDatabase.close();
        if (result == -1) {
            return false;
        } else {
            return true;
        }



    }


    public Cursor getTripsDesc(SQLiteDatabase dp) {
        return dp.rawQuery("SELECT  * FROM trip", null);
    }
    public  Cursor search(SQLiteDatabase dp,String id){
        return dp.rawQuery("SELECT * FROM trip where id like '%" + id + "%' ", null);
    }
    public  ArrayList<ListItem> MyProfil(SQLiteDatabase dp,Cursor cursor){
        ArrayList<ListItem> details = new ArrayList<ListItem>();
        ArrayList<String>  name= new ArrayList<>() ;
        int i = 0;
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                String s = cursor.getString(0);
                name.add(s);

            } while (cursor.moveToNext());

            Cursor c ;
            do {
                c = search(dp, name.get(i));
                c.moveToFirst();
                if (c.getCount() != 0) {
                    ListItem listItem = new ListItem(nameFeild[0] + c.getString(0),
                            nameFeild[1] + c.getString(1),
                            nameFeild[2] + c.getString(2),
                            nameFeild[3] + c.getString(3),
                            nameFeild[4] + c.getString(4),
                            nameFeild[5] + c.getString(5),
                            nameFeild[6] + c.getString(6),
                            nameFeild[7] + c.getString(7));

                    details.add(listItem);
                    i++;
                }

            }while (name.size() != i) ;
        }
        return details;
    }

    public boolean updateTrip (String id, String airport ,String destination,String timeOfTravel, String data,int numberOfSeats , int ticketPrice ,int  weightOfBags){
        tripDatabase = this.getWritableDatabase();
        ContentValues row = new  ContentValues();
        row.put("Id",id);
        row.put("airport",airport);
        row.put("destination",destination);
        row.put("timeOfTravel",timeOfTravel);
        row.put("data",data);
        row.put("numberOfSeats",numberOfSeats);
        row.put("ticketPrice",ticketPrice);
        row.put("weightOfBags",weightOfBags);

        Cursor cursor = tripDatabase.rawQuery("Select * from trip where Id = ?", new String[] {id});
        if (cursor.getCount() >0)
        {
            long result =tripDatabase.update("trip", row, "Id = ?", new String[] {id});
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

    public boolean deleteTrip (String id)
    {
        tripDatabase = this.getWritableDatabase();
        Cursor cursor = tripDatabase.rawQuery("Select * from trip where Id = ?", new String[] {id});
        if (cursor.getCount() >0)
        {
            long result =tripDatabase.delete("trip", "Id = ?", new String[] {id});
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

    public Cursor getShowSelectTrip(SQLiteDatabase dp,String airport,String dest) {

        return  dp.rawQuery("SELECT * FROM trip where airport like '%" + airport + "%' and destination like '%" + dest + "%'" , null);

    }

}

