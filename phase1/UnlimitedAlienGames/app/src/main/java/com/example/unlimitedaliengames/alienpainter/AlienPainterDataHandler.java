package com.example.unlimitedaliengames.alienpainter;

import android.content.Context;

import com.example.unlimitedaliengames.userdata.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * A class containing methods that read and write User objects
 * UNFINISHED
 */
class AlienPainterDataHandler {

    static void readFile(User currUser) {
        try {
            FileOutputStream fos = new FileOutputStream(new File("Alien_Users.txt"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(currUser);
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
