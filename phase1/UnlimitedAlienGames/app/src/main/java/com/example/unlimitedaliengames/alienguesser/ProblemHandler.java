package com.example.unlimitedaliengames.alienguesser;

class ProblemHandler {
    private GuesserView guesserView;
    private String givenProblem;
    private String correctAnswer;
    private String userAnswer;
    private int problemAnswered;
    private int totalScore = 0;
    private static final int correctScore = 2;
    private static final int incorrectScore = -1;

    ProblemHandler(GuesserView view){
        guesserView = view;
        problemAnswered = 0;
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
            guesserView.finishGuess();
        }
    }

    private boolean checkAnswer(){
        if(correctAnswer != null && userAnswer != null){
            return correctAnswer.equals(userAnswer);
        }else{
            return false;
        }
    }

    void handOutProblem(String id, String answer){
        givenProblem = id;
        correctAnswer = answer;
        problemAnswered += 1;

        guesserView.clearProblemView();
        guesserView.updateProblemView("problem_" + problemAnswered + "_text");
        guesserView.updateProblemView(id);
        guesserView.swapGameState();
    }
}
