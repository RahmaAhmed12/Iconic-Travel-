package com.example.iconictravel;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HomeAdmin extends AppCompatActivity {

    DatabaseTrip databaseTrip ;
    SQLiteDatabase dp ;
    Cursor cursor ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        databaseTrip = new DatabaseTrip(HomeAdmin.this);
        dp =  databaseTrip.getWritableDatabase();
        ListView listView = (ListView) findViewById(R.id.information) ;
        EditText searc = (EditText) findViewById(R.id.search) ;

        String [] nameFeild = {"Flight Number : " , "Airport : " , "Destination : " ,"Time Of Travel : " ,"Date : ",
                "Number Of Seats : ","Ticket Price : ","Weight Of Bags : " } ;

        cursor = databaseTrip.getTripsDesc(dp);
        ArrayList<ListItem> name = new ArrayList<ListItem>();
        if(cursor.getCount() !=0) {

            cursor.moveToFirst();
            do {
                ListItem listItem = new ListItem(nameFeild[0] + cursor.getString(0) ,
                        nameFeild[1] + cursor.getString(1),
                        nameFeild[2] + cursor.getString(2)  ,
                        nameFeild[3] + cursor.getString(3) ,
                        nameFeild[4] + cursor.getString(4) ,
                        nameFeild[5] + cursor.getString(5) ,
                        nameFeild[6] + cursor.getString(6),
                        nameFeild[7] + cursor.getString(7));

                name.add(listItem);
            } while (cursor.moveToNext());

        }else {
            Intent allTrips= new Intent(HomeAdmin.this , AddTrips.class);
            startActivity(allTrips);
        }
        ListAdapter adapter = new ListAdapter(name);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        searc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cursor= databaseTrip.search(dp,searc.getText().toString());
                if(cursor.getCount() > 0 ){
                    name.clear();
                    cursor.moveToFirst();
                    do{
                        ListItem listItem = new ListItem(nameFeild[0] + cursor.getString(0) ,
                                nameFeild[1] + cursor.getString(1),
                                nameFeild[2] + cursor.getString(2)  ,
                                nameFeild[3] + cursor.getString(3) ,
                                nameFeild[4] + cursor.getString(4) ,
                                nameFeild[5] + cursor.getString(5) ,
                                nameFeild[6] + cursor.getString(6),
                                nameFeild[7] + cursor.getString(7));

                        name.add(listItem);
                    }while (cursor.moveToNext());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    class ListAdapter extends BaseAdapter {
        ArrayList<ListItem> listitem = new ArrayList<ListItem> ();
        ListAdapter(ArrayList<ListItem> listitem){
            this.listitem =listitem ;
        }
        @Override
        public int getCount() {
            return listitem.size();
        }

        @Override
        public Object getItem(int position) {
            return listitem.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.row_item, null);
            TextView flightNumber = (TextView) view.findViewById(R.id.flightnumber) ;
            TextView airport = (TextView) view.findViewById(R.id.airPort) ;
            TextView dest = (TextView) view.findViewById(R.id.dest) ;
            TextView timeoftravvel = (TextView) view.findViewById(R.id.timeofTravel) ;
            TextView date = (TextView) view.findViewById(R.id.date) ;
            TextView numberOfseat = (TextView) view.findViewById(R.id.numberofSeat) ;
            TextView ticket = (TextView) view.findViewById(R.id.ticketprice) ;
            TextView weight = (TextView) view.findViewById(R.id.weightofbags) ;
            flightNumber.setText(listitem.get(position).id );
            airport.setText(listitem.get(position).airport );
            dest.setText(listitem.get(position).destination );
            timeoftravvel.setText(listitem.get(position).timeOfTravel);
            date.setText(listitem.get(position).data) ;
            numberOfseat.setText(listitem.get(position).numberOfSeats );
            ticket.setText(listitem.get(position).ticketPrice );
            weight.setText(listitem.get(position).weightOfBags );
            return view;
        }
    }
    // menu
    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.addmenu){
            Intent allTrips= new Intent(HomeAdmin.this , AddTrips.class);
            startActivity(allTrips);
        }else if(item.getItemId()== R.id.logout){
            Intent allTrips= new Intent(HomeAdmin.this , LoginActivity.class);
            startActivity(allTrips);
        }else if(item.getItemId()== R.id.update){
            Intent allTrips= new Intent(HomeAdmin.this , UpdateTrips.class);
            startActivity(allTrips);
        }
        return super.onOptionsItemSelected(item);
    }

}