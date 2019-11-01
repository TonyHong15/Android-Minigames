package com.example.unlimitedaliengames.alienshooter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.unlimitedaliengames.MainActivity;
import com.example.unlimitedaliengames.R;
import com.example.unlimitedaliengames.alienpainter.AlienPainter;

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

    private TextView instructionTitle;
    private TextView instructions;
    private View exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alien_shooter);
        instructions = findViewById(R.id.instructions);
        instructionTitle = findViewById(R.id.instructionTitle);
        aliens = new ArrayList<>();
        generateAliens();
        generateOnClickListener();
        timer_text = findViewById(R.id.alienTimer);
        point_text = findViewById(R.id.alienPoint);
        timer = new Timer(this);
        timer_button = findViewById(R.id.timer_button);
        timer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!timer.getIsActive()) {
                    startTimer();
                    setVisibility();
                    presenter.randomizeAliens(aliens);
                }
            }
        });
        exit = findViewById(R.id.exit_button);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endGame();
            }
        });
        presenter = new AlienShooterPresenter(this, new AlienShooterManager());
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
        instructionTitle.setVisibility(View.INVISIBLE);
        instructions.setVisibility(View.INVISIBLE);
        for (View alien : aliens) {
            alien.setVisibility(View.VISIBLE);
        }
    }

    private void startTimer() {
        String temp = "Game in Progress";
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