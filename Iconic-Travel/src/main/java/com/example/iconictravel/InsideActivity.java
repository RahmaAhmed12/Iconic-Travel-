package com.example.iconictravel;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class InsideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside);


        final Spinner from_in =(Spinner) findViewById(R.id.from_spninner);
        ArrayAdapter<CharSequence>data1 =ArrayAdapter.createFromResource(this,R.array.inside_cities,R.layout.spinner_text);
        data1.setDropDownViewResource(R.layout.spinner_dropdown);
        from_in.setAdapter(data1);

        final Spinner to_in =(Spinner) findViewById(R.id.to_spinner);
        ArrayAdapter<CharSequence>data2 =ArrayAdapter.createFromResource(this,R.array.inside_cities,R.layout.spinner_text);
        data2.setDropDownViewResource(R.layout.spinner_dropdown);
        to_in.setAdapter(data2);
        Button show_btn =(Button) findViewById(R.id.show_btn);
        show_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value1 = from_in.getSelectedItem().toString();
                String value2 =to_in.getSelectedItem().toString();
                if(value1==value2){
                    Toast.makeText(getApplicationContext(),"Please chose different cities",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent i=new Intent(InsideActivity.this ,ShowTrips.class);
                    i.putExtra("from",value1);
                    i.putExtra("to",value2);
                    startActivity(i);
                }




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
                Intent goToHome = new Intent(InsideActivity.this, HomeActivity.class);
                startActivity(goToHome);
                break;
            }
            case R.id.myProfile:
            {
                Intent goToHome = new Intent(InsideActivity.this, MyProfile.class);
                startActivity(goToHome);
                break;
            }

            case R.id.logout: {
                Intent goToLogout = new Intent(InsideActivity.this, LoginActivity.class);
                startActivity(goToLogout);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}