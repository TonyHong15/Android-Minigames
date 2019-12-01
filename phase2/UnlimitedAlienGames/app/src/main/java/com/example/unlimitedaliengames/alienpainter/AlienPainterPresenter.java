package com.example.unlimitedaliengames.alienpainter;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;

import com.example.unlimitedaliengames.R;
import com.example.unlimitedaliengames.userdata.User;

/**
 * This class uses AlienPainterTimer and AlienPainterFunctions to manage the actual game mechanics
 * This class also contains methods that tell AlienPainterActivity what to display to the user
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

    /**
     * Used to check whether the user has won or not
     */
    private boolean isVictorious;

    /**
     * Used to check is the game has ended
     */
    private boolean gameEnded;

    private Context mContext;

    /**
     * Creates an AlienPainterPresenter
     *
     * @param view            The view
     * @param grid            The 2D Array of imageButtons
     * @param buttonFunctions The object referencing the class containing the rules
     * @param mContext        The Context
     */
    AlienPainterPresenter(AlienPainterView view,
                          ImageButton[][] grid, AlienPainterFunctions buttonFunctions,
                          Context mContext, User currUser) {
        this.view = view;
        this.painterTimer = new AlienPainterTimer(this);
        this.grid = grid;
        this.mContext = mContext;
        this.buttonFunctions = buttonFunctions;
        this.currUser = currUser;

        startTimer();

        //Set default values for isVictorious and isEnglish and gameEnded
        gameEnded = false;
        isVictorious = false;
    }

    /**
     * The event in which any of the imageButtons are pressed.
     * This method will call the presenter to update the number of moves the player has made.
     * This method will flip the buttons according to whichever one the player has pressed.
     * This method wil check whether the player has won the game and call the playerWon
     * This method calculates points earned by player for the move they made
     * method in the presenter if so.
     *
     * @param v The view
     */
    void onGridClick(View v) {
        //Checks whether the time is still active and whether the win condition has been met
        if (painterTimer.getIsActive() && !gameEnded) {
            if (v.getContentDescription().equals("white")) {
                v.setContentDescription(mContext.getString(R.string.alien_painter_white_clicked));
            } else {
                v.setContentDescription(mContext.getString(R.string.alien_painter_black_clicked));
            }

            //Call the recordButtonPress method
            buttonFunctions.recordButtonPress();

            //Update the number of moves made by the player
            buttonFunctions.updateNumMoves();

            //Do the actual button flipping
            buttonFunctions.flipButton();

            //Calculate points earned
            buttonFunctions.calculatePoints();

            //Display the information calculated
            view.updateStats();


            if (buttonFunctions.checkWinCondition()) {

                view.showPlayerWon();

                playerWon();
            }
        }
    }



    /**
     * As the name suggests, terminates the timer
     */
    void terminateTimer() {
        painterTimer.cancel();
    }

    /**
     * Starts the timer.
     */
    private void startTimer() {
        painterTimer.setActive(true);
        painterTimer.start();
    }

    /**
     * Resets the timer of the game
     */
    void resetTimer() {
        painterTimer.reset();
    }

    /**
     * Updates the time displayed on screen.
     *
     * @param time the time to update painterTextViewTime with.
     */
    void updateTimer(int time) {
        if (!buttonFunctions.checkWinCondition()) {
            setTimeLeft(time);
            view.updateTimer(time);
        }
    }

    /**
     * Calls this when the timer is done.
     */
    void timerExpired() {
        gameEnded = true;
        view.timerExpired();
    }

    /**
     * Updates the timeLeft variable used to track the time left
     *
     * @param timeLeft the amount of time left until the end of the game
     */
    private void setTimeLeft(int timeLeft) {
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
     * Returns whether the player is victorious
     *
     * @return returns the isVictorious variable
     */
    boolean getIsVictorious() {
        return isVictorious;
    }

    /**
     * Returns whether the game has ended
     *
     * @return returns the gameEnded variable
     */
    boolean getGameEnded() {
        return gameEnded;
    }

    /**
     * Resets the game
     */
    void resetGame() {
        buttonFunctions.resetGame();
        gameEnded = false;
        isVictorious = false;
    }

    /**
     * Records the statistics of the player by first updating the data of the current User, then
     * writing the User object to a file at the program location.
     */
    private void playerWon() {
        isVictorious = true;
        gameEnded = true;
        painterTimer.setActive(false);
        painterTimer.cancel();
        buttonFunctions.checkBonus();
        view.updateStats();
        /*currUser.setPainterData(getNumMoves(), getTimeLeft());
        AlienPainterDataHandler.readFile(currUser);*/
    }

    /**
     * Calls the instantReplay function in AlienPainterFunctions to provide an instant replay of
     *  the game for the player.
     */
    void instantReplay() {
        buttonFunctions.instantReplay();
    }

}

