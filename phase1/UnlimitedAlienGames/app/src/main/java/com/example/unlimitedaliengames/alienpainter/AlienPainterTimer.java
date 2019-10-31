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
        String text = "Time: " + seconds / 1000;
        view.updateTimer(text);
    }

    void setActive(boolean active) {
        isActive = active;
    }

    boolean getIsActive() {
        return isActive;
    }

    public void onFinish() {
        isActive = false;
        view.resetTimer();
        view.TimerExpired();
        cancel();
    }
}
