package com.example.unlimitedaliengames.userdata;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        //String path = context.getFilesDir().getAbsolutePath();
        File file = new File(context.getFilesDir(),"/savedUserData.json");

        try {
            FileWriter output = new FileWriter(file);

            User user1 = new User("toshi", "123123");
            JSONObject json1 = new JSONObject();
            json1.put("username", user1.name);

            JSONArray userList = new JSONArray();
            userList.put(json1);

            output.write(userList.toString());
            output.flush();

        } catch (IOException e){
            Log.e("tag1", "writeToFile failed");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /*
    Read user data from a file
     */
    /*public static void readFromFile(Context context){
        File file = new File(context.getFilesDir(),"/savedUserData.json");

        JSONParser parser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(file));

            ArrayList<Object> a = new ArrayList<Object>();
            if (jsonArray != null)
                for (int i = 0; i < jsonArray.length(); i++)
                    a.add(jsonArray.get(i));

            for(int i = 0; i < a.size(); i++) {
                User user = (User) a.get(i);

                String name = user.name;
                Log.i("tag1", "name = "+name);
            }
        } catch(FileNotFoundException fe) {
            fe.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }*/
}
