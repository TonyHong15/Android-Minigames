package com.example.unlimitedaliengames.alienguesser;

import android.content.res.TypedArray;

import java.lang.Math;

public class ProblemHandler {
    private GuesserView guesserView;
    private String givenProblem;
    private String correctAnswer;
    private String userAnswer;

    ProblemHandler(GuesserView view){
        guesserView = view;
    }

    void onDestroy(){
        guesserView = null;
    }

    void takeInAnswer(String answer){
        userAnswer = answer;
        processAnswer();
    }

    private void processAnswer(){
        guesserView.clearProblemView();

        if(checkAnswer()){
            guesserView.updateProblemView("correct_guess_message");
            guesserView.updateProblemView("next_problem_message");
        }else{
            guesserView.updateProblemView("wrong_guess_message");
            guesserView.updateProblemView("next_problem_message");
        }
    }

    private boolean checkAnswer(){
        if(correctAnswer != null && userAnswer != null){
            return correctAnswer.equals(userAnswer);
        }else{
            return false;
        }
    }

    public int handOutProblem(int length){
        guesserView.clearProblemView();
        return (int) (Math.random() * length) + 1;
    }
}
