package com.example.unlimitedaliengames.alienshooter;

import android.view.View;

import java.util.List;
import java.util.Random;

class AlienShooterManager {
    private int points;
    private View redAlien;

    AlienShooterManager() {

    }

    void randomize(List<View> aliens) {
        int random = randomNum();
        randomizeContentDescription(random, aliens);
        changeAlienImage(aliens);
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

    private void changeAlienImage(List<View> aliens) {
        String redAlien = "red alien";
        for (int i = 0; i < 9; i++) {
            if (aliens.get(i).getContentDescription().equals(redAlien)) {

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
}
