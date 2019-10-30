package com.example.unlimitedaliengames.alienguesser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.unlimitedaliengames.R;

public class AlienGuesser extends AppCompatActivity implements GuesserView{

    ProblemHandler handler;
    private Button submit;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alien_guesser);

        handler = new ProblemHandler();
        submit = findViewById(R.id.submitButton);
        next = findViewById(R.id.nextButton);
//        Intent intent = getIntent();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGuessResult();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestProblem();
            }
        });
    }

    @Override
    public void updateProblem(){

    }

    @Override
    public void swapGameState(){
        //Swap the visibility of submit button
        if(submit.getVisibility() == View.VISIBLE){
            submit.setVisibility(View.GONE);
        }else{
            submit.setVisibility(View.VISIBLE);
        }
        //Swap the visibility of next button
        if(next.getVisibility() == View.VISIBLE){
            next.setVisibility(View.GONE);
        }else{
            next.setVisibility(View.VISIBLE);
        }
    }

    private void getGuessResult(){
        swapGameState();
    }

    private void requestProblem(){
        swapGameState();
    }
}
