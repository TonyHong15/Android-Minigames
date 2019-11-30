package com.example.unlimitedaliengames.alienpainter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.unlimitedaliengames.R;

/**
 * The Game Over Screen for alien painter
 */
class AlienPainterGameOverActivity extends AppCompatActivity {


    private int numMoves, timeLeft;

    private TextView numMovesTextView;

    private TextView timeLeftTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alien_painter_game_over);

        Intent intent = getIntent();
        intent.getIntExtra(AlienPainter.NUM_MOVES, numMoves);
        intent.getIntExtra(AlienPainter.TIME_LEFT, timeLeft);

        numMovesTextView = findViewById(R.id.numMovesTextView);
        timeLeftTextView = findViewById(R.id.timeLeftTextView);

        numMovesTextView.setText("Number of Moves: " + numMoves);
        timeLeftTextView.setText("Time left: " + timeLeft);
    }
}
