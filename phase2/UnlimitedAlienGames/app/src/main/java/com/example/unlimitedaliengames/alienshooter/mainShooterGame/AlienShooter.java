package com.example.unlimitedaliengames.alienshooter.mainShooterGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.unlimitedaliengames.MainActivity;
import com.example.unlimitedaliengames.R;
import com.example.unlimitedaliengames.alienshooter.instructionPages.CustomizeActivity;
import com.example.unlimitedaliengames.alienshooter.instructionPages.BonusInstructionActivity;
import com.example.unlimitedaliengames.alienshooter.instructionPages.GameOverActivity;

import java.util.ArrayList;
import java.util.List;

public class AlienShooter extends AppCompatActivity implements AlienShooterView {
    private AlienShooterPresenter presenter;
    private static final int numOfAliens = 9;
    public final static String POINTS = "pass points";
    public final static String CORRECT = "pass friendly";
    public final static String INCORRECT = "pass evil";
    public final static String TIME = "pass time";
    public final static String FRIENDLY = "pass friendly custom";
    public final static String EVIL = "pass evil custom";
    //timer
    private Timer timer;
    private TextView timer_text;
    private Button timer_button;
    private List<View> aliens;
    private TextView point_text, friendlyPoint, evilPoint;

    private String time, friendly, evil;
    private View exit;
    private long timeLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alien_shooter);

        Intent intent = getIntent();
        time = intent.getStringExtra(CustomizeActivity.PASS_TIME);
        friendly = intent.getStringExtra(CustomizeActivity.PASS_FRIENDLY);
        evil = intent.getStringExtra(CustomizeActivity.PASS_EVIL);

        aliens = new ArrayList<>();
        generateAliens();
        generateOnClickListener();
        timer_text = findViewById(R.id.alienTimer);
        point_text = findViewById(R.id.alienPoint);
        friendlyPoint = findViewById(R.id.incorrectHits);
        evilPoint = findViewById(R.id.correctHits);


        determineTime();
        timer = new Timer(this, timeLeft);

        timer_button = findViewById(R.id.timer_button);
        setTimerListener();
        exit = findViewById(R.id.exit_button);
        setExitListener();

        presenter = new AlienShooterPresenter(this);
    }

    /**
     * determines time for the game based on the customization chosen by the user
     */
    private void determineTime() {
        if (time.equals("15 seconds")) {
            timeLeft = 15000;
        } else {
            timeLeft = 30000;
        }
    }

    /**
     * Adds an onClickListener to the exit button so that game ends when the user clicks it
     */
    private void setExitListener() {
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endGame();
            }
        });
    }

    /**
     * Adds an onClickListener to the start button so that if the game has not started, this will
     * start the timer and the game
     */
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

    /**
     * Stores the time left when a user leaves the app so that when they return, they can continue
     * the game with their stats before they left
     */
    @Override
    protected void onPause() {
        super.onPause();
        timeLeft = timer.getTimeLeft();
        String temp = "Resume Game";
        timer_button.setText(temp);
        timer.cancel();
    }

    /**
     * If the user exits the app and returns, this will create a new timer with the amount of time
     * remaining based on when the user left
     */
    @Override
    protected void onResume() {
        super.onResume();
        timer = new Timer(this, timeLeft);

    }

    /**
     * Returns to the menu and destroys this activity/game instance
     */
    private void endGame() {
        startActivity(new Intent(this, MainActivity.class));
        timer.cancel();
        finish();
    }

    /**
     * Sets the image of all aliens based on the content description of each alien image button
     */
    public void changeAlienImage() {
        String redAlien = "red alien";
        for (int i = 0; i < 9; i++) {
            if (aliens.get(i).getContentDescription().equals(redAlien)) {
                setRedAlien(i);
            } else {
                setNormalAlien(i);
            }
        }
    }

    /**
     * Sets the alien at index i in aliens to a evil alien with colour scheme based on user
     * customization
     *
     * @param i integer that represents the index of the alien in aliens
     */
    private void setRedAlien(int i) {
        if (evil.equals("Red")) {
            aliens.get(i).setBackgroundResource(R.drawable.red_alien);
        } else {
            aliens.get(i).setBackgroundResource(R.drawable.black_alien);
        }
    }

    /**
     * Sets the alien at index i in aliens to a good alien with colour scheme based on user
     * customization
     *
     * @param i integer that represents the index of the alien in aliens
     */
    private void setNormalAlien(int i) {
        if (friendly.equals("Blue")) {
            aliens.get(i).setBackgroundResource(R.drawable.blue_alien);
        } else {
            aliens.get(i).setBackgroundResource(R.drawable.yellow_alien);
        }
    }

    /**
     * adds all the alien imageButtons to aliens
     */
    private void generateAliens() {
        for (int i = 1; i <= numOfAliens; i++) {
            String temp = "imageButton" + i;
            int tempID = getResources().getIdentifier(temp, "id", getPackageName());
            aliens.add(findViewById(tempID));
        }
    }

    /**
     * Run when activity is closed
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    /**
     * Generates onClickListener to each of the alienButtons representing an alien
     */
    private void generateOnClickListener() {
        for (int i = 0; i < numOfAliens; i++) {
            aliens.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (timer.getIsActive()) {
                        presenter.clickedAlien(aliens, v);
                    }
                }
            });
        }
    }

    /**
     * Makes all alien imageButtons visible
     */
    private void setVisibility() {
        for (View alien : aliens) {
            alien.setVisibility(View.VISIBLE);
        }
    }

    /**
     * starts the timer that corresponds to the time remaining in the game
     */
    private void startTimer() {
        String temp = "Game in progress";
        timer_button.setText(temp);
        timer.setActive();
        timer.start();
    }

    /**
     * updates the label telling the user how much time is remaining
     *
     * @param text a String that tells the user the time remaining currently in the game
     */
    @Override
    public void updateTimer(String text) {
        timer_text.setText(text);
    }

    /**
     * updates the labels corresponding to the current stats of the user for this current game.
     *
     * @param points    the points the user currently has scored
     * @param correct   the number of evil aliens the user has shot
     * @param incorrect the number of good aliens the user has shot
     */
    @Override
    public void updatePoints(int points, int correct, int incorrect) {
        String text = "Points: " + points;
        String correctText = "Evil Aliens Shot: " + correct;
        String incorrectText = "Friendly Aliens Shot: " + incorrect;
        point_text.setText(text);
        friendlyPoint.setText(incorrectText);
        evilPoint.setText(correctText);
    }

    /**
     * when the game ends, this is run and the score screen activity replaces this current activity
     * which is destroyed
     */
    public void finishGame() {
        if (presenter.getPoints() >= 5) {
            Intent intent = bonusIntent();
            startActivity(intent);
        } else {
            Intent intent = createIntent();
            startActivity(intent);
        }
        finish();
    }


    public Intent bonusIntent() {
        Intent intent = new Intent(this, BonusInstructionActivity.class);
        intent.putExtra(POINTS, presenter.getPoints());
        intent.putExtra(CORRECT, presenter.getCorrect());
        intent.putExtra(INCORRECT, presenter.getIncorrect());
        intent.putExtra(TIME, time);
        intent.putExtra(EVIL, evil);
        intent.putExtra(FRIENDLY, friendly);
        return intent;
    }

    /**
     * creates an intent containing the user statistics data from the current game. This intent
     * transitions from this activity to the score screen/ game over activity.
     *
     * @return an Intent that contains the information of the user's statistics in the current game
     */
    public Intent createIntent() {
        Intent intent = new Intent(this, GameOverActivity.class);
        intent.putExtra(POINTS, presenter.getPoints());
        intent.putExtra(CORRECT, presenter.getCorrect());
        intent.putExtra(INCORRECT, presenter.getIncorrect());
        intent.putExtra(TIME, time);
        intent.putExtra(EVIL, evil);
        intent.putExtra(FRIENDLY, friendly);
        intent.putExtra("from", "notBonus");
        return intent;
    }

}