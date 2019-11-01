package com.example.unlimitedaliengames.alienshooter;

import android.view.View;


import java.util.List;

class AlienShooterPresenter {
    private AlienShooterView view;
    private AlienShooterManager rules;
    private Timer timer;


    AlienShooterPresenter(AlienShooterView view, AlienShooterManager rules, Timer timer) {
        this.view = view;
        this.rules = rules;
        this.timer = timer;
    }

    void clickedAlien(View v, List<View> aliens) {
        view.updateAliens(v);

    }

    void randomizeAliens(List<View> aliens) {
        rules.randomize(aliens);
    }


}
