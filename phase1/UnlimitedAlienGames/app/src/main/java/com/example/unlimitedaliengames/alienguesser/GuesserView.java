package com.example.unlimitedaliengames.alienguesser;

public interface GuesserView {
    void swapGameState();
    void updateProblemView(String message, boolean direct);
    void clearProblemView();
    void updateScoreView(String message);
    void finishGuess();
}
