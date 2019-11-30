package com.example.unlimitedaliengames.alienshooter.bonusShooterGame;

import android.os.Handler;

public class BonusRoundPresenter implements BonusRoundPresenterInterface {

    private BonusRoundView view;
    private BonusRoundManager rules;

    BonusRoundPresenter(BonusRoundView view) {
        this.view = view;
        this.rules = new BonusRoundManager(this);

    }

    public boolean checkOnHit(float bulletX, float bulletY, float ufoX, float ufoY) {
        return rules.checkIfHit(bulletX, bulletY, ufoX, ufoY);
    }

    public int getBullet() {
        int numLeft = rules.getNumBullets();
        rules.decreaseNumBullets();
        return numLeft;
    }


    void checkLastBullet() {
        if (!canShoot()) {
            endBonusRound();
        }
    }

    void endBonusRound() {
        String text = "Out of Ammo";
        view.setShoot(text);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.endingDescription();
                view.setExitButton();
            }
        }, 1500);
    }

    public boolean canShoot() {
        return rules.getNumBullets() > 0;
    }

    public void increasePoint() {
        rules.increasePoints();
    }

    public void updatePoints() {
        view.updatePointText(rules.getPoints());
    }

    public int getPoints() {
        return rules.getPoints();
    }
}