package com.example.unlimitedaliengames;

/*
An User object.
 */
public class User {
    /*
    User's information.
     */
    private String name;
    private String password;

    /*
    Stats for the guesser game.
     */
    private int numProblem;
    private int guesserScore;
    private String currProblem;
    private String correctAns;

    /*
    Initialize a user with not game info.
     */
    User(String name, String password){
        this.name = name;
        this.password = password;
    }
}
