package com.example.unlimitedaliengames.alienguesser;

import com.example.unlimitedaliengames.userdata.User;
import java.util.Random;

public class RandomHandler extends Handler{
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
    private static final int multiplicationMax = 20;

    /*
    Globals representing the current game state.
     */
    private String givenProblem;
    private String correctAnswer;
    private String userAnswer;
    private int problemAnswered;
    private int totalScore;


    RandomHandler(GuesserView view, User curr){
        super(view, curr);
        guesserView = view;
        currUser = curr;
    }

    /*
    Update view, display the problem saved in globals, if saved is empty, generate.
     */
    void handOutProblem(){
        generateProblem();
        guesserView.clearProblemView();
        guesserView.updateProblemView("problem_" + (problemAnswered+1) + "_text", false);
        guesserView.updateProblemView(givenProblem, true);
        guesserView.swapGameState();
    }

    void processAnswer(){
        problemAnswered += 1;
        super.processAnswer();
    }
    /*
    Generate a problem by assigning globals.
     */
    private void generateProblem() {
        int first = getRandomNumber();
        int second = getRandomNumber();
        givenProblem = first + "*" + second;
        correctAnswer = Integer.toString(first*second);
    }

    private static int getRandomNumber() {
        Random r = new Random();
        return r.nextInt(multiplicationMax+1);
    }

}
