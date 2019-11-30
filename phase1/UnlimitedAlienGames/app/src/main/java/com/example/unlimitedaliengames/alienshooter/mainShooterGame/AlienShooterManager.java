package com.example.unlimitedaliengames.alienshooter.mainShooterGame;

import android.view.View;

import java.util.List;
import java.util.Random;

class AlienShooterManager {

    private int points;
    private int correct;
    private int incorrect;
    private AlienShooterPresenterInterface presenter;

    AlienShooterManager(AlienShooterPresenterInterface presenter) {
        points = 0;
        correct = 0;
        incorrect = 0;
        this.presenter = presenter;
    }

    /**
     * getter for instance variable incorrect
     * @return the variable incorrect that contains the data of how many aliens the user incorrectly
     * clicked
     */
    int getIncorrect() {
        return incorrect;
    }

    /**
     * increases the incorrect aliens clicked by one
     */
    void setIncorrect() {
        this.incorrect += 1;
    }
    /**
     * getter for instance variable correct
     * @return the variable incorrect that contains the data of how many aliens the user correctly
     * clicked
     */
    int getCorrect() {
        return correct;
    }

    /**
     * increases the aliens the user has correctly clicked by one
     */
    void setCorrect() {
        this.correct += 1;
    }

    /**
     * getter for the instance variable points
     * @return instance variable points that represents the current points the user has scored
     */
    int get_point() {
        return points;
    }

    /**
     * randomizes the evil alien location in the list
     * @param aliens list of aliens in this game
     */
    void randomize(List<View> aliens) {
        int random = randomNum();
        randomizeContentDescription(random, aliens);
        presenter.changeAlienImage();
    }

    /**
     * changes the alien image button content description based the type of alien each button is
     * randomly assigned to
     * @param random integer that represents randomly generated location of evil alien in the list
     * @param aliens the list containing all the alien image buttons
     */
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

    /**
     * generates a random number between 0 and 8
     * @return randomly generated number
     */
    private int randomNum() {
        Random random = new Random();
        return random.nextInt(9);
    }

    /**
     * checks if a specific alien image button in aliens is an evil or good alien
     * @param v View that represents the alien image button
     * @return boolean that is true when the alien is evil and false when the alien is good
     */
    boolean checkAlien(View v) {
        return v.getContentDescription().equals("red alien");
    }

    /**
     * adds points to the user's score
     * @param p the number of points to be added to the user's score
     */
    void setPoints(int p) {
        points += p;
    }
}
