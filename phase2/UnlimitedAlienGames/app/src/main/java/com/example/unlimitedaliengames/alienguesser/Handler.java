package com.example.unlimitedaliengames.alienguesser;

import com.example.unlimitedaliengames.userdata.User;

class Handler {
    /*
    The interface.
    */
    private GuesserView guesserView;
    /*
    The current user
    */
    private User currUser;
    /*
    Game logic variables.
    */
    private static final int correctScore = 2;
    private static final int incorrectScore = -1;
    private int bankSize;

    /*
    Globals representing the current game state.
    */
    private String givenProblem;
    private String correctAnswer;
    private String userAnswer;
    private int problemAnswered;
    private int totalScore;

    Handler(GuesserView view, User curr){
        currUser = curr;
        guesserView = view;
    }

    void onDestroy(){
        guesserView = null;
    }


    /*
    Get the answer from the View.
    */
    void takeInAnswer(String answer){
        userAnswer = answer;
        processAnswer();
    }

    /*
    Process answer and display message.
     */
    void processAnswer(){
        problemAnswered += 1;
        guesserView.clearProblemView();

        if(checkAnswer()){
            guesserView.updateProblemView("correct_guess_message", false);
            totalScore += correctScore;
        }else{
            guesserView.updateProblemView("wrong_guess_message", false);
            totalScore += incorrectScore;
        }

        guesserView.updateScoreView("Score: " + totalScore);

        if(problemAnswered < 10){
            guesserView.updateProblemView("next_problem_message", false);
            guesserView.swapGameState();
        }else{
            guesserView.updateProblemView("game_finish_message", false);
            problemAnswered = 0;
            guesserView.finishGuess();
        }
        //So that the user will be getting different problem when saved after submit.
        givenProblem = null;
        correctAnswer = null;
    }

    /*
    Check if the answer is correct.
     */
    private boolean checkAnswer(){
        if(correctAnswer != null && userAnswer != null){
            return correctAnswer.equals(userAnswer);
        }else{
            return false;
        }
    }

    void handOutProblem(){}

    /*
    Save the current game state.
    */
    void saveGame(){    }

    /*
    Return the current user using this object.
     */
    User getCurrUser(){
        return currUser;
    }
}
