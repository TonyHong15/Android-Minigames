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

    boolean checkIfHit(float bulletX, float bulletY, float ufoX, float ufoY){
        return (bulletX - 200 < ufoX && ufoX < bulletX + 200 &&
                bulletY - 200 < ufoY && ufoY < bulletY + 200);
    }
}
