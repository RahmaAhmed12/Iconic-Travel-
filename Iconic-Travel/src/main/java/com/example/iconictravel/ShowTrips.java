package com.example.iconictravel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowTrips extends AppCompatActivity {
    DatabaseTrip databaseTrip;
    SQLiteDatabase dp;
    Cursor cursor;
    String value1 = "0" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_trips);
        databaseTrip = new DatabaseTrip(ShowTrips.this);
        dp =  databaseTrip.getWritableDatabase();
        Intent i = getIntent();
        String Airport = i.getStringExtra("from");
        String dist = i.getStringExtra("to");


        ListView listView = (ListView) findViewById(R.id.tripsselected);
        String[] nameFeild = {"    Flight Number : ", "  Airport : ", "  Destination : ", "  Time Of Travel : ", "  Date : ",
                "  Number Of Seats : ", "  Ticket Price : ", "  Weight Of Bags : "};

        cursor = databaseTrip.getShowSelectTrip(dp ,Airport , dist);
        ArrayList<ListItem> name = new ArrayList<ListItem>();
        if (cursor.getCount() != 0) {

            cursor.moveToFirst();
            do {
                ListItem listItem = new ListItem(nameFeild[0] + cursor.getString(0),
                        nameFeild[1] + cursor.getString(1),
                        nameFeild[2] + cursor.getString(2),
                        nameFeild[3] + cursor.getString(3),
                        nameFeild[4] + cursor.getString(4),
                        nameFeild[5] + cursor.getString(5),
                        nameFeild[6] + cursor.getString(6),
                        nameFeild[7] + cursor.getString(7));

                name.add(listItem);
            } while (cursor.moveToNext());

        } else {
            Intent allTrips = new Intent(ShowTrips.this, InsideActivity.class);
            startActivity(allTrips);
        }
        ShowTrips.ListAdapter adapter = new ShowTrips.ListAdapter(name);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);


    }



    class ListAdapter extends BaseAdapter {
        ArrayList<ListItem> listitem = new ArrayList<ListItem>();

        ListAdapter(ArrayList<ListItem> listitem) {
            this.listitem = listitem;
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
            View view = inflater.inflate(R.layout.row_item_show, null);
            Button booking = (Button) view.findViewById(R.id.book);
            TextView flightNumber = (TextView) view.findViewById(R.id.flightnumber);
            TextView airport = (TextView) view.findViewById(R.id.airPort);
            TextView dest = (TextView) view.findViewById(R.id.dest);
            TextView timeoftravvel = (TextView) view.findViewById(R.id.timeofTravel);
            TextView date = (TextView) view.findViewById(R.id.date);
            TextView numberOfseat = (TextView) view.findViewById(R.id.numberofSeat);
            TextView ticket = (TextView) view.findViewById(R.id.ticketprice);
            TextView weight = (TextView) view.findViewById(R.id.weightofbags);
            flightNumber.setText(listitem.get(position).id);
            airport.setText(listitem.get(position).airport);
            dest.setText(listitem.get(position).destination);
            timeoftravvel.setText(listitem.get(position).timeOfTravel);
            date.setText(listitem.get(position).data);
            numberOfseat.setText(listitem.get(position).numberOfSeats);
            ticket.setText(listitem.get(position).ticketPrice);
            weight.setText(listitem.get(position).weightOfBags);
            booking.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    value1 = flightNumber.getText().toString();
                    Intent intent = new Intent(ShowTrips.this , BookActivity.class) ;
                    intent.putExtra("id",value1);
                    startActivity(intent);
                }
            });
            return view;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
            {
                Intent goToHome = new Intent(ShowTrips.this, HomeActivity.class);
                startActivity(goToHome);
                break;
            }
            case R.id.myProfile:
            {
                Intent goToHome = new Intent(ShowTrips.this, MyProfile.class);
                startActivity(goToHome);
                break;
            }

            case R.id.logout: {
                Intent goToLogout = new Intent(ShowTrips.this, LoginActivity.class);
                startActivity(goToLogout);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }


}

