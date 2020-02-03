package com.tetris;

import com.tetris.manager.GameEngine;

public class Main {

    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine(12, 24);
        gameEngine.run();
    }
}
