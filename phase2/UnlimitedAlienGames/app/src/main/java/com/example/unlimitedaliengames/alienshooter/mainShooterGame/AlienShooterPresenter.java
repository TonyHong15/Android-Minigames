package com.example.unlimitedaliengames.alienshooter.mainShooterGame;

import android.content.Intent;
import android.view.View;

import java.util.List;

class AlienShooterPresenter implements AlienShooterPresenterInterface {
    private AlienShooterView view;
    private AlienShooterManager rules;


    AlienShooterPresenter(AlienShooterView view) {
        this.view = view;
        this.rules = new AlienShooterManager(this);
    }

    /**
     * determines if the user clicked a correct alien or not and changes user statistics accordingly
     * @param aliens the list of image buttons that correspond to aliens
     * @param v the alien image button that was clicked
     */
    void clickedAlien(List<View> aliens, View v) {
        if (rules.checkAlien(v)) {
            rules.setPoints(1);
            rules.setCorrect();
        } else {
            rules.setPoints(-2);
            rules.setIncorrect();
        }
        rules.randomize(aliens);
        view.updatePoints(getPoints(), getCorrect(), getIncorrect());
    }

    /**
     * getter for the points user has scored
     * @return the current points the user has scored
     */
    int getPoints() {
        return rules.get_point();
    }

    /**
     * getter for the number of evil aliens user has shot
     * @return the current number of evil aliens shot
     */
    int getCorrect() {
        return rules.getCorrect();
    }

    /**
     * getter for the number of good aliens user has shot
     * @return the current number of good aliens shot
     */
    int getIncorrect() {
        return rules.getIncorrect();
    }

    /**
     * called when activity/game is closed
     */
    void destroy() {
        view = null;
    }



    void addBonusPoints(int bonus){
        rules.setPoints(bonus);
    }
    /**
     * change all alien image buttons to appropriate alien representation
     */
    public void changeAlienImage() {
        view.changeAlienImage();
    }

    /**
     * Randomizes the location of the evil alien
     * @param aliens a list containing image buttons that represents all the aliens in this game
     */
    void randomizeAliens(List<View> aliens) {
        rules.randomize(aliens);
    }


}
