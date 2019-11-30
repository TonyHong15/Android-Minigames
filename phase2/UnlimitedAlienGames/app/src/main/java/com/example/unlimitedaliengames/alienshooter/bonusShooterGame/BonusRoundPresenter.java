package com.example.unlimitedaliengames.alienshooter.bonusShooterGame;

public class BonusRoundPresenter implements BonusRoundPresenterInterface {

    private BonusRoundView view;
    private BonusRoundManager rules;

    BonusRoundPresenter(BonusRoundView view){
        this.view = view;
        this.rules = new BonusRoundManager(this);

    }

    boolean checkOnHit(float bulletX, float bulletY, float ufoX, float ufoY) {
        return rules.checkIfHit(bulletX, bulletY, ufoX, ufoY);
    }

    int getBullet(){
        int numLeft = rules.getNumBullets();
        rules.decreaseNumBullets();
        return numLeft;
    }

    boolean canShoot(){
        return rules.getNumBullets() > 0;
    }

    public void increasePoint() {
        rules.increasePoints();
    }

    public void updatePoints() {
        view.updatePointText(rules.getPoints());
    }
}