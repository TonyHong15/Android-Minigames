package com.example.unlimitedaliengames.alienshooter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.unlimitedaliengames.MainActivity;
import com.example.unlimitedaliengames.R;

public class GameOverActivity extends AppCompatActivity{
    private View restart, customize, backToMenu;
    private TextView textPoints, friendly, evil;
    int points, correct, incorrect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        customize = findViewById(R.id.Customize);
        setCustomizeListener();
        backToMenu = findViewById(R.id.BackToMenu);
        setReturnListener();
        restart = findViewById(R.id.Restart);
        setListenerRestart();

        Intent intent = getIntent();

        points = intent.getIntExtra(AlienShooter.POINTS, 0);
        correct = intent.getIntExtra(AlienShooter.CORRECT, 0);
        incorrect = intent.getIntExtra(AlienShooter.INCORRECT, 0);

        textPoints = findViewById(R.id.Points);
        setPoints();
        friendly = findViewById(R.id.FriendlyAlien);
        setFriendly();
        evil = findViewById(R.id.EvilAlien);
        setEvil();

    }

    private void setPoints(){
        String text = "Total Points: "+ points;
        textPoints.setText(text);
    }
    private void setFriendly(){
        String text = "Friendly Aliens Shot: "+ incorrect;
        friendly.setText(text);
    }
    private void setEvil(){
        String text = "Evil Aliens Shot: "+ correct;
        evil.setText(text);
    }
    private void setListenerRestart() {
        restart.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            restartActivity();
            finish();
        }
    });}

    private void setCustomizeListener() {
        customize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customizeActivity();
                finish();
            }
        });
    }

    private void setReturnListener() {
        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu();
                finish();
            }
        });
    }

    private void customizeActivity() {
        startActivity(new Intent(this, CustomizeActivity.class));
    }
    private void restartActivity() {
        startActivity(new Intent(this, AlienShooter.class));
    }

    private void menu() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
