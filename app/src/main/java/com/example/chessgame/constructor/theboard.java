package com.example.chessgame.constructor;

public final class theboard {
    private String[][] board1 ;
    //storing the moved score
    private  int[][] checkplayer1;


    public theboard(String[][] board1 , int[][] checkplayer1) {
        this.board1 = board1;
        this.checkplayer1 = checkplayer1;
    }

    public String[][] getBoard() {
        return board1;
    }

    public int[][] getCheckplayer() {
        return checkplayer1;
    }
}
