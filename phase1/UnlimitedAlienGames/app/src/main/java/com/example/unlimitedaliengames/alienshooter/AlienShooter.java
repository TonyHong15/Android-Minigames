package com.example.unlimitedaliengames.alienshooter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.unlimitedaliengames.R;

import java.util.List;

public class AlienShooter extends AppCompatActivity implements AlienShooterView {

    private List<ImageButton> aliens;
    private AlienShooterPresenter presenter;

    //timer
    private Timer timer;
    private TextView timer_text;
    private Button timer_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alien_shooter);
        timer_text = findViewById(R.id.alienTimer);
        timer = new Timer(timer_text, this);
        timer_button = findViewById(R.id.timer_button);
        timer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!timer.getIsActive())
                    startTimer();
            }
        });

    }

    public void startTimer() {
        timer_button.setVisibility(View.INVISIBLE);
        timer.setActive(true);
        timer.start();
    }

    @Override
    public void resetTimer() {
        timer_button.setVisibility(View.VISIBLE);
        timer.setActive(false);
    }

    @Override
    public void updateTimer(String text) {
        timer_text.setText(text);
    }
}
