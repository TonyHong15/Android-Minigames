package com.example.unlimitedaliengames;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.unlimitedaliengames.userdata.*;

/*
An Login menu for the game.
 */
public class LoginActivity extends AppCompatActivity {

    /*
    Components in the layout.
     */
    private EditText username;
    private EditText password;
    private TextView display;

    /*
    Array of users, currently only support 16 users.
     */
    private UserDatabase users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        display = findViewById(R.id.messageDisplay);

        users = new UserDatabase();

        //handling clicking for login
        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //handling clicking for register.
        findViewById(R.id.registerButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptRegister();
            }
        });
    }

    /*
    Attempt to put a user into this database, if duplicated, change the password.
     */
    private void attemptRegister() {
        String user = username.getText().toString();
        String pass = password.getText().toString();
        if(users.attemptRegister(user, pass)){
            display.setText(getString(R.string.register_ok));
        }else{
            display.setText(getString(R.string.register_fail));
        }
    }

    /*
    Attempt to login based on given user name and password.
     */
    void attemptLogin(String user, String pass){

    }

    /*
    Go to main menu if successfully logged in.
    */
    void onSuccess(User currUser){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}