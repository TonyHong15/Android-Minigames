package com.example.unlimitedaliengames.alienshooter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.unlimitedaliengames.R;

public class BonusInstructionActivity extends AppCompatActivity {
    private  Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus_instruction);
        startButton = findViewById(R.id.BonusStartButton);
        setBonusStartListener();
    }

    private void setBonusStartListener() {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBonusRound();
                finish();
            }
        });
    }

    private void startBonusRound(){
        startActivity(new Intent(this, BonusRound.class));

    }
}
