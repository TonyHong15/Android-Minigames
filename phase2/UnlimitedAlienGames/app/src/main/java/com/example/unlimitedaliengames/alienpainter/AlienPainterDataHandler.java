package com.example.unlimitedaliengames.alienpainter;

import android.content.Context;

import com.example.unlimitedaliengames.userdata.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * A class containing methods that handle recording of statistics for the current user
 */
class AlienPainterDataHandler {

    /**
     * records the statistics of currUser
     */
    void recordStats(int gamesPlayed, int totalTime, int points, User currUser) {
        currUser.updateGamesPlayed(gamesPlayed);
        currUser.updateTimePlayed(totalTime);
        currUser.updateTotalPoints(points);
    }
}
