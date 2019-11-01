package com.example.unlimitedaliengames.alienguesser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.unlimitedaliengames.LoginActivity;
import com.example.unlimitedaliengames.MainActivity;
import com.example.unlimitedaliengames.R;
import com.example.unlimitedaliengames.userdata.*;

public class AlienGuesser extends AppCompatActivity implements GuesserView{
    /*
    The name of the package.
     */
    String pack;
    /*
    Problem handler for the guesser.
     */
    ProblemHandler handler;
    /*
    Components in the layout.
     */
    private TextView problem;
    private EditText answer;
    private Button submit;
    private Button next;
    private Button ret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alien_guesser);
        Intent intent = getIntent();

        TypedArray problems = getResources().obtainTypedArray(R.array.problem_bank);

        handler = new ProblemHandler(this,
                (User)intent.getParcelableExtra(LoginActivity.PASS_USER),
                problems.length());

        problems.recycle();
        setUpInterface();
    }

    /*
    Set up the globals and listeners.
     */
    private void setUpInterface() {
        pack = getPackageName();
        submit = findViewById(R.id.submitButton);
        next = findViewById(R.id.nextButton);
        ret = findViewById(R.id.returnButton);
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

        //handling clicking event for return button.
        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToMain();
            }
        });

        //handling clicking event for take break button.
        findViewById(R.id.takeBreak).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestSave();
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
        String toView = problem.getText() + "\n\n" + message + "\n";
        problem.setText(toView);
    }

    @Override
    public void finishGuess(){
        ret.setVisibility(View.VISIBLE);
    }

    /*
    Send the user guess to the problem handler.
     */
    private void submitGuessResult(){
        handler.takeInAnswer(answer.getText().toString());
    }

    /*
    Request from problem handler a random problem from the problem bank.
     */
    private void requestProblem(){
        handler.handOutProblem();
    }

    /*
    Tell the handler to save current game state.
     */
    private void requestSave(){
        handler.saveGame();
    }
    /*
    Return the the main menu.
     */
    private void returnToMain(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public String fetchFromRes(String name){
        int id = getResources().getIdentifier(name, "string", pack);
        return getString(id);
    }
}
