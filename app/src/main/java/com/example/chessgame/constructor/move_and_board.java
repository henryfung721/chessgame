package com.example.chessgame.constructor;

public class move_and_board {

        private final theboard board;
        private final themove move;

        public move_and_board(theboard board, themove move) {
            this.board = board;
            this.move = move;
        }

        public theboard getBoard() {
            return board;
        }

        public themove getMove() {
            return move;
        }

}
