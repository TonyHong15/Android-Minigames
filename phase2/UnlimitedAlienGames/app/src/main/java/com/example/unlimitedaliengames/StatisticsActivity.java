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
    private Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);   

        Intent intent = getIntent();
        User curr = (User) intent.getSerializableExtra(PASS_USER);

        totalTime = curr.getGamesPlayed();
        totalGamesPlayed = curr.getGamesPlayed();
        totalPoints = curr.getTotalPoints();

        game = findViewById(R.id.Game);
        points = findViewById(R.id.Points);
        time = findViewById(R.id.Time);
        exit = findViewById(R.id.BackToMenu);

        String text1 = "Game Played: " + totalGamesPlayed;
        String text2 = "Total Points: " + totalPoints;
        String text3 = "Total Time Played" + totalTime;
        game.setText(text1);
        points.setText(text2);
        time.setText(text3);
        EndButtonListener();

    }

    private void EndButtonListener() {
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitScoreBoard();
                finish();

            }
        });
    }

    private void exitScoreBoard(){
        startActivity(new Intent(this, MainActivity.class));
    }
}
