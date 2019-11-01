package com.example.unlimitedaliengames.alienpainter;

import android.os.CountDownTimer;

public class AlienPainterTimer extends CountDownTimer {
    private static final long TIMELEFT = 30000;
    private boolean isActive = true;
    private AlienPainterView view;

    AlienPainterTimer(AlienPainterView view) {
        super(TIMELEFT, 1000);
        this.view = view;
    }

    public void onTick(long seconds) {
        view.updateTimer(Math.toIntExact(seconds / 1000));
    }

    void setActive(boolean active) {
        isActive = active;
    }

    boolean getIsActive() {
        return isActive;
    }

    void reset () {
        view.resetTimer();
    }

    public void onFinish() {
        isActive = false;
        view.TimerExpired();
        cancel();
    }
}
