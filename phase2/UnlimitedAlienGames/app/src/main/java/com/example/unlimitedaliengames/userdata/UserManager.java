package com.example.unlimitedaliengames.userdata;

import android.content.Context;
import android.util.JsonReader;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

//import org.json.simple.parser.ParseException;

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
    public void writeToFile(String data, Context context) throws IOException {

        //String path = context.getFilesDir().getAbsolutePath();
        File file = new File(context.getFilesDir(), "saved.json");

        try {
            FileWriter output = new FileWriter(file);

            User user1;
            user1 = new User("hehe", "123123");
            JsonObject json1 = new JsonObject();
            json1.addProperty("userId", user1.name);
            json1.addProperty("password", user1.getPassword());
            json1.addProperty("Statistics", 1);

            User user2;
            user2 = new User("haha", "123123");
            JsonObject json2 = new JsonObject();
            json2.addProperty("userId", user2.name);
            json2.addProperty("password", user2.getPassword());
            json2.addProperty("Statistics", 1);

            JsonArray userList = new JsonArray();
            userList.add(json1);
            userList.add(json2);

            output.write(userList.toString());
            Log.e("tag1", "finish write");
            output.flush();

        } catch (IOException e){
            Log.e("tag1", "writeToFile failed");
        }
    }

    public void readMessage(Context context) throws IOException {
        JsonParser parser = new JsonParser();
        File file = new File(context.getFilesDir(), "saved.json");
        try {
            FileReader reader = new FileReader(file);
            Log.e("tag2", "finish read");

            Object obj = parser.parse(reader);

            JsonArray userList = (JsonArray) obj;

            int listSize = userList.size();

            System.out.println(userList);

            for (int i = 0; i < listSize; i++) {
                System.out.println(userList.get(i));
                JsonObject item = (JsonObject) userList.get(i);
                System.out.println(item.get("userId"));
            }
//            Log.e("tag3", "finish read");



//            JsonElement name = jsonArray.get(0);
//
//            String userId = name.getAsString();
////            String password = jsonObject.get("password").getAsString();
////            String stats = jsonObject.get("Statistics").getAsString();
//
//            Log.e("Name: ", userId);
//            Log.e("Password: ", password);
//            Log.e("Statistics: ", stats);
        } catch (Exception e) {
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
