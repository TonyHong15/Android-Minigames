package com.example.unlimitedaliengames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.unlimitedaliengames.alienguesser.AlienGuesser;
import com.example.unlimitedaliengames.alienpainter.AlienPainter;
import com.example.unlimitedaliengames.alienshooter.AlienShooter;
import com.example.unlimitedaliengames.userdata.*;

public class MainActivity extends AppCompatActivity {

    private User currUser;
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        currUser = (User) intent.getSerializableExtra(LoginActivity.PASS_USER);
    }

    public void goToAlienGuesser(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, AlienGuesser.class);
        intent.putExtra(LoginActivity.PASS_USER, currUser);
        startActivity(intent);
        finish();
    }

    public void goToAlienPainter(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, AlienPainter.class);
        intent.putExtra(LoginActivity.PASS_USER, currUser);
        startActivity(intent);
        finish();
    }

    public void goToAlienShooter(View view) {
        // Do something in response to button
        startActivity(new Intent(this, AlienShooter.class));
        finish();
    }

}
