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

public class AlienShooter extends AppCompatActivity {

    private List<ImageButton> aliens;
    private AlienShooterPresenter presenter;

    //timer
    private long time_left = 30000;
    private TextView timer_text;
    private Button timer_button;
    private boolean use_timer = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alien_shooter);

        timer_text = findViewById(R.id.alienTimer);
        timer_button = findViewById(R.id.timer_button);

        timer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (use_timer)
                    startTimer();
            }
        });

    }

    public void startTimer() {
        use_timer = false;
        timer_button.setVisibility(View.INVISIBLE);
        new CountDownTimer(time_left, 1000) {

            public void onTick(long seconds) {
                String text = "Time: " + seconds / 1000;
                timer_text.setText(text);
            }

            public void onFinish() {
            }
        }.start();

    }

    void changeAlien() {

    }

}
