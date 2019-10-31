package com.example.unlimitedaliengames.alienguesser;

public interface GuesserView {
    void swapGameState();
    void updateProblemView(String message);
    void updateScoreView(String message);
}
