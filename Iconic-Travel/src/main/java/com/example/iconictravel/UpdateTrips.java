package com.example.iconictravel;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateTrips extends AppCompatActivity {
    final DatabaseTrip Trip = new DatabaseTrip(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_trip);
        Button update = (Button) findViewById(R.id.update);

        final EditText id = (EditText) findViewById(R.id.editTextId);
        final EditText airport = (EditText) findViewById(R.id.editTextAirport);
        final EditText destination = (EditText) findViewById(R.id.editTextdestination);
        final EditText timeOfTravel = (EditText) findViewById(R.id.editTexttimeOfTravel);
        final EditText data = (EditText) findViewById(R.id.editTextdata);
        final EditText numberOfSeats = (EditText) findViewById(R.id.editTextnumberOfSeats);
        final EditText ticketPrice = (EditText) findViewById(R.id.editTextticketPrice);
        final EditText weightOfBags = (EditText) findViewById(R.id.editTextweightOfBags);
        Button delete = (Button) findViewById(R.id.delete);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdate = Trip.updateTrip(id.getText().toString(),airport.getText().toString()
                        , destination.getText().toString(), timeOfTravel.getText().toString()
                        , data.getText().toString() , Integer.parseInt(numberOfSeats.getText().toString()),
                        Integer.parseInt(ticketPrice.getText().toString()),Integer.parseInt(weightOfBags.getText().toString()));

                if(isUpdate==true)
                    Toast.makeText(UpdateTrips.this, "Data Successfully Updated!", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(UpdateTrips.this, "Something went wrong :(.", Toast.LENGTH_LONG).show();

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String deleteId = id.getText().toString();
                boolean checkDeleteId = Trip.deleteTrip(deleteId);
                if(checkDeleteId == true)
                    Toast.makeText(UpdateTrips.this, "Data Successfully Deleted!", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(UpdateTrips.this, "Something went wrong :(.", Toast.LENGTH_LONG).show();
            }
        });
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
            Intent allTrips= new Intent(UpdateTrips.this , HomeAdmin.class);
            startActivity(allTrips);
        }else if(item.getItemId()== R.id.logout){
            Intent allTrips= new Intent(UpdateTrips.this , LoginActivity.class);
            startActivity(allTrips);
        }else if(item.getItemId()== R.id.addmenu){
            Intent allTrips= new Intent(UpdateTrips.this , AddTrips.class);
            startActivity(allTrips);
        }
        return super.onOptionsItemSelected(item);
    }


}


