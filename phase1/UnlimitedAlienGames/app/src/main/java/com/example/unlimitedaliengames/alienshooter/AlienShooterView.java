package com.example.unlimitedaliengames.alienshooter;


interface AlienShooterView {

    void updateTimer(String text);

    void changeAlienImage();

    void updatePoints(int points, int correct, int incorrect);

    void finishGame();
}
