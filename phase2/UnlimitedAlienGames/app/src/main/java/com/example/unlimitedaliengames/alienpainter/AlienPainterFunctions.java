package com.example.unlimitedaliengames.alienpainter;

import android.content.Context;
import android.os.Handler;
import android.widget.ImageButton;

import com.example.unlimitedaliengames.R;

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds the actual mechanics of the game, including calculating points earned by player,
 * recording number of moves made, and amount of time left.
 */
class AlienPainterFunctions {

    /**
     * The number of moves the player must make less than or equal to, in order to get
     * the bonus points
     */
    private static final int BONUSMOVES = 10;

    /**
     * The number of bonus points the player gets
     */
    private static final int BONUSPOINTS = 50000;

    /**
     * Used to record the amount of time left when the player has won
     */
    private int timeLeft;

    /**
     * Used to record the number of moves the player has made
     */
    private int numMoves;

    /**
     * Used to record the amount of points the user has earned
     */
    private int points;

    /**
     * Holds the view of the user
     */
    private AlienPainterView view;

    /**
     * Holds the grid of ImageButtons in the view
     */
    private ImageButton[][] grid;

    /**
     * Holds the strings that describe the colour of each ImageButton on the board initially
     */
    private String[][] initialBoard;

    /**
     * Used to hold the list of gridButton clicks the user has made for the purpose
     * of instant replay
     */
    private List<Integer[]> replayList = new ArrayList<>();

    /**
     * A Handler used for the purpose of instant replay
     */
    private Handler replayHandler = new Handler();

    private Context mContext;

    /**
     * Default constructor for AlienPainterFunctions
     *
     * @param view     the view
     * @param grid     the 2D array of ImageButtons
     * @param mContext the context
     */
    AlienPainterFunctions(AlienPainterView view, ImageButton[][] grid, Context mContext, String[][] initialBoard) {
        this.view = view;
        this.grid = grid;
        this.mContext = mContext;
        this.initialBoard = initialBoard;
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
                    flip(i, j, "white"); //flip the clicked one
                    flipAdjacent(i, j); //flip the ones near the clicked one
                } else if (grid[i][j].getContentDescription().equals(mContext.getString
                        (R.string.alien_painter_black_clicked))) {
                    flip(i, j, "black"); //flip the clicked one
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
        if (colour.equals(mContext.getString(R.string.alien_painter_white))) {
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

    /**
     * Resets the 2D imageButton array, numMoves and points
     */
    void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                double x = Math.random() * 2;
                if (x < 1) {
                    grid[i][j].setImageResource(R.drawable.black_circle);
                    grid[i][j].setContentDescription(mContext.getString(R.string.alien_painter_black));
                } else if (x < 2) {
                    grid[i][j].setImageResource(R.drawable.white_circle);
                    grid[i][j].setContentDescription(mContext.getString(R.string.alien_painter_white));
                }
            }
        }

        this.updateNumMoves(0);
        this.setPoints(0);
    }

    /**
     * Updates the timeLeft variable used to track the time left
     *
     * @param timeLeft the amount of time left until the end of the game
     */
    void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    /**
     * Returns the amount of time left when the game ended
     *
     * @return timeLeft the amount of time left
     */
    int getTimeLeft() {
        return this.timeLeft;
    }

    /**
     * Updates the number of moves the user has made
     */
    void updateNumMoves() {
        this.numMoves++;
    }

    /**
     * Updates the number of moves the user has made, an overloaded version with an int
     * parameter given
     *
     * @param num the number to update numMoves with
     */
    private void updateNumMoves(int num) {
        this.numMoves = num;
    }

    /**
     * Returns the number of moves the user has made
     *
     * @return the number of moves the user has made
     */
    int getNumMoves() {
        return this.numMoves;
    }

    /**
     * @return the amount of points the user has earned
     */
    int getPoints() {
        return this.points;
    }

    /**
     * Set the amount of points the user has earned
     *
     * @param points the amount of points to set this.points to
     */
    void setPoints(int points) {
        this.points = points;
    }

    /**
     * Calculates and records the amount of points the user has earned.
     * This method is called after every move the player has made
     * The points earned is the product of 50-numMoves and timeLeft.
     */
    void calculatePoints() {
        int pointsToAdd;
        pointsToAdd = timeLeft * (50 - numMoves);
        if (pointsToAdd >= 0)
            this.points += pointsToAdd;
    }

    /**
     * Checks if the user finished the game with 10 or less move. If yes,
     * give the player additional points.
     */
    void checkBonus() {
        if (numMoves < BONUSMOVES) {
            this.points += BONUSPOINTS;
        }
    }

    /**
     * Iterate through the 2D array of buttons and record the row and col values of the
     * button that the player has pressed for the purpose of instant replay.
     */
    void recordButtonPress() {
        //Create a local array of length 2 that will be sent to the replayList
        Integer[] replayArray = new Integer[2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //Find the button that was pressed
                if (grid[i][j].getContentDescription().equals(mContext.getString
                        (R.string.alien_painter_white_clicked)) ||
                        grid[i][j].getContentDescription().equals(mContext.getString
                                (R.string.alien_painter_black_clicked))) {
                    replayArray[0] = i;
                    replayArray[1] = j;
                    replayList.add(replayArray);
                    break;
                }
            }
        }
    }

    /**
     * Provides an instant replay of the game.
     * This is done by first restoring the board back to the original state.
     * Then iterating through replayList
     */
    void instantReplay() {
        //Restore the board to the initial state using initialBoard
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (initialBoard[i][j].equals(mContext.getString(R.string.alien_painter_black))) {
                    grid[i][j].setImageResource(R.drawable.black_circle);
                    grid[i][j].setContentDescription(mContext.getString(R.string.alien_painter_black));
                } else {
                    grid[i][j].setImageResource(R.drawable.white_circle);
                    grid[i][j].setContentDescription(mContext.getString(R.string.alien_painter_white));
                }
            }
        }

        //Then iterate through replayList with a delay
        replayRunnable.run();

    }

    /**
     * A runnable variable used for the purpose of instant replay
     */
    private Runnable replayRunnable = new Runnable() {
        @Override
        public void run() {

            Integer[] intPair = replayList.get(0);
            int temp1 = intPair[0];
            int temp2 = intPair[1];

            //Flip the buttons
            if (grid[temp1][temp2].getContentDescription().equals(mContext.getString
                    (R.string.alien_painter_white))) {
                System.out.println("WHITE REPLAY");
                grid[temp1][temp2].setContentDescription(mContext.getString(R.string.alien_painter_white_clicked));
            } else if (grid[temp1][temp2].getContentDescription().equals(mContext.getString
                    (R.string.alien_painter_black))) {
                System.out.println("BLACK REPLAY");
                grid[temp1][temp2].setContentDescription(mContext.getString(R.string.alien_painter_black_clicked));
            }
            flipButton();

            //Remove the first item in replayList
            replayList.remove(0);

            //Loop the Runnable if replayList is not empty
            if (!replayList.isEmpty()) {
                replayHandler.postDelayed(this, 500);
            }
        }
    };

}
