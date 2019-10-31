package com.example.unlimitedaliengames.alienguesser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.unlimitedaliengames.R;

public class AlienGuesser extends AppCompatActivity implements GuesserView{

    String pack;
    ProblemHandler handler;
    private TextView problem;
    private EditText answer;
    private Button submit;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alien_guesser);
        Intent intent = getIntent();

        pack = getPackageName();
        handler = new ProblemHandler(this);
        submit = findViewById(R.id.submitButton);
        next = findViewById(R.id.nextButton);
        problem = findViewById(R.id.problemView);
        answer = findViewById(R.id.answerText);

        //Handling clicking event for submit button.
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitGuessResult();
            }
        });

        //handling clicking event for next button.
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestProblem();
            }
        });
    }

    @Override
    protected void onDestroy(){
        handler.onDestroy();
        super.onDestroy();
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

    @Override
    public void updateProblemView(String message){
        int id = getResources().getIdentifier(message, "string", pack);
        String toView = problem.getText() + "\n" + getString(id);
        problem.setText(toView);
    }

    @Override
    public void clearProblemView(){
        problem.setText("");
    }

    @Override
    public void updateScoreView(String message){

    }

    private void submitGuessResult(){
        //For testing purpose, to be changed.
        handler.takeInAnswer(answer.getText().toString());
        swapGameState();
    }

    private void requestProblem(){
        //For testing purpose, to be changed.
        handler.handOutProblem();
        swapGameState();
    }
}
