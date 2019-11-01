package com.example.unlimitedaliengames;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.unlimitedaliengames.userdata.UserDatabase;

/*
An Login menu for the game.
 */
public class LoginActivity extends AppCompatActivity {

    /*
    Components in the layout.
     */
    private EditText userName;
    private EditText password;

    /*
    Array of users, currently only support 16 users.
     */
    private UserDatabase users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);

        users = new UserDatabase();

        //handling clicking for login
        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSuccess();
            }
        });

        //handling clicking for register.
        findViewById(R.id.registerButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userName.getText().toString();
                String pass = password.getText().toString();
                users.attemptRegister(user, pass);
            }
        });
    }

    /*
    Go to main menu if successfully logged in.
     */
    void onSuccess(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    /*
    Attempt to login based on given user name and password.
     */
    void attemptLogin(String user, String pass){

    }
}