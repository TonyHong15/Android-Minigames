package com.example.unlimitedaliengames.alienpainter;

import android.os.CountDownTimer;

/**
 * A class that inherits CountDownTimer and has a few more attributes used to create
 * a timer for the game
 */
public class AlienPainterTimer extends CountDownTimer {
    private static final long TIMELEFT = 30000;
    private boolean isActive = true;
    private AlienPainterView view;

    /**
     * Constructs a new CountDownTimer
     * @param view The View
     */
    AlienPainterTimer(AlienPainterView view) {
        super(TIMELEFT, 1000);
        this.view = view;
    }

    public void onTick(long seconds) {
        view.updateTimer(Math.toIntExact(seconds / 1000));
    }

    /**
     * Sets whether the timer is active or not
     * @param active whether the timer is active
     */
    void setActive(boolean active) {
        isActive = active;
    }

    /**
     * Returns whether the timer is active
     * @return isActive
     */
    boolean getIsActive() {
        return isActive;
    }

    /**
     * Reset the timer
     */
    void reset () {
        view.resetTimer();
    }

    /**
     * Called when the CountDownTimer expires
     */
    public void onFinish() {
        isActive = false;
        view.TimerExpired();
        cancel();
    }
}
