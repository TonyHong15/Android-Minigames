package com.example.unlimitedaliengames.userdata;

import android.graphics.Paint;

import java.io.Serializable;

/**
 * A class containing the tracked data for this User in regards to the alien painter game
 */
class PainterData implements Serializable {

    /**
     * The Statistics to track for Painter Users
     */
    private int numMoves;
    private int timeLeft;

    /**
     * The default constructor
     */
    PainterData() {
        this.timeLeft = 0;
        this.numMoves = 0;
    }

    /**
     * The constructor with numMoves and timeLeft provided.
     * @param numMoves the number of moves the player made
     * @param timeLeft the amount of time left when the player finished the game
     */
    PainterData (int numMoves, int timeLeft) {
        this.numMoves = numMoves;
        this.timeLeft = timeLeft;
    }

    /**
     * Updates the statistics of the user in regards to the alien painter game
     * @param numMoves the number of moves the player made
     * @param timeLeft the amount of time left when the player finished the game
     */
    void updateStats(int numMoves, int timeLeft) {
        this.numMoves = numMoves;
        this.timeLeft = timeLeft;
    }
}
