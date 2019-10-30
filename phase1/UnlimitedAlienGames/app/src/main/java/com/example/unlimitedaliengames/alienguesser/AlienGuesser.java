package com.example.unlimitedaliengames.alienguesser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.unlimitedaliengames.R;

public class AlienGuesser extends AppCompatActivity implements GuesserView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alien_guesser);

        Intent intent = getIntent();  //Leaving this here in case we need to send something through the intent in the future
    }

    @Override
    public void handOutProblem(){

    }
}
