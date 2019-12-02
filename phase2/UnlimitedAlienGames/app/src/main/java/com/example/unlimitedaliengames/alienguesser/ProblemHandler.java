package com.example.unlimitedaliengames.alienguesser;

import com.example.unlimitedaliengames.userdata.*;

/*
The controls for guesser game.
 */
class ProblemHandler extends Handler{
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
        super(view, curr);
        guesserView = view;
        currUser = curr;
        bankSize = Size;

        //Loading user's saved game
        givenProblem = curr.guesserData.currProblem;
        correctAnswer = curr.guesserData.correctAns;
        totalScore = curr.guesserData.guesserScore;
        problemAnswered = curr.guesserData.numProblem;
    }

    /*
    Update view, display the problem saved in globals, if saved is empty, generate.
     */
    void handOutProblem(){
        if (givenProblem == null || correctAnswer == null) {
            generateProblem();
        }
        guesserView.clearProblemView();
        guesserView.updateProblemView("problem_" + (problemAnswered+1) + "_text", false);
        guesserView.updateProblemView(givenProblem, false);
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
        currUser.guesserData.currProblem = givenProblem;
        currUser.guesserData.correctAns = correctAnswer;
        currUser.guesserData.guesserScore = totalScore;
        currUser.guesserData.numProblem = problemAnswered;
        currUser.updateGamesPlayed(1);
        currUser.updateTotalPoints(totalScore);
    }

}
