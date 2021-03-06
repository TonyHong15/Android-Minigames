package com.example.unlimitedaliengames.userdata;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

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

    /*
    Write (save) user data onto a file
     */
    public static void writeToFile(String data,Context context)  {
        File path = context.getFilesDir();
        File file = new File(path, "savedUserData.txt");

        try {
            FileOutputStream stream = new FileOutputStream(file);
            try {
                stream.write("user data test".getBytes());
                Log.i("tag2", "writeToFile success!");
            } finally {
                stream.close();
            }
        } catch (IOException e){
            Log.e("tag1", "writeToFile failed");
        }
    }

    /*
    Read user data from a file
     */
    private String readFromFile(Context context){
        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("config.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}
