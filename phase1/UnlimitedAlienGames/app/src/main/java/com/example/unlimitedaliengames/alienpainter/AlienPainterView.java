package com.example.unlimitedaliengames.alienpainter;

interface AlienPainterView {
    void startTimer();

    void resetTimer();

    void updateTimer(String text);

    void TimerExpired();
}
