package com.example.unlimitedaliengames.alienguesser;

import com.example.unlimitedaliengames.userdata.*;

import java.io.Serializable;

/*
The controls for guesser game.
 */
class ProblemHandler {
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


    ProblemHandler(GuesserView view, User curr, int Size){
        bankSize = Size;
        currUser = curr;
        guesserView = view;

        //Loading user's saved game
        givenProblem = curr.guesserData.currProblem;
        correctAnswer = curr.guesserData.correctAns;
        totalScore = curr.guesserData.guesserScore;
        problemAnswered = curr.guesserData.numProblem;
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
    private void processAnswer(){
        problemAnswered += 1;
        guesserView.clearProblemView();

        if(checkAnswer()){
            guesserView.updateProblemView("correct_guess_message");
            totalScore += correctScore;
        }else{
            guesserView.updateProblemView("wrong_guess_message");
            totalScore += incorrectScore;
        }

        guesserView.updateScoreView("Score: " + totalScore);

        if(problemAnswered < 10){
            guesserView.updateProblemView("next_problem_message");
            guesserView.swapGameState();
        }else{
            guesserView.updateProblemView("game_finish_message");
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

    /*
    Update view, display the problem based on rng, and save info about the problem.
     */
    void handOutProblem(){
        if (givenProblem == null || correctAnswer == null) {
            generateProblem();
        }
        guesserView.clearProblemView();
        guesserView.updateProblemView("problem_" + (problemAnswered+1) + "_text");
        guesserView.updateProblemView(givenProblem);
        guesserView.swapGameState();
    }

    private void generateProblem() {
        int i = (int) (Math.random() * bankSize) + 1;
        String name = "problem_" + i;
        String answer_name = name + "_ans";

        givenProblem = name;
        correctAnswer = guesserView.fetchFromRes(answer_name);
    }

    /*
    Save the current game state.
     */
    void saveGame(){
        GuesserData d = new GuesserData(problemAnswered, totalScore, givenProblem, correctAnswer);
        currUser.saveGuesser(d);
    }

    User getCurrUser(){
        return currUser;
    }
}
