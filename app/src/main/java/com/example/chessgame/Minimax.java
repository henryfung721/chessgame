package com.example.chessgame;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Minimax {
    static chessboard chessboardclass=new chessboard();
    static chessmove chessmoveclass=new chessmove();
    public static ArrayList<String> validmove = new ArrayList<String>();
    public static ArrayList<Integer> scoreofthemove = new ArrayList<Integer>();
    public static String [][] cloneboard ; // to store the piece
    public static int[][] clonecheckplayer ; //to check who owned the pieces
    public static boolean cloneblackcastling,clonewhitecastling;


    static int kingmark=900;
    static int queenmark=90;
    static int rookmark=50;
    static int bishopmark=30;
    static int knightmark=30;
    static int pawnmark=10;

    public void computermove(int player,String [][] board , int[][] checkplayer ){
        //set up everything
        validmove=chessmoveclass.getallpossiblemove(player,board ,checkplayer);

        clonetheboard();
        clonethedata(board,checkplayer);

        //find the possible moves
        estimatetheboard(player,board,checkplayer);

        Log.d("total of tthe arraylist", "Value: "+scoreofthemove.size());
        int move=0;
        if(player==chessboardclass.player1) {
            move= findthebestmovewhite();
        }else if(player==chessboardclass.player2) {
            move= findthebestmoveblack();
        }

        //make the move
        makethemove(player,move);
        resetarray();
    }

    public static void estimatetheboard(int player,String [][] board , int[][] checkplayer){
        for(int validmovesize=0;validmovesize<validmove.size();validmovesize++){
            String data=validmove.get(validmovesize);
            int firstpositionx=Integer.parseInt(data.substring(0,1));
            int firstpositiony=Integer.parseInt(data.substring(1,2));
            int secondpositionx=Integer.parseInt(data.substring(2,3));
            int secondpositiony=Integer.parseInt(data.substring(3,4));

            //make the changes
            clonearraychangechessposition(player,firstpositionx,firstpositiony,secondpositionx,secondpositiony);

            //get the score
            int boardscore = evaluatetheboard();
            Log.d("first position", "Value: "+"X:"+firstpositionx+"Y:"+firstpositiony);
            Log.d("marks of the move", "Value: "+"X:"+secondpositionx+"    Y:"+secondpositiony +"is "+ boardscore);
            //store value to array
            scoreofthemove.add(boardscore);
            clonethedata(board,checkplayer);
        }
    }

    public static void clonetheboard(){
        cloneboard= new String[chessboardclass.chessboardxvalue][chessboardclass.chessboardyvalue];
        clonecheckplayer= new int[chessboardclass.chessboardxvalue][chessboardclass.chessboardyvalue];
    }
    public static void clonethedata(String [][] board , int[][] checkplayer){
        for(int x=0;x<=7;x++){
            for(int y=0;y<=7;y++){
                cloneboard[x][y]=board[x][y];
                clonecheckplayer[x][y]=checkplayer[x][y];
            }
        }
        clonewhitecastling=chessboardclass.whitecastling;
        cloneblackcastling=chessboardclass.blackcastling;
    }


    public static void clonearraychangechessposition(int player ,int x, int y,int x1,int y1){
        //variable of last clicked pieces
        if(clonecheckplayer[x][y]==player&&clonecheckplayer[x1][y1]==player&&cloneboard[x][y]==chessboardclass.King&&cloneboard[x1][y1]==chessboardclass.Rook){
            if(player==chessboardclass.player1) {
                if (clonewhitecastling == false) {
                    if ((y == 0 && y1 == 4) || (y == 4 && y1 == 0)) {
                        //put the pieces to the new place
                        clonecheckplayer[x][2] = player;
                        cloneboard[x][2] = chessboardclass.King;
                        clonecheckplayer[x][3] = player;
                        cloneboard[x][3] = chessboardclass.Rook;
                        //remove the last pieces
                        clonecheckplayer[x1][y1] = chessboardclass.noone;
                        cloneboard[x1][y1] = chessboardclass.empty;
                        clonecheckplayer[x][y] = chessboardclass.noone;
                        cloneboard[x][y] = chessboardclass.empty;
                        clonewhitecastling = true;
                    } else if ((y == 7 && y1 == 4) || (y == 4 && y1 == 7)) {
                        //put the pieces to the new place
                        clonecheckplayer[x][6] = player;
                        cloneboard[x][6] = chessboardclass.King;
                        clonecheckplayer[x][5] = player;
                        cloneboard[x][5] = chessboardclass.Rook;
                        //remove the last pieces
                        clonecheckplayer[x1][y1] = chessboardclass.noone;
                        cloneboard[x1][y1] = chessboardclass.empty;
                        clonecheckplayer[x][y] = chessboardclass.noone;
                        cloneboard[x][y] = chessboardclass.empty;
                        clonewhitecastling = true;
                    }
                }
            }else if(player==chessboardclass.player2) {
                if (cloneblackcastling == false) {
                    if ((y == 0 && y1 == 4) || (y == 4 && y1 == 0)) {
                        //put the pieces to the new place
                        clonecheckplayer[x][2] = player;
                        cloneboard[x][2] = chessboardclass.King;
                        clonecheckplayer[x][3] = player;
                        cloneboard[x][3] = chessboardclass.Rook;
                        //remove the last pieces
                        clonecheckplayer[x1][y1] = chessboardclass.noone;
                        cloneboard[x1][y1] = chessboardclass.empty;
                        clonecheckplayer[x][y] = chessboardclass.noone;
                        cloneboard[x][y] = chessboardclass.empty;
                        cloneblackcastling = true;
                    } else if ((y == 7 && y1 == 4) || (y == 4 && y1 == 7)) {
                        //put the pieces to the new place
                        clonecheckplayer[x][6] = player;
                        cloneboard[x][6] = chessboardclass.King;
                        clonecheckplayer[x][5] = player;
                        cloneboard[x][5] = chessboardclass.Rook;
                        //remove the last pieces
                        clonecheckplayer[x1][y1] = chessboardclass.noone;
                        cloneboard[x1][y1] = chessboardclass.empty;
                        clonecheckplayer[x][y] = chessboardclass.noone;
                        cloneboard[x][y] = chessboardclass.empty;
                        cloneblackcastling = true;
                    }
                }
            }
        }
        else {
            //put the pieces to the new place
            clonecheckplayer[x1][y1] = clonecheckplayer[x][y];
            cloneboard[x1][y1] = cloneboard[x][y];
            //remove the last pieces
            clonecheckplayer[x][y] = chessboardclass.noone;
            cloneboard[x][y] = chessboardclass.empty;
        }
    }

    public static int evaluatetheboard(){
        int totalmark=0;
        for(int x=0;x<=7;x++){
            for(int y=0;y<=7;y++){
                String chess= cloneboard[x][y];
                int player = clonecheckplayer[x][y];
                int piecemarks=0;
                int playermarks=0;
                if(player==1){
                    playermarks=1;
                }else if(player==2){
                    playermarks=-1;
                }else if(player==0){
                    playermarks=0;
                }
                if(chess==chessboardclass.King){
                    piecemarks=kingmark;
                    piecemarks=piecemarks*playermarks;
                }
                if(chess==chessboardclass.Queen){
                    piecemarks=queenmark;
                    piecemarks=piecemarks*playermarks;
                }
                if(chess==chessboardclass.Bishop){
                    piecemarks=bishopmark;
                    piecemarks=piecemarks*playermarks;
                }
                if(chess==chessboardclass.Knight){
                    piecemarks=knightmark;
                    piecemarks=piecemarks*playermarks;
                }
                if(chess==chessboardclass.Rook){
                    piecemarks=rookmark;
                    piecemarks=piecemarks*playermarks;
                }
                if(chess==chessboardclass.Pawn){
                    piecemarks=pawnmark;
                    piecemarks=piecemarks*playermarks;
                }
                totalmark=totalmark+piecemarks;
            }
        }
        return totalmark;
    }
    public int findthebestmovewhite(){
        int bestmove=0;
        int bestmovevalue= scoreofthemove.get(0);
            for (int position = 0; position < scoreofthemove.size(); position++) {
                Log.d("score1", "Value: "+scoreofthemove.get(position));
                if (scoreofthemove.get(position) > bestmovevalue) {
                    bestmove = position;
                    bestmovevalue = scoreofthemove.get(position);
                }
            }
        return bestmove;
    }
    public int findthebestmoveblack(){
        int bestmove=0;
        int bestmovevalue= scoreofthemove.get(0);
        for (int position = 0; position < scoreofthemove.size(); position++) {
            Log.d("score2", "Value: "+scoreofthemove.get(position));
            if (scoreofthemove.get(position) < bestmovevalue) {
                bestmove = position;
                bestmovevalue = scoreofthemove.get(position);
            }
        }
        return bestmove;
    }

    public void computerchangechessposition(int player ,int x, int y,int x1,int y1){
        //variable of last clicked pieces
        if(chessboardclass.checkplayer[x][y]==player&&chessboardclass.checkplayer[x1][y1]==player&&chessboardclass.board[x][y]==chessboardclass.King&&chessboardclass.board[x1][y1]==chessboardclass.Rook){
            if(player==chessboardclass.player1) {
                if (clonewhitecastling == false) {
                    if ((y == 0 && y1 == 4) || (y == 4 && y1 == 0)) {
                        //put the pieces to the new place
                        chessboardclass.checkplayer[x][2] = player;
                        chessboardclass.board[x][2] = chessboardclass.King;
                        chessboardclass.checkplayer[x][3] = player;
                        chessboardclass.board[x][3] = chessboardclass.Rook;
                        //remove the last pieces
                        chessboardclass.checkplayer[x1][y1] = chessboardclass.noone;
                        chessboardclass.board[x1][y1] = chessboardclass.empty;
                        chessboardclass.checkplayer[x][y] = chessboardclass.noone;
                        chessboardclass.board[x][y] = chessboardclass.empty;
                        chessboardclass.whitecastling = true;
                    } else if ((y == 7 && y1 == 4) || (y == 4 && y1 == 7)) {
                        //put the pieces to the new place
                        chessboardclass.checkplayer[x][6] = player;
                        chessboardclass.board[x][6] = chessboardclass.King;
                        chessboardclass.checkplayer[x][5] = player;
                        chessboardclass.board[x][5] = chessboardclass.Rook;
                        //remove the last pieces
                        chessboardclass.checkplayer[x1][y1] = chessboardclass.noone;
                        chessboardclass.board[x1][y1] = chessboardclass.empty;
                        chessboardclass.checkplayer[x][y] = chessboardclass.noone;
                        chessboardclass.board[x][y] = chessboardclass.empty;
                        chessboardclass.whitecastling = true;
                    }
                }
            }else if(player==chessboardclass.player2) {
                if (chessboardclass.blackcastling == false) {
                    if ((y == 0 && y1 == 4) || (y == 4 && y1 == 0)) {
                        //put the pieces to the new place
                        chessboardclass.checkplayer[x][2] = player;
                        chessboardclass.board[x][2] = chessboardclass.King;
                        chessboardclass.checkplayer[x][3] = player;
                        chessboardclass.board[x][3] = chessboardclass.Rook;
                        //remove the last pieces
                        chessboardclass.checkplayer[x1][y1] = chessboardclass.noone;
                        chessboardclass.board[x1][y1] = chessboardclass.empty;
                        chessboardclass.checkplayer[x][y] = chessboardclass.noone;
                        chessboardclass.board[x][y] = chessboardclass.empty;
                        chessboardclass.blackcastling = true;
                    } else if ((y == 7 && y1 == 4) || (y == 4 && y1 == 7)) {
                        //put the pieces to the new place
                        chessboardclass.checkplayer[x][6] = player;
                        chessboardclass.board[x][6] = chessboardclass.King;
                        chessboardclass.checkplayer[x][5] = player;
                        chessboardclass.board[x][5] = chessboardclass.Rook;
                        //remove the last pieces
                        chessboardclass.checkplayer[x1][y1] = chessboardclass.noone;
                        chessboardclass.board[x1][y1] = chessboardclass.empty;
                        chessboardclass.checkplayer[x][y] = chessboardclass.noone;
                        chessboardclass.board[x][y] = chessboardclass.empty;
                        chessboardclass.blackcastling = true;
                    }
                }
            }
        }
        else {
            //put the pieces to the new place
            chessboardclass.checkplayer[x1][y1] = chessboardclass.checkplayer[x][y];
            chessboardclass.board[x1][y1] = chessboardclass.board[x][y];
            //remove the last pieces
            chessboardclass.checkplayer[x][y] = chessboardclass.noone;
            chessboardclass.board[x][y] = chessboardclass.empty;
        }
    }
    public void makethemove(int player, int randomnumber){
        String data=validmove.get(randomnumber);
        int firstpositionx=Integer.parseInt(data.substring(0,1));
        int firstpositiony=Integer.parseInt(data.substring(1,2));
        int secondpositionx=Integer.parseInt(data.substring(2,3));
        int secondpositiony=Integer.parseInt(data.substring(3,4));
        computerchangechessposition(player,firstpositionx,firstpositiony,secondpositionx,secondpositiony);
        chessmoveclass.clearvalidmovearray();
    }
    public void resetarray(){
        scoreofthemove.clear();
    }

}
