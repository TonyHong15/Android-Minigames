package com.example.unlimitedaliengames.alienshooter;

import android.view.View;

import com.example.unlimitedaliengames.R;

import java.util.List;
import java.util.Random;

class AlienShooterManager {

    private int points;
    private AlienShooterPresenterInterface presenter;

    AlienShooterManager(AlienShooterPresenterInterface presenter) {
        points = 0;
        this.presenter = presenter;
    }

    int get_point() {
        return points;
    }

    void randomize(List<View> aliens) {
        int random = randomNum();
        randomizeContentDescription(random, aliens);
        presenter.changeAlienImage();
    }

    private void randomizeContentDescription(int random, List<View> aliens) {
        for (int i = 0; i < 9; i++) {
            if (random == i) {
                String text = "red alien";
                aliens.get(i).setContentDescription(text);
            } else {
                String text = "normal alien";
                aliens.get(i).setContentDescription(text);
            }
        }
    }

    private int randomNum() {
        Random random = new Random();
        return random.nextInt(9);
    }

    boolean checkAlien(View v) {
        return v.getContentDescription().equals("red alien");
    }

    void setPoints(int p) {
        points += p;
    }
}
