package com.example.unlimitedaliengames.userdata;

/*
User database class, stores an array of users.
 */
public class UserManager {

    /*
    The array of users.
     */
    private User[] users;
    /*
    Current slot of next new user.
     */
    private int slot;
    /*
    Users to extract, always after matching password.
     */
    private int toExtract;

    public UserManager(){
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

    /*
    Add the user to the database.
     */
    private void addUser(String name, String pass){
        users[slot] = new User(name, pass);
        slot += 1;
    }

    /*
    Return true if there is a user with given name and password.
     */
    public boolean validateCredentials(String name, String pass){
        for (int i = 0; i < 15; i++){
            if (users[i] != null && users[i].name.equals(name)){
                if(users[i].matchPassword(pass)){
                    toExtract = i;
                    return true;
                }
            }
        }
        return false;
    }

    /*
    Extract the user found by validateCredentials.
     */
    public User extractUser(){
        return users[toExtract];
    }
}
