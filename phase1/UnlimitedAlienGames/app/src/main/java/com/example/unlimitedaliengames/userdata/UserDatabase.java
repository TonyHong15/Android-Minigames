package com.example.unlimitedaliengames.userdata;

import com.example.unlimitedaliengames.userdata.User;

/*
User database class, stores an array of users.
 */
public class UserDatabase {

    /*
    The array of users.
     */
    private User[] users;
    private int slot;

    public UserDatabase(){
        users = new User[16];
        slot = 0;
    }

    public void attemptRegister(String name, String pass){
        for (int i = 0; i < 15; i++){
            if ((users[i]).name.equals(name)){
                users[i].setPassword(pass);
            }else{
                addUser(name, pass);
            }
        }
    }

    private void addUser(String name, String pass){
        users[slot] = new User(name, pass);
    }
}
