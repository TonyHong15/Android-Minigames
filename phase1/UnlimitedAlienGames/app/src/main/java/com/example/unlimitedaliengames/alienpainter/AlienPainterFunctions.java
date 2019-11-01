package com.example.unlimitedaliengames.alienpainter;

import android.content.Context;
import android.widget.ImageButton;

import com.example.unlimitedaliengames.R;

/**
 * This class holds the actual mechanics of the game
 */
class AlienPainterFunctions {

    /**
     * Holds the view of the user
     */
    private AlienPainterView view;

    /**
     * Holds the grid of imageButtons in the view
     */
    private ImageButton[][] grid;

    private Context mContext;

    AlienPainterFunctions(AlienPainterView view, ImageButton[][] grid, Context mContext) {
        this.view = view;
        this.grid = grid;
        this.mContext = mContext;
    }

    /**
     * Flips the imageButtons around the imageButton the user clicked,
     * including the one the user clicked.
     */
     void flipButton() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j].getContentDescription().equals(mContext.getString
                        (R.string.alien_painter_white_clicked))) {
                    flip(i, j, "black"); //flip the clicked one
                    flipAdjacent(i, j); //flip the ones near the clicked one
                } else if (grid[i][j].getContentDescription().equals(mContext.getString
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
            grid[i][j].setContentDescription(mContext.getString(R.string.alien_painter_black));
        } else {
            grid[i][j].setImageResource(R.drawable.white_circle);
            grid[i][j].setContentDescription(mContext.getString(R.string.alien_painter_white));
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
}
