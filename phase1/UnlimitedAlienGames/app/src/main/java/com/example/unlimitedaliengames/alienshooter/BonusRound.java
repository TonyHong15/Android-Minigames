package com.example.unlimitedaliengames.alienshooter;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unlimitedaliengames.R;

import java.util.ArrayList;
import java.util.List;

public class BonusRound extends AppCompatActivity implements BonunsRoundView{

    private TextView text;
    private ImageView bullet1, canon, ufo;
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

        canon = findViewById(R.id.Canon);

        text = findViewById(R.id.Points);

        ufo = findViewById(R.id.Ufo);
        presenter = new BonusRoundPresenter(this);

        shoot = findViewById(R.id.Shoot);
        ShootListener();

        canonMotion();
        ufoMotion();

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

    private void ufoMotion(){
        leftToRightAnimation(ufo, 500);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ufoMotion();
            }
        }, 1000);
    }

    private void canonMotion(){
        leftToRightAnimation(canon, 1000);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    canonMotion();
                }
            }, 2000);
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

    private void shootingMotion(final View v, final float x, final float y){
        setBulletLocation(v, x, y);
        ObjectAnimator animation = ObjectAnimator.ofFloat(v, "translationY", -300f);
        animation.setDuration(2000);
        animation.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkOnHit(v);
            }
        }, 1300);
    }

    private void setBulletLocation(View v, float x, float y){
        v.setX(x);
        v.setY(y);
    }

    private void checkOnHit(View v) {
        float bulletX = v.getX();
        float bulletY = v.getY();
        float ufoX = ufo.getX();
        float ufoY = ufo.getY();

        if (presenter.checkOnHit(bulletX, bulletY, ufoX, ufoY)) {
            presenter.increasePoint();
            presenter.updatePoints();
            v.setVisibility(View.INVISIBLE);
        }
    }

    public void updatePointText(int point){
        String textPoint = "points: " + point;
        text.setText(textPoint);
    }
}


