package com.example.unlimitedaliengames.alienpainter;

interface AlienPainterView {

    void updateTimer(int time);

    void timerExpired();

    void updateStats();

    void updateLanguage();

    void showPlayerWon();
}
