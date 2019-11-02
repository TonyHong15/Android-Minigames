package com.example.unlimitedaliengames.userdata;

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

    /*
    Initialize a user with not game info.
     */
    User(String name, String password){
        this.name = name;
        this.password = password;
        this.guesserData = new GuesserData(0, 0, null, null);
    }

    void setPassword(String pass){
        this.password = pass;
    }

    boolean matchPassword(String pass){
        return password.equals(pass);
    }

    public void saveGuesser(GuesserData data){
        this.guesserData = data;
    }

    public void eraseGuesser(){
        this.guesserData = null;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
