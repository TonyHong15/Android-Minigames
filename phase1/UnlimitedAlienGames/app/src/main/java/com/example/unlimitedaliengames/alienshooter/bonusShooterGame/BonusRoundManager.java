package com.example.unlimitedaliengames.alienshooter.bonusShooterGame;

public class BonusRoundManager {
    private int points;
    private float ufoX = 300;
    private float ufoY = 300;
    private BonusRoundPresenterInterface presenter;
    private int numBullets = 5;

    BonusRoundManager(BonusRoundPresenter presenter){
        this.presenter = presenter;
        points = 0;
    }

    int getPoints(){
        return points;
    }

    void increasePoints(){
        points += 1;
    }

    int getNumBullets(){
        return numBullets;
    }

    void decreaseNumBullets(){
        numBullets -= 1;
    }

    boolean checkIfHit(float x, float y){
        return (ufoX - 100 <= x &&
                ufoY + 100 >= x);
    }
}
