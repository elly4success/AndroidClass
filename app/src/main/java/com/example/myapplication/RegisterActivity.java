package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    //Declaration of our widgets
    EditText nameTxt,emailTxt,phoneTxt,passswordTxt,cpasswordTxt;
    RadioGroup genderRg;
    Button loginBtn,registerBtn;
    String gender="male";
    SQLiteHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Initialize our sqlite database handler
        db = new SQLiteHandler(getApplicationContext());

        //Initialize and connect widgets to corresponding widgets in the layout
        //Initialize edit texts
        nameTxt = findViewById(R.id.name_tv);
        emailTxt = findViewById(R.id.email_tv);
        phoneTxt = findViewById(R.id.phone_tv);
        passswordTxt = findViewById(R.id.pswd_tv);
        cpasswordTxt = findViewById(R.id.cpswd_tv);
        //Initialize radio group
        genderRg = findViewById(R.id.gender);

        //Initialize buttons
        registerBtn = findViewById(R.id.btnRegister);
        loginBtn = findViewById(R.id.btnLogin);

        //Event listener to listen for button clicks
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent to move from one activity to another
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

        //Event listener to listen for button clicks
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Calling the login check
                loginCheck();
            }
        });
    }

    public void loginCheck(){
        //Storing data from edittext in variables of type String
        String name = nameTxt.getText().toString();
        String email = emailTxt.getText().toString();
        String phone = phoneTxt.getText().toString();
        String password = passswordTxt.getText().toString();
        String confirmpassword = cpasswordTxt.getText().toString();

        //Event listener for Radio Group
        genderRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.male:
                        gender = "male";
                        break;
                    case R.id.female:
                        gender = "Female";
                        break;
                }
            }
        });

        //Checking if our Strings are empty
        if(name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()
        || confirmpassword.isEmpty() || gender.isEmpty()){
            //Display a message to the user using a toast
            Toast.makeText(getApplicationContext(),
                    "Please fill all credentials",
                    Toast.LENGTH_LONG).show();
        }else {
            if(password.matches(confirmpassword)){
                //Adding user to database
                db.AddUser(name,email,phone,password,gender);

                //Display a message to the user using a toast
                Toast.makeText(getApplicationContext(),
                        "name is " + name +
                        "email is " + email +
                            "phone is " + phone +
                            "password is " + password +
                            "gender is " + gender,
                        Toast.LENGTH_LONG).show();
            }else{
                //Display a message to the user using a toast
                Toast.makeText(getApplicationContext(),
                        "Passwords don't match",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}
