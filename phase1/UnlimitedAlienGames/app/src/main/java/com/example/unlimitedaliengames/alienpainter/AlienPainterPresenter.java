package com.example.unlimitedaliengames.alienpainter;

import android.content.Context;
import android.widget.ImageButton;

import com.example.unlimitedaliengames.R;

/**
 * This class serves as the middle man between AlienPainter and AlienPainterFunctions
 */
class AlienPainterPresenter {

    /**
     * Holds the view of the user
     */
    private AlienPainterView view;

    /**
     * Holds the timer
     */
    private AlienPainterTimer painterTimer;

    /**
     * Holds the grid of imageButtons in the view
     */
    private ImageButton[][] grid;

    /**
     * Holds the game rules
     */
    private AlienPainterFunctions buttonFunctions;

    private Context mContext;

    /**
     * Creates an AlienPainterPresenter
     * @param view The view
     * @param painterTimer  The timer displayed
     * @param grid  The 2D Array of imageButtons
     * @param buttonFunctions The object referencing the class containing the rules
     * @param mContext The Context
     */
    AlienPainterPresenter(AlienPainterView view, AlienPainterTimer painterTimer, ImageButton[][] grid, AlienPainterFunctions buttonFunctions, Context mContext) {
        this.view = view;
        this.painterTimer = painterTimer;
        this.grid = grid;
        this.mContext = mContext;
        this.buttonFunctions = buttonFunctions;

    }

    /**
     * Calls the flipButton method in buttonFunctions to find out what happens when the user clicks
     * one of the imageButtons in the 2D array
     */
    void flipButton() {
        buttonFunctions.flipButton();
    }

    /**
     * Updates the timeLeft variable used to track the time left
     * @param timeLeft the amount of time left until the end of the game
     */
    void setTimeLeft(int timeLeft) {
        buttonFunctions.setTimeLeft(timeLeft);
    }

    /**
     * Updates the number of moves the user has made
     */
    void updateNumMoves() {
        buttonFunctions.updateNumMoves();
    }

    /**
     * Returns the number of moves the user has made
     * @return the number of moves the user has made
     */
    int getNumMoves() {
        return buttonFunctions.getNumMoves();
    }
}
