package com.example.unlimitedaliengames.alienguesser;

/*
The controls for guesser game.
 */
class ProblemHandler {
    /*
    The interface.
     */
    private GuesserView guesserView;

    /*
    Game logic variables.
     */
    private static final int correctScore = 2;
    private static final int incorrectScore = -1;

    /*
    Globals representing the current game state.
     */
    private String givenProblem;
    private String correctAnswer;
    private String userAnswer;
    private int problemAnswered;
    private int totalScore;


    ProblemHandler(GuesserView view){
        guesserView = view;
        givenProblem = null;
        correctAnswer = null;
        totalScore = 0;
        problemAnswered = 0;
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
            givenProblem = null;
            correctAnswer = null;
            problemAnswered = 0;
            saveGame();
            guesserView.finishGuess();
        }
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
    void handOutProblem(String id, String answer){
        givenProblem = id;
        correctAnswer = answer;
        problemAnswered += 1;

        guesserView.clearProblemView();
        guesserView.updateProblemView("problem_" + problemAnswered + "_text");
        guesserView.updateProblemView(id);
        guesserView.swapGameState();
    }

    /*
    Save the current game state.
     */
    void saveGame(){
        String currProblem = givenProblem;
    }
}
