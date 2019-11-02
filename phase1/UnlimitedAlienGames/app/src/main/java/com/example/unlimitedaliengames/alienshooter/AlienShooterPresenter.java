package com.example.unlimitedaliengames.alienshooter;

import android.view.View;


import java.util.List;

class AlienShooterPresenter implements AlienShooterPresenterInterface{
    private AlienShooterView view;
    private AlienShooterManager rules;


    AlienShooterPresenter(AlienShooterView view) {
        this.view = view;
        this.rules = new AlienShooterManager(this);
    }

    void clickedAlien(List<View> aliens, View v) {
        if (rules.checkAlien(v)) {
            rules.setPoints(1);
            rules.setCorrect();
        }
        else {
            rules.setPoints(-2);
            rules.setIncorrect();
        }
        rules.randomize(aliens);
        view.updatePoints(getPoints(), getCorrect(), getIncorrect());
    }

    int getPoints(){
        return rules.get_point();
    }
    int getCorrect(){
        return rules.getCorrect();
    }
    int getIncorrect(){
        return rules.getIncorrect();
    }

    void destroy() {
        view = null;
    }

    public void changeAlienImage(){
        view.changeAlienImage();
    }
    void randomizeAliens(List<View> aliens) {
        rules.randomize(aliens);
    }


}
