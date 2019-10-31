package com.example.unlimitedaliengames.alienpainter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.unlimitedaliengames.R;


public class AlienPainter extends AppCompatActivity implements View.OnClickListener {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alien_painter);

        Intent intent = getIntent();  //Leaving this here in case we need to send something through the intent in the future

        painterTextViewMoves = findViewById(R.id.painterTextView1);
        painterTextViewTime = findViewById(R.id.painterTextView2);

        //Constructs the 2D array for the
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

    }

    /**
     * The event in which any of the imageButtons are pressed
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v.getContentDescription().equals("white")) {
            v.setContentDescription(getString(R.string.alien_painter_black_clicked));
        } else {
            v.setContentDescription(getString(R.string.alien_painter_white_clicked));
        }
        moveCount++;
        painterTextViewMoves.setText("Number of Moves: " + moveCount);

        flipButton();


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
            if (grid[i + 1][j].getContentDescription().equals(getString(R.string.alien_painter_black))) {
                flip(i + 1, j, "black");
            } else {
                flip(i + 1, j, "white");
            }
        } else if (i == 2) {
            if (grid[i - 1][j].getContentDescription().equals(getString(R.string.alien_painter_black))) {
                flip(i - 1, j, "black");
            } else {
                flip(i - 1, j, "white");
            }
        } else {
            if (grid[i + 1][j].getContentDescription().equals(getString(R.string.alien_painter_black))) {
                flip(i + 1, j, "black");
            } else {
                flip(i + 1, j, "white");
            }

            if (grid[i - 1][j].getContentDescription().equals(getString(R.string.alien_painter_black))) {
                flip(i - 1, j, "black");
            } else {
                flip(i - 1, j, "white");
            }
        }

        //For columns
        if (j == 0) {
            if (grid[i][j + 1].getContentDescription().equals(getString(R.string.alien_painter_black))) {
                flip(i, j + 1, "black");
            } else {
                flip(i, j + 1, "white");
            }
        } else if (j == 2) {
            if (grid[i][j - 1].getContentDescription().equals(getString(R.string.alien_painter_black))) {
                flip(i, j - 1, "black");
            } else {
                flip(i, j - 1, "white");
            }
        } else {
            if (grid[i][j + 1].getContentDescription().equals(getString(R.string.alien_painter_black))) {
                flip(i, j + 1, "black");
            } else {
                flip(i, j + 1, "white");
            }

            if (grid[i][j - 1].getContentDescription().equals(getString(R.string.alien_painter_black))) {
                flip(i, j - 1, "black");
            } else {
                flip(i, j - 1, "white");
            }
        }
    }
}
