package com.example.unlimitedaliengames.alienshooter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

import com.example.unlimitedaliengames.R;

import java.util.List;

public class AlienShooterView extends AppCompatActivity {

    private List<ImageButton> aliens;
    private AlienShooterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alien_shooter);


    }
    void changeAlien(){

    }

}
