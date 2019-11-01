package com.example.unlimitedaliengames;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

/*
An Login menu for the game.
 */
public class LoginActivity extends AppCompatActivity {

    /*
    Components in the layout.
     */
    private EditText userName;
    private EditText password;
    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        login = findViewById(R.id.loginButton);
        register = findViewById(R.id.registerButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSuccess();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptRegister();
            }
        });
    }

    /*
    Go to main menu if successfully logged in.
     */
    void onSuccess(){
        startActivity(new Intent(this, MainActivity.class));
    }

    /*
    Attempt to register.
     */
    void attemptRegister(){
        String user = userName.getText().toString();
        String pass = password.getText().toString();
    }
}
