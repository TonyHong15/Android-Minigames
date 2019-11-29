package com.example.bonus;

import android.widget.ImageView;

public class Alien {
    private ImageView alien;

    public Alien(ImageView v) {
        alien = v;
    }

    float getX(){
        return alien.getX();
    }

    float getY(){
        return alien.getY();
    }

    boolean checkIfHit(float x) {
        return (alien.getX() - 100 <= x &&
                alien.getX() + 100 >= x);
    }
}
