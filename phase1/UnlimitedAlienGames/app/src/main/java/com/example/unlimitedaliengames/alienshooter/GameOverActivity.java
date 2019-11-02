package com.example.unlimitedaliengames.alienshooter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.unlimitedaliengames.MainActivity;
import com.example.unlimitedaliengames.R;

public class GameOverActivity extends AppCompatActivity{
    private View restart, customize, backToMenu, gameInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        customize = findViewById(R.id.Customize);
        backToMenu = findViewById(R.id.BackToMenu);
        restart = findViewById(R.id.Restart);
        setListenerRestart();
        gameInfo = findViewById(R.id.Game_Info);
    }

    private void setListenerRestart() {}

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

    private void menu() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
