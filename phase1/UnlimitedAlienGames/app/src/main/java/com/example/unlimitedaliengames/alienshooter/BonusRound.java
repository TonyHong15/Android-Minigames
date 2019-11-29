package com.example.unlimitedaliengames.alienshooter;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.text.style.BulletSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.unlimitedaliengames.R;

import java.util.ArrayList;
import java.util.List;

public class BonusRound extends AppCompatActivity implements BonunsRoundView{

    private ImageView bullet1, canon, ufo, bulletCount;
    private ImageView bullet2, bullet3, bullet4, bullet5;
    private Button shoot;
    private BonusRoundPresenter presenter;
    private List<ImageView> bullets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus_round);

        bullets = new ArrayList<>();
        bullets.add(bullet1);

        //add 5 bullets
        bullet1 = findViewById(R.id.Bullet1);
        bullet2 = findViewById(R.id.Bullet2);
        bullet3 = findViewById(R.id.Bullet3);
        bullet4 = findViewById(R.id.Bullet4);
        bullet5 = findViewById(R.id.Bullet5);

        bullets.add(bullet1);
        bullets.add(bullet2);
        bullets.add(bullet3);
        bullets.add(bullet4);
        bullets.add(bullet5);

//        setInvisibility();

        canon = findViewById(R.id.Canon);

        ufo = findViewById(R.id.Ufo);
        presenter = new BonusRoundPresenter(this);

        shoot = findViewById(R.id.Shoot);
        ShootListener();

        canonMotion();
//        ufoMotion();

    }

    private void setInvisibility() {
        for (View bullet: bullets) {
            bullet.setVisibility(View.INVISIBLE);
        }
    }

    private void ShootListener() {
        shoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presenter.canShoot()){
                    shoot(presenter.getBullet());
                }
                else{
                    String text = "Out of Ammo";
                    shoot.setText(text);
                }

            }
        });
    }

    void shoot(int bulletLeft){
        float xValue = canon.getX();
        float yValue = canon.getY();
        bullets.get(bulletLeft).setVisibility(View.VISIBLE);
        shootingMotion(bullets.get(bulletLeft), xValue, yValue);

    }

    private void canonMotion(){
        leftToRightAnimation(canon, 5000);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    canonMotion();
                }
            }, 10000);
    }

    private void leftToRightAnimation(View v, int t){
        ObjectAnimator canonMoveLeft = ObjectAnimator.ofFloat(v, "translationX", 0f);
        canonMoveLeft.setDuration(t);
        ObjectAnimator canonMoveRight = ObjectAnimator.ofFloat(v, "translationX", 870f);
        canonMoveRight.setDuration(t);
        AnimatorSet moveCanon = new AnimatorSet();
        moveCanon.play(canonMoveRight).before(canonMoveLeft);
        moveCanon.start();
    }

    private void shootingMotion(View v, float x, float y){
        setBulletLocation(v, x, y);
        ObjectAnimator animation = ObjectAnimator.ofFloat(v, "translationY", -1500f);
        animation.setDuration(3000);
        animation.start();
        checkOnHit(x, y);
    }

    private void setBulletLocation(View v, float x, float y){
        v.setX(x);
        v.setY(y);
    }

    private void checkOnHit(float x, float y) {
//        if (presenter.checkOnHit(x, y)) {
//            String hit = "hit";
//            shoot.setText(hit);
//        }
//        else{
//            String hit = "fail";
//            shoot.setText(hit);
//        }
    }
}


