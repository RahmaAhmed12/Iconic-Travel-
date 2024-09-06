package com.example.iconictravel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTrips extends AppCompatActivity {
    final DatabaseTrip newTrip = new DatabaseTrip(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trips);
        Button add = (Button) findViewById(R.id.update);

        final EditText airport = (EditText) findViewById(R.id.editTextAirport);
        final EditText destination = (EditText) findViewById(R.id.editTextdestination);
        final EditText timeOfTravel = (EditText) findViewById(R.id.editTexttimeOfTravel);
        final EditText numberOfSeats = (EditText) findViewById(R.id.editTextnumberOfSeats);
        final EditText ticketPrice = (EditText) findViewById(R.id.editTextticketPrice);
        final EditText weightOfBags = (EditText) findViewById(R.id.editTextweightOfBags);
        final EditText data = (EditText) findViewById(R.id.editTextdata);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newEntry = airport.getText().toString();
                if(airport.length()!=0&&destination.getText().toString().length()!=0 &&data.getText().toString().length()!=0 &&
                        timeOfTravel.getText().toString().length()!=0   ) {
                    AddData(newEntry,destination.getText().toString(), timeOfTravel.getText().toString(),Integer.parseInt(numberOfSeats.getText().toString()),
                            Integer.parseInt(ticketPrice.getText().toString()),Integer.parseInt(weightOfBags.getText().toString()),data.getText().toString());
                    airport.setText("");
                    destination.setText("");
                    data.setText("");
                    timeOfTravel.setText("");
                    numberOfSeats.setText("");
                    weightOfBags.setText("");
                    ticketPrice.setText("");

                }else {
                    Toast.makeText(AddTrips.this, "You must put something in the text field!", Toast.LENGTH_LONG).show();
                }
            }

        });


    }
    public void AddData( String airport ,String destination,String timeOfTravel,int numberOfSeats , int ticketPrice ,int  weightOfBags, String data ) {

        boolean insertData = newTrip.CreateNewTrip(  airport , destination, timeOfTravel, numberOfSeats ,  ticketPrice ,  weightOfBags,  data );

        if(insertData==true){
            Toast.makeText(this, "Data Successfully Inserted!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Something went wrong :(.", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.home){
            Intent allTrips= new Intent(AddTrips.this , HomeAdmin.class);
            startActivity(allTrips);
        }else if(item.getItemId()== R.id.logout){
            Intent allTrips= new Intent(AddTrips.this , LoginActivity.class);
            startActivity(allTrips);
        }else if(item.getItemId()== R.id.update){
            Intent allTrips= new Intent(AddTrips.this , UpdateTrips.class);
            startActivity(allTrips);
        }
        return super.onOptionsItemSelected(item);
    }

}
