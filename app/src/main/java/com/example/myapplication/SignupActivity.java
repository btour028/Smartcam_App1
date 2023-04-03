package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    EditText username,password,repassword;
    Button SignIn,SignUp;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        repassword=findViewById(R.id.repassword);
        SignUp=findViewById(R.id.buttonSignUp) ; //register
        SignIn=findViewById(R.id.buttonSignIn);
        DB= new DBHelper(this);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                String repass=repassword.getText().toString();

                if (TextUtils.isEmpty(user)||TextUtils.isEmpty(pass)||TextUtils.isEmpty(repass)){
                    Toast.makeText(SignupActivity.this, "All fields Required", Toast.LENGTH_SHORT).show();}
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser= DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert= DB.insertData(user,pass);
                            if(insert==true){
                                Toast.makeText(SignupActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            } else{
                                Toast.makeText(SignupActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else{
                            Toast.makeText(SignupActivity.this, "User Already Exists", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignupActivity.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, SigninActivity.class));
            }
        });

    }
}