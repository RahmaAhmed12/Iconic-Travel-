package com.example.iconictravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText username,password;
    Button log;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        log=findViewById(R.id.button);
        username=findViewById(R.id.editTextTextPersonName);
        password = findViewById(R.id.editTextTextPassword);
        DB=new DBHelper(this);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass= password.getText().toString();
                if(TextUtils.isEmpty(user)||TextUtils.isEmpty(pass))
                {
                    Toast.makeText(LoginActivity.this, "All fields required", Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean checkuserpass=DB.checkpasswordandname(user,pass);
                    boolean checkad=DB.checkadmin(user,pass );
                    if(checkad == true)
                    {
                        Toast.makeText(LoginActivity.this,"Welcome Admin",Toast.LENGTH_LONG).show();
                        Intent intent= new Intent(getApplicationContext(),HomeAdmin.class);
                        startActivity(intent);
                    }
                    else if(checkuserpass==true )
                    {
                        Toast.makeText(LoginActivity.this,"login  successfully",Toast.LENGTH_LONG).show();
                        Intent intent= new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                    }

                    else
                    {
                        Toast.makeText(LoginActivity.this,"login  failed",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }
}