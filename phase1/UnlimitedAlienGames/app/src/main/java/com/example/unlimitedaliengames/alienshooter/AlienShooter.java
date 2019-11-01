package com.example.unlimitedaliengames.alienshooter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.unlimitedaliengames.MainActivity;
import com.example.unlimitedaliengames.R;

import java.util.ArrayList;
import java.util.List;

public class AlienShooter extends AppCompatActivity implements AlienShooterView,
        View.OnClickListener {
    private AlienShooterPresenter presenter;
    private static final int numOfAliens = 9;
    //timer
    private Timer timer;
    private TextView timer_text;
    private Button timer_button;
    private List<View> aliens;
    private TextView point_text;

    private String time, friendly, evil;
    private View exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        time = intent.getStringExtra(CustomizeActivity.PASS_TIME);
        friendly = intent.getStringExtra(CustomizeActivity.PASS_FRIENDLY);
        evil = intent.getStringExtra(CustomizeActivity.PASS_EVIL);
        setContentView(R.layout.activity_alien_shooter);
        aliens = new ArrayList<>();
        generateAliens();
        generateOnClickListener();
        timer_text = findViewById(R.id.alienTimer);
        point_text = findViewById(R.id.alienPoint);

        timer = new Timer(this, determineTime());

        timer_button = findViewById(R.id.timer_button);
        setTimerListener();
        exit = findViewById(R.id.exit_button);
        setExitListener();

        presenter = new AlienShooterPresenter(this, new AlienShooterManager());
    }

    private long determineTime() {
        long targetTime;
        if (time.equals("15 seconds")) {
            targetTime = 15000;
        } else {
            targetTime = 30000;
        }
        return targetTime;
    }

    private void setExitListener() {
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endGame();
            }
        });
    }

    private void setTimerListener() {
        timer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!timer.getIsActive()) {
                    startTimer();
                    presenter.randomizeAliens(aliens);
                    setVisibility();
                }
            }
        });
    }

    /*
    End the current instance of game and return to Main menu.
     */
    private void endGame() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


    private void generateAliens() {
        for (int i = 1; i <= numOfAliens; i++) {
            String temp = "imageButton" + i;
            int tempID = getResources().getIdentifier(temp, "id", getPackageName());
            aliens.add(findViewById(tempID));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    private void generateOnClickListener() {
        for (int i = 0; i < numOfAliens; i++) {
            aliens.get(i).setOnClickListener(this);
        }
    }

    private void setVisibility() {
        for (View alien : aliens) {
            alien.setVisibility(View.VISIBLE);
        }
    }

    private void startTimer() {
        String temp = time;
        timer_button.setText(temp);
        timer.setActive(true);
        timer.start();
    }

    @Override
    public void resetTimer() {
        String temp = "Retry Game";
        timer_button.setText(temp);
        timer.setActive(false);
    }

    @Override
    public void updateTimer(String text) {
        timer_text.setText(text);
    }

    @Override
    public void updatePoints(int p) {
        String text = "Points: " + p;
        point_text.setText(text);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (timer.getIsActive()) {
            presenter.clickedAlien(aliens, v);
        }
    }
}