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

    void clickedAlien(List<View> aliens, View v) {
        if (rules.checkAlien(v))
            rules.changePoint(1);
        else
            rules.changePoint(-1);
        rules.randomize(aliens);
        view.updatePoints(rules.get_point());
    }

    void randomizeAliens(List<View> aliens) {
        rules.randomize(aliens);
    }


}
