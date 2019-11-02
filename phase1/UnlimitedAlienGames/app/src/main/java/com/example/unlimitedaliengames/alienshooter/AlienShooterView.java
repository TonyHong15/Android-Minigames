package com.example.unlimitedaliengames.alienshooter;


interface AlienShooterView {
    void resetTimer();

    void updateTimer(String text);

    void changeAlienImage();

    void updatePoints(int points, int correct, int incorrect);
}
