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
import android.widget.RadioButton;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RadioButton inside_radio = (RadioButton) findViewById(R.id.inside_radio);
        RadioButton outside_radio =(RadioButton) findViewById(R.id.outside_radio);
        Button next_btn =(Button) findViewById(R.id.next_btn);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!(inside_radio.isChecked()&& outside_radio.isChecked()))
                {
                    if(inside_radio.isChecked())
                    {
                        Intent i = new Intent(HomeActivity.this ,InsideActivity.class);
                        startActivity(i);
                        inside_radio.setChecked(false);
                    }
                    else if (outside_radio.isChecked())
                    {
                        Intent i = new Intent(HomeActivity.this,OutsideActivity.class);
                        startActivity(i);
                        outside_radio.setChecked(false);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Please You Must Chose One ",Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please Chose Just One ",Toast.LENGTH_LONG).show();
                    inside_radio.setChecked(false);
                    outside_radio.setChecked(false);
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

            case R.id.logout: {
                Intent goToLogout = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(goToLogout);
                break;
            }
            case R.id.myProfile:
            {
                Intent goToHome = new Intent(HomeActivity.this, MyProfile.class);
                startActivity(goToHome);
                break;
            }


        }
        return super.onOptionsItemSelected(item);
    }
}