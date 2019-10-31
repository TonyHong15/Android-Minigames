package com.example.unlimitedaliengames.alienguesser;

class ProblemHandler {
    private GuesserView guesserView;
    private String correctAnswer;
    private String userAnswer;
    private int problemAnswered;

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
        }else{
            guesserView.updateProblemView("wrong_guess_message");
        }

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
        correctAnswer = answer;
        problemAnswered += 1;

        guesserView.clearProblemView();
        guesserView.updateProblemView("problem_" + problemAnswered + "_text");
        guesserView.updateProblemView(id);
        guesserView.swapGameState();
    }
}
