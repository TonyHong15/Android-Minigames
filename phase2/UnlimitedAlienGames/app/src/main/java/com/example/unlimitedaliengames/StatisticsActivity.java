package com.example.unlimitedaliengames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.unlimitedaliengames.userdata.User;

import static com.example.unlimitedaliengames.LoginActivity.PASS_USER;

public class StatisticsActivity extends AppCompatActivity {
    private int totalTime;
    private int totalGamesPlayed;
    private int totalPoints;
    private TextView game, points, time;
    private Button exit, save;

    /**
     * Holds the object that references the current user
     */
    private User currUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Intent intent = getIntent();
        currUser = (User) intent.getSerializableExtra(PASS_USER);

        totalTime = (int) currUser.getTimePlayed();
        totalGamesPlayed = currUser.getGamesPlayed();
        totalPoints = currUser.getTotalPoints();

        game = findViewById(R.id.Game);
        points = findViewById(R.id.Points);
        time = findViewById(R.id.Time);
        exit = findViewById(R.id.BackToMenu);
        save = findViewById(R.id.statisticsSaveButton);

        game.setText("Total Number of Games Played: " + totalGamesPlayed);
        points.setText("Total Points Earned: " + totalPoints);
        time.setText("Total Amount of Time Played: " + totalTime);
        SetupButtonListeners();

    }

    private void SetupButtonListeners() {
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitScoreBoard();
                finish();

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                currUser.writeToFile(getApplicationContext());
            }
        });
    }

    private void exitScoreBoard() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(PASS_USER, currUser);
        startActivity(intent);
        finish();
    }
}
