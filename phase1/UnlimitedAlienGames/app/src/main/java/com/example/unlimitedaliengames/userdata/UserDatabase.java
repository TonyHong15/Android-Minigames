package com.example.unlimitedaliengames.userdata;

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

    /*
    Attempt to put a user into this database, if duplicated, change the password.
     */
    public boolean attemptRegister(String name, String pass){
        for (int i = 0; i < 15; i++){
            if (users[i] != null && users[i].name.equals(name)){
                users[i].setPassword(pass);
                return false;
            }
        }
        addUser(name, pass);
        return true;
    }

    private void addUser(String name, String pass){
        users[slot] = new User(name, pass);
        slot += 1;
    }
}
