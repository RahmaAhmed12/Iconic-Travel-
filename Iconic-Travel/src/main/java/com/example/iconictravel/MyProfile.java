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

public class MyProfile extends AppCompatActivity {

    Button flightCancellation;
    String id;
    ListView list_profile;

    DatabaseHelperP databaseHelperP;
    DatabaseTrip databaseTrip ;
    MyProfile.ListAdapter adapter ;
    SQLiteDatabase dp,dp2;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        databaseHelperP = new DatabaseHelperP(MyProfile.this);
        dp =  databaseHelperP.getWritableDatabase();
        databaseTrip = new DatabaseTrip(MyProfile.this) ;
        ListView listView = (ListView) findViewById(R.id.list);


        cursor = databaseHelperP.getFlightInformation(dp);
        ArrayList<ListItem> name = new ArrayList<ListItem>();
        if (cursor.getCount() != 0) {

            dp2 =databaseTrip.getWritableDatabase();
            name = databaseTrip.MyProfil(dp2,cursor);


        } else {
            Intent allTrips = new Intent(MyProfile.this, InsideActivity.class);
            startActivity(allTrips);
        }
        adapter = new MyProfile.ListAdapter(name);
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
            View view = inflater.inflate(R.layout.row_item_profile, null);
            Button booking = (Button) view.findViewById(R.id.flight_canceling);
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

                    boolean checked = databaseHelperP.Flight_Cancellation( flightNumber.getText().toString());

                    if(checked)
                        Toast.makeText(getApplicationContext(),"Cancel is done",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getApplicationContext(),"Failed ",Toast.LENGTH_LONG).show();


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
                Intent goToHome = new Intent(MyProfile.this, HomeActivity.class);
                startActivity(goToHome);
                break;
            }
            case R.id.logout: {
                Intent goToLogout = new Intent(MyProfile.this, LoginActivity.class);
                startActivity(goToLogout);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}