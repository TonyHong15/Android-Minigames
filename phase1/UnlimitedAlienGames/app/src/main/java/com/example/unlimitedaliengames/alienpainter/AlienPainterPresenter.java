package com.example.unlimitedaliengames.alienpainter;

import android.content.Context;
import android.widget.ImageButton;

import com.example.unlimitedaliengames.userdata.User;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class serves as the middle man between AlienPainter and AlienPainterFunctions
 */
class AlienPainterPresenter {

    /**
     * The Constant that holds the String name of the user data xml file
     */
    private static final String DATA_FILE_NAME = "painter_user_data";

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

    /**
     * Holds the information of the user
     */
    private User currUser;

    private Context mContext;

    /**
     * Creates an AlienPainterPresenter
     *
     * @param view            The view
     * @param painterTimer    The timer displayed
     * @param grid            The 2D Array of imageButtons
     * @param buttonFunctions The object referencing the class containing the rules
     * @param mContext        The Context
     */
    AlienPainterPresenter(AlienPainterView view, AlienPainterTimer painterTimer,
                          ImageButton[][] grid, AlienPainterFunctions buttonFunctions,
                          Context mContext, User currUser) {
        this.view = view;
        this.painterTimer = painterTimer;
        this.grid = grid;
        this.mContext = mContext;
        this.buttonFunctions = buttonFunctions;
        this.currUser = currUser;
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
     *
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
     *
     * @return the number of moves the user has made
     */
    int getNumMoves() {
        return buttonFunctions.getNumMoves();
    }

    /**
     * Resets the board
     */
    void resetBoard() {
        buttonFunctions.resetGame();
    }

    /**
     * Records the statistics of the player
     */
    void playerWon() {
        //writeXML();
    }

}
