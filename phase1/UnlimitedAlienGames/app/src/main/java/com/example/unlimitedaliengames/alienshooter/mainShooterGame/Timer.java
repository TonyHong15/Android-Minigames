package com.example.unlimitedaliengames.alienshooter.mainShooterGame;

import android.os.CountDownTimer;

import com.example.unlimitedaliengames.alienshooter.mainShooterGame.AlienShooterView;

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

    boolean getIsActive() {
        return isActive;
    }

    public void onFinish() {
        isActive = false;
        cancel();
        view.finishGame();
    }

    long getTimeLeft(){
        return timeLeft;
    }

}
