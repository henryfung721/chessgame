package com.example.chessgame.constructor;

public final class mymove {
    private final String move;
    private final int score;

    public mymove(String move, int score) {
        this.move = move;
        this.score = score;
    }

    public String getMove() {
        return move;
    }

    public int getScore() {
        return score;
    }
}