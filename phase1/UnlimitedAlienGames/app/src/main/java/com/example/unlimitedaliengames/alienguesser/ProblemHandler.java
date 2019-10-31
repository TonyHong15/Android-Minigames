package com.example.unlimitedaliengames.alienguesser;

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
    public void takeInAnswer(String answer){
        userAnswer = answer;
        processAnswer();
    }

    private void processAnswer(){
        if(checkAnswer()){
            guesserView.updateProblemView("next_problem_message");
        }else{
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
}
