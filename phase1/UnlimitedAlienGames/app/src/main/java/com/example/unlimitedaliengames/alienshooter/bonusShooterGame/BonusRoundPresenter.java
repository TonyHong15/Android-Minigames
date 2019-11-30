package com.example.unlimitedaliengames.alienshooter.bonusShooterGame;

public class BonusRoundPresenter implements BonusRoundPresenterInterface {

    private BonunsRoundView view;
    private BonusRoundManager rules;

    BonusRoundPresenter(BonunsRoundView view){
        this.view = view;
        this.rules = new BonusRoundManager(this);

    }

    boolean checkOnHit(float x, float y) {
        return rules.checkIfHit(x, y);
    }

    int getBullet(){
        int numLeft = rules.getNumBullets();
        rules.decreaseNumBullets();
        return numLeft;
    }

    boolean canShoot(){
        return rules.getNumBullets() > 0;
    }

}