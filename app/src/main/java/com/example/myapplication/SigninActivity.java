package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SigninActivity extends AppCompatActivity {


    EditText username,password;
    Button signIn,intro;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        username=findViewById(R.id.username1);
        password=findViewById(R.id.password1);
        signIn=findViewById(R.id.buttonSignIn1);
        intro=findViewById(R.id.buttonIntro1);
        DB= new DBHelper(this);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();

                if (TextUtils.isEmpty(user)|| TextUtils.isEmpty(pass))
                    Toast.makeText(SigninActivity.this, "All fields Required", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass= DB.checkusernamepassword(user,pass);
                    if (checkuserpass==true){
                        Toast.makeText(SigninActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    } else{
                        Toast.makeText(SigninActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SigninActivity.this, IntroActivity.class));
            }
        });


    }
}