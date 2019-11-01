package com.example.unlimitedaliengames.userdata;

/*
An User object.
 */
public class User {
    /*
    User's information.
     */
    public String name;
    private String password;

    /*
    Stats for the guesser game.
     */
    public GuesserData guesserData;

    /*
    Initialize a user with not game info.
     */
    public User(String name, String password){
        this.name = name;
        this.password = password;
    }

    void setPassword(String pass){
        this.password = pass;
    }

    boolean matchPassword(String pass){
        return password.equals(pass);
    }
}