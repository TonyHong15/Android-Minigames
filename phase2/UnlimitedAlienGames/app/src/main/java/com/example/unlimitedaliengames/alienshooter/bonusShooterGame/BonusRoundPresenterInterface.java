package com.example.unlimitedaliengames.alienshooter.bonusShooterGame;

public interface BonusRoundPresenterInterface {
    int getPoints();
    void increasePoint();
    void updatePoints();
    boolean checkOnHit(float bulletX, float bulletY, float ufoX, float ufoY);
    int getBullet();
    boolean canShoot();
}
