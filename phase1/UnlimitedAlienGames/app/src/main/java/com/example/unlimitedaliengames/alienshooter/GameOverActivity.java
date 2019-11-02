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
    private TextView gameInfo;
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
        gameInfo= findViewById(R.id.GameOver);
        Intent intent = getIntent();

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
