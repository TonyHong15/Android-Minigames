package com.example.unlimitedaliengames.alienpainter;

import android.content.Context;
import android.widget.ImageButton;

import com.example.unlimitedaliengames.userdata.User;

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
     * Returns the amount of time left when the player finished
     *
     * @return the amount of time left, in seconds, integer.
     */
    int getTimeLeft() {
        return buttonFunctions.getTimeLeft();
    }

    /**
     * Set the amount of points the user has earned
     * @param points the amount of points to set to
     */
    void setPoints(int points) {
        buttonFunctions.setPoints(points);
    }

    /**
     * Calls the getPoints method in AlienPainterFunctions to set
     *  the amount of points the user has earned
     *
     * @return the amount of points the user has earned
     */
    int getPoints() {
        return buttonFunctions.getPoints();
    }

    /**
     * Calls the calculatePoints function in AlienPainterFunctions to calculate and update
     *  the points the player has earned
     */
    void calculatePoints() {
        buttonFunctions.calculatePoints();
    }

    /**
     * Resets the board
     */
    void resetGame() {
        buttonFunctions.resetGame();
    }

    /**
     * Records the statistics of the player by first updating the data of the current User, then
     * writing the User object to a file at the program location.
     */
    void playerWon() {
        painterTimer.cancel();
        currUser.setPainterData(getNumMoves(), getTimeLeft());
        AlienPainterDataHandler.readFile(currUser);
    }

}

