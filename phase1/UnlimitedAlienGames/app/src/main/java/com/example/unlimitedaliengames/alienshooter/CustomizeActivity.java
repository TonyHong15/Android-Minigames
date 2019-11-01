package com.example.unlimitedaliengames.alienshooter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.unlimitedaliengames.R;

public class CustomizeActivity extends AppCompatActivity {

    private View fifteen, thirty, red, black, blue, yellow, proceed;
    private View time, friendly, evil;
    private boolean choseEvil, choseFriendly, choseTime;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);
    }
}
