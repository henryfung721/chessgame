package com.example.chessgame.constructor;

import java.util.Comparator;

public class minimax_score {
        /*
        constructor to store the previous move

        meaning of the variables:
        first position(the position where the piece is not moved) :(firstpositionx,firstpositiony)
        second position(the position where the piece are moving to) :(secondpositionx,secondpositiony)
        moving player(the player who move the piece): player
        */
        private int player;
        private String firstchesstype;
        private int firstpositionx ;
        private int firstpositiony;
        private String secondchesstype;
        private int secondpositionx ;
        private int secondpositiony;
        private boolean castling;
        private int minimaxscrore;
        private String predictchesstype;
        private int predictfirstpositionx ;
        private int predictfirstpositiony;
        private String predictsecondchesstype;
        private int predictsecondpositionx ;
        private int predictsecondpositiony;

        public minimax_score(int player, String firstchesstype, int firstpositionx, int firstpositiony, String secondchesstype, int secondpositionx, int secondpositiony, boolean castling,int minimaxscrore,String predictfirstchesstype,String predictsecondchesstype, int predictfirstpositionx, int predictfirstpositiony,  int predictsecondpositionx, int predictsecondpositiony) {
            this.player=player;
            this.firstchesstype=firstchesstype;
            this.firstpositionx = firstpositionx;
            this.firstpositiony = firstpositiony;
            this.secondchesstype=secondchesstype;
            this.secondpositionx = secondpositionx;
            this.secondpositiony = secondpositiony;
            this.castling=castling;
            this.minimaxscrore=minimaxscrore;
            this.predictchesstype=predictfirstchesstype;
            this.predictfirstpositionx = predictfirstpositionx;
            this.predictfirstpositiony = predictfirstpositiony;
            this.predictsecondchesstype=predictsecondchesstype;
            this.predictsecondpositionx = predictsecondpositionx;
            this.predictsecondpositiony = predictsecondpositiony;
        }



    public int getPlayer() {
            return player;
        }
        public String getFirstchesstype(){
            return firstchesstype;
        }
        public String getSecondchesstype(){
            return secondchesstype;
        }
        public int getFirstpositionx() {
            return firstpositionx;
        }
        public int getFirstpositiony() {
            return firstpositiony;
        }
        public int getSecondpositionx() {
            return secondpositionx;
        }
        public int getSecondpositiony() {
            return secondpositiony;
        }
        public boolean isCastling() {
            return castling;
        }
        public int getMinimaxscrore(){return  minimaxscrore;}
        public String getpredictFirstchesstype(){
            return predictchesstype;
        }
        public String getpredictSecondchesstype(){
            return predictsecondchesstype;
        }
        public int getpredictFirstpositionx() {
            return predictfirstpositionx;
        }
        public int getpredictFirstpositiony() {
            return predictfirstpositiony;
        }
        public int getpredictSecondpositionx() {
            return predictsecondpositionx;
        }
        public int getpredictSecondpositiony() {
            return predictsecondpositiony;
        }

        public static Comparator <minimax_score> ascendingminimaxscore = new Comparator<minimax_score>(){
            @Override
            public int compare(minimax_score o1, minimax_score o2) {
                int rollno1 = o1.getMinimaxscrore();
                int rollno2 = o2.getMinimaxscrore();
                return rollno1-rollno2;
            }
        };

    public static Comparator <minimax_score> descendingingminimaxscore = new Comparator<minimax_score>(){
        @Override
        public int compare(minimax_score o1, minimax_score o2) {
            int rollno1 = o1.getMinimaxscrore();
            int rollno2 = o2.getMinimaxscrore();
            return rollno2-rollno1;
        }
    };

}
