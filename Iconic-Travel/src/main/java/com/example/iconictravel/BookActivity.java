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
import android.widget.RadioButton;
import android.widget.Toast;

public class BookActivity extends AppCompatActivity {
    String  id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        Button done = (Button) findViewById(R.id.button);
        Intent i = getIntent();
        final String flightNumber = i.getStringExtra("id");
        final EditText f_name = (EditText) findViewById(R.id.editTextTextPersonName3);
        final EditText s_name = (EditText) findViewById(R.id.editTextTextPersonName4);
        final EditText ph = (EditText) findViewById(R.id.editTextPhone2);
        final EditText ticket = (EditText) findViewById(R.id.editTextNumber);
        final EditText pay = (EditText) findViewById(R.id.editTextTextPersonName6);
        final String f =flightNumber.substring(flightNumber.length()-1);
        final DatabaseHelperP newmpassendger = new DatabaseHelperP(this);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newmpassendger.CreateNewpassenger(Integer.parseInt(f),f_name.getText().toString(), s_name.getText().toString(),
                        ph.getText().toString(), Integer.parseInt(ticket.getText().toString()), pay.getText().toString());
                Toast.makeText(BookActivity.this, "Data Saved ", Toast.LENGTH_LONG).show();
               /* Intent i= new Intent(BookActivity.this,HomeActivity.class);
                startActivity(i);*/


            }

        });



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
                Intent goToHome = new Intent(BookActivity.this, HomeActivity.class);
                startActivity(goToHome);
                break;
            }
            case R.id.myProfile:
            {
                Intent goToHome = new Intent(BookActivity.this, MyProfile.class);
                startActivity(goToHome);
                break;
            }

            case R.id.logout: {
                Intent goToLogout = new Intent(BookActivity.this, LoginActivity.class);
                startActivity(goToLogout);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}