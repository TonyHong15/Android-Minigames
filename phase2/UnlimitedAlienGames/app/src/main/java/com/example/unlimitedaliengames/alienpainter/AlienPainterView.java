package com.example.unlimitedaliengames.alienpainter;

interface AlienPainterView {
    void startTimer();

    void resetTimer();

    void updateTimer(int time);

    void TimerExpired();

    void flipButton();
}
