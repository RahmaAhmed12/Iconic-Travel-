package com.example.iconictravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText username,password,confirm,email;
    Button reg,loginn ;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        reg=findViewById(R.id.button);
        username=findViewById(R.id.editTextTextPersonName);
        email=findViewById(R.id.editTextTextPersonName2);
        password=findViewById(R.id.editTextTextPassword2);
        confirm=findViewById(R.id.editTextTextPassword4);
        loginn=findViewById(R.id.button4);
        DB = new DBHelper(this);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                String em=email.getText().toString();
                String pass = password.getText().toString();
                String con= confirm.getText().toString();
                if(TextUtils.isEmpty(user)|| TextUtils.isEmpty(pass) || TextUtils.isEmpty(con)|| TextUtils.isEmpty(em))
                {
                    Toast.makeText(RegisterActivity.this, "All Fields required",Toast.LENGTH_LONG).show();
                }
                else
                {
                    if(pass.equals(con))
                    {
                        boolean checker= DB.checkpassword(pass);
                        if(checker==false){
                            boolean insert=DB.insertdata(user,pass);
                            if(insert==true)
                            {
                                Toast.makeText(RegisterActivity.this,"Registed successfully",Toast.LENGTH_LONG).show();
                                Intent intent= new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(intent);
                            } else
                            {
                                Toast.makeText(RegisterActivity.this,"Already have account",Toast.LENGTH_LONG).show();
                            }

                        }

                    }
                }
            }
        });
        loginn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });


    }

}