package com.example.unlimitedaliengames.alienpainter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.unlimitedaliengames.R;

/**
 * The Game Over Screen for alien painter
 */
public class AlienPainterGameOverActivity extends AppCompatActivity {

    private boolean isVictorious;

    private int numMoves, timeLeft;

    private TextView numMovesTextView;

    private TextView timeLeftTextView;

    /**
     * This method is automatically called when the view switches to AlienPainterGameOverActivity
     * @param savedInstanceState the instanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alien_painter_game_over);

        Intent intent = getIntent();
        intent.getBooleanExtra(AlienPainter.SCOREBOARDSTATUS, isVictorious);
        intent.getIntExtra(AlienPainter.NUM_MOVES, numMoves);
        intent.getIntExtra(AlienPainter.TIME_LEFT, timeLeft);

        numMovesTextView = findViewById(R.id.numMovesTextView);
        timeLeftTextView = findViewById(R.id.timeLeftTextView);

        numMovesTextView.setText("Number of Moves: " + numMoves);
        timeLeftTextView.setText("Time left: " + timeLeft);
    }
}
