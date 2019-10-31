package com.example.unlimitedaliengames.alienpainter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unlimitedaliengames.R;


public class AlienPainter extends AppCompatActivity implements View.OnClickListener, AlienPainterView {

    private ImageButton[][] grid = new ImageButton[3][3];

    /**
     * Counts the number of moves the player has made
     */
    private int moveCount = 0;

    /**
     * Holds the textView for number of moves the player has made
     */
    private TextView painterTextViewMoves;

    /**
     * Holds the textView for amount of time that has passed
     */
    private TextView painterTextViewTime;

    /**
     * Holds the instance of AlienPainterTimer for the...timer
     */
    private AlienPainterTimer painterTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alien_painter);

        Intent intent = getIntent();  //Leaving this here in case we need to send something through the intent in the future

        painterTextViewMoves = findViewById(R.id.painterTextView1);
        painterTextViewTime = findViewById(R.id.painterTextView2);

        //Initialize the Timer
        painterTimer = new AlienPainterTimer(this);

        //Constructs the 2D array for the imageButtons
        String imageButtonID;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                imageButtonID = "painterImageButton_" + i + j;

                //This step allows easy assignment of each imageButton with a nested loop
                int resID = this.getResources().getIdentifier(imageButtonID, "id", this.getPackageName());
                grid[i][j] = findViewById(resID);

                grid[i][j].setOnClickListener(this); //Response to each button click

                //Generate a random number to decide whether to put white or black here
                double x = Math.random() * 2;
                if (x < 1) {
                    grid[i][j].setImageResource(R.drawable.black_circle);
                    grid[i][j].setContentDescription(getString(R.string.alien_painter_black));
                } else if (x < 2) {
                    grid[i][j].setImageResource(R.drawable.white_circle);
                    grid[i][j].setContentDescription(getString(R.string.alien_painter_white));
                }

            }
        }

        //Make sure the grid isn't entirely black at the beginning
        grid[1][1].setImageResource(R.drawable.white_circle);
        grid[1][1].setContentDescription("white");

        //Start the Timer
        startTimer();

    }

    /**
     * The event in which any of the imageButtons are pressed
     *
     * @param v The view
     */
    @Override
    public void onClick(View v) {
        //Checks whether the time is still active and whether the win condition has been met
        if (painterTimer.getIsActive() && !checkWinCondition()) {
            if (v.getContentDescription().equals("white")) {
                v.setContentDescription(getString(R.string.alien_painter_black_clicked));
            } else {
                v.setContentDescription(getString(R.string.alien_painter_white_clicked));
            }
            moveCount++;
            painterTextViewMoves.setText("Number of Moves: " + moveCount);

            flipButton();

            if (checkWinCondition()) {
                Toast.makeText(this, "You have won!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    /**
     * Checks whether all the player has completed the task. In this case, all the buttons should
     * be displaying a black circle.
     *
     * @return Whether the player has won or not
     */
    private boolean checkWinCondition() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!grid[i][j].getContentDescription().equals("black")) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Flips the imageButtons around the imageButton the user clicked,
     * including the one the user clicked.
     */
    private void flipButton() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j].getContentDescription().equals(getString
                        (R.string.alien_painter_white_clicked))) {
                    flip(i, j, "black"); //flip the clicked one
                    flipAdjacent(i, j); //flip the ones near the clicked one
                } else if (grid[i][j].getContentDescription().equals(getString
                        (R.string.alien_painter_black_clicked))) {
                    flip(i, j, "white"); //flip the clicked one
                    flipAdjacent(i, j); //flip the ones near the clicked one
                }
            }
        }
    }

    /**
     * Flips the colour of the target imageButton, as indicated by i and j
     *
     * @param i      the row the imageButton is at
     * @param j      the column the imageButton is at
     * @param colour the current colour of the imageButton
     */
    private void flip(int i, int j, String colour) {
        if (colour.equals("white")) {
            grid[i][j].setImageResource(R.drawable.black_circle);
            grid[i][j].setContentDescription(getString(R.string.alien_painter_black));
        } else {
            grid[i][j].setImageResource(R.drawable.white_circle);
            grid[i][j].setContentDescription(getString(R.string.alien_painter_white));
        }

    }

    /**
     * Flips the imageButtons around the target imageButton, if applicable.
     *
     * @param i the row of the target imageButton
     * @param j the column of the target imageButton
     */
    private void flipAdjacent(int i, int j) {

        //For rows
        if (i == 0) {
            flip(i + 1, j, grid[i + 1][j].getContentDescription().toString());
        } else if (i == 2) {
            flip(i - 1, j, grid[i - 1][j].getContentDescription().toString());
        } else {
            flip(i + 1, j, grid[i + 1][j].getContentDescription().toString());

            flip(i - 1, j, grid[i - 1][j].getContentDescription().toString());
        }

        //For columns
        if (j == 0) {
            flip(i, j + 1, grid[i][j + 1].getContentDescription().toString());
        } else if (j == 2) {
            flip(i, j - 1, grid[i][j - 1].getContentDescription().toString());
        } else {
            flip(i, j + 1, grid[i][j + 1].getContentDescription().toString());

            flip(i, j - 1, grid[i][j - 1].getContentDescription().toString());
        }
    }

    /**
     * Starts the timer.
     */
    @Override
    public void startTimer() {
        painterTimer.setActive(true);
        painterTimer.start();
    }

    /**
     * When asking the player whether to play again and they answer yes, this is called.
     */
    @Override
    public void resetTimer() {

    }

    /**
     * Updates the time displayed on screen.
     *
     * @param text the text to update painterTextViewTime with.
     */
    @Override
    public void updateTimer(String text) {
        painterTextViewTime.setText("Time Remaining: " + text);
    }

    /**
     * Calls this when the timer is done.
     */
    @Override
    public void TimerExpired() {
        //Inform the player they have failed
        Toast.makeText(this, "You have lost!", Toast.LENGTH_SHORT).show();
        //Ask whether the player wants to try again
    }
}
