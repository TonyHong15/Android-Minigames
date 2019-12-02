package com.example.unlimitedaliengames.userdata;

import android.content.Context;

interface UserManagerInterface {

    void writeToFile(Context context);

    void readFromFile(Context context);
}
