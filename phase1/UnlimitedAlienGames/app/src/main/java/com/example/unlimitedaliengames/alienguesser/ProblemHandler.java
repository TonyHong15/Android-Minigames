package com.example.unlimitedaliengames.alienguesser;

public class ProblemHandler {
    private GuesserView guesserView;
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
        guesserView.swapGameState();
    }

    private boolean checkAnswer(){
        if(correctAnswer != null && userAnswer != null){
            return correctAnswer.equals(userAnswer);
        }else{
            return false;
        }
    }

    public void handOutProblem(String id, String answer){
        correctAnswer = answer;

        guesserView.clearProblemView();
        guesserView.updateProblemView(id);
        guesserView.swapGameState();
    }
}
