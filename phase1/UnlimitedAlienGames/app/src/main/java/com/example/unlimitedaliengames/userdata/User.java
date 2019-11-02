package com.example.unlimitedaliengames.userdata;

import android.graphics.Paint;

import java.io.Serializable;

/*
An User object.
 */
public class User implements Serializable {
    /*
    User's information.
     */
    public String name;
    private String password;

    /*
    Stats for the guesser game.
     */
    public GuesserData guesserData;

    /**
     * Statistics of the user for alien painter game
     */
    private PainterData painterData;

    /**
     * The default constructor for User
     */
    User() {
        this.name = "";
        this.password = "";
        this.guesserData = new GuesserData();
        this.painterData = new PainterData();
    }

    /*
    Initialize a user with not game info.
     */
    User(String name, String password){
        this.name = name;
        this.password = password;
        this.guesserData = new GuesserData();
        this.painterData = new PainterData();
    }

    /*
    Set the password of the current user.
     */
    void setPassword(String pass){
        this.password = pass;
    }

    /*
    Return true if the password matches.
     */
    boolean matchPassword(String pass){
        return password.equals(pass);
    }

    /**
     * Updates the statistics of this User in regards to the alien painter game
     * @param numMoves the number of moves the player has made
     * @param timeLeft the amount of time left when the player finished the game
     */
    public void setPainterData(int numMoves, int timeLeft) {
        this.painterData.updateStats(numMoves, timeLeft);
    }

    /*
    Return the name of this user.
     */
    public String getName() {
        return name;
    }

    /*
    Return the password of the current user.
     */
    public String getPassword() {
        return password;
    }
}
