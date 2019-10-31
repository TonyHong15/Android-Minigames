package com.example.unlimitedaliengames.alienshooter;

import android.os.CountDownTimer;
import android.widget.TextView;


class Timer extends CountDownTimer {
    private static final long TIMELEFT = 30000;
    private boolean isActive = false;
    private AlienShooterView view;

    Timer(TextView timerText, AlienShooterView view) {
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
        cancel();
        view.resetTimer();
    }
}
