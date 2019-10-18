package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    //Declaration of our widgets
    EditText emailTxt,pswdTxt;
    Button btnLogin,btnRegister,btnResetPswd;
    SQLiteHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialize our sqlite database handler
        db = new SQLiteHandler(getApplicationContext());

        //Initialize and connect widgets to corresponding widgets in the layout
        //Initialize edit texts
        emailTxt = findViewById(R.id.email_tv);
        pswdTxt = findViewById(R.id.pswd_tv);

        //Initialize buttons
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        //Event listener to listen for button clicks
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Storing data from edit text in variables of type String
                String email =  emailTxt.getText().toString();
                String password = pswdTxt.getText().toString();

                //Checking if our Strings are empty
                if(email.isEmpty() || password.isEmpty()){
                    //Display a message to the user using a toast
                    Toast.makeText(LoginActivity.this,
                            "Please input all credentials",
                            Toast.LENGTH_LONG).show();
                }else {
                    //Check if our user exists in the sqlite database
                    if(db.Login(email,password)){
                        //Intent to move from one activity to another
                        Intent intent = new Intent(LoginActivity.this
                                ,MainActivity.class);
                        startActivity(intent);
                    }else{
                        //Display a message to the user using a toast
                        Toast.makeText(LoginActivity.this,
                                "Wrong credentials",
                                Toast.LENGTH_LONG).show();
                    }
                    //Display a message to the user using a toast
                    Toast.makeText(LoginActivity.this,
                            "email is " + email +
                                    "password is " + password,
                            Toast.LENGTH_LONG).show();

                }
            }
        });

        //Event listener to listen for button clicks
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent to move from one activity to another
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
