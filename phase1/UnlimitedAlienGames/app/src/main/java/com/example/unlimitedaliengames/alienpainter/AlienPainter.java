package com.example.unlimitedaliengames.alienpainter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.unlimitedaliengames.R;

public class AlienPainter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alien_painter);

        Intent intent = getIntent();  //Leaving this here in case we need to send something through the intent in the future
    }
}
