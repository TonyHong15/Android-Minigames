package com.example.unlimitedaliengames.alienshooter;

import android.os.CountDownTimer;

class Timer extends CountDownTimer {
    private boolean isActive = false;
    private AlienShooterView view;
    private long timeLeft;

    Timer(AlienShooterView view, long time) {
        super(time, 1000);
        this.view = view;
    }

    public void onTick(long seconds) {
        String text = "Time: " + seconds / 1000;
        timeLeft = seconds;
        view.updateTimer(text);
    }

    void setActive() {
        isActive = true;
    }

    void setInactive() {
        isActive = false;
    }

    boolean getIsActive() {
        return isActive;
    }

    public void onFinish() {
        isActive = false;
        cancel();
        view.finishGame();
    }

    public long getTimeLeft(){
        return timeLeft;
    }

}
