package com.example.chessgame.chessboard;

import android.util.Log;

import com.example.chessgame.chessboard.chessboard;
import com.example.chessgame.chessboard.chessmove;
import com.example.chessgame.constructor.minimax_score;
import com.example.chessgame.constructor.move_and_board;
import com.example.chessgame.constructor.mymove;
import com.example.chessgame.constructor.theboard;
import com.example.chessgame.constructor.themove;

import java.util.ArrayList;
import java.util.Collections;

public class Minimax {
    static chessboard chessboardclass=new chessboard();
    static chessmove chessmoveclass=new chessmove();

    public static boolean cloneblackcastling,clonewhitecastling;

    static int kingmark=900;
    static int queenmark=90;
    static int rookmark=50;
    static int bishopmark=30;
    static int knightmark=30;
    static int pawnmark=10;



    //run when computer move
    public void computermove(int player,String [][] board , int[][] checkplayer,int minimaxlooptime ){
        {
            //setup the data need for computer AI
            theboard clone = clonetheboard(board,checkplayer);
            mymove getmymove = null;
            if(player==1) {
                 getmymove = minimax(player, clone.getBoard(), clone.getCheckplayer(), minimaxlooptime, true);
            }
            if(player==2) {
                 getmymove = minimax(player, clone.getBoard(), clone.getCheckplayer(), minimaxlooptime, false);
            }
            Log.d("want to move ", "Value: " + getmymove.getMove());
            Log.d("want to move ", "Value: " + getmymove.getScore());
            makethemove1(player, getmymove.getMove());
        }
    }

    //computer AI make the move
    public static mymove minimax(int player,String [][] board , int[][] checkplayer ,int depth ,Boolean maximizingplayer){
        //the variable to store return output
        mymove returnoutput=null;

        //if the depth ended
        if(depth<=0){
            returnoutput= new mymove("",evaluatetheboard1(board,checkplayer));
            return returnoutput;
        }
        //if game win
        if(ifwingame(player, board ,checkplayer)){
            returnoutput= new mymove("",evaluatetheboard1(board,checkplayer));
            return returnoutput;
        }
        /////////////

        if(maximizingplayer==true){
            returnoutput =  maximizing(player,board,checkplayer,depth);
        }else if(maximizingplayer==false) {
            returnoutput =  minimizing(player,board,checkplayer,depth);
        }
        return returnoutput;
    }

    private static boolean ifwingame(int player,String [][] board , int[][] checkplayer) {
        int checkwin=0;
        for(int x = 0;x<=7;x++){
            for(int y = 0;y<=7;y++){
                if(board[x][y]==chessboardclass.King){
                    if(checkplayer[x][y]==player){
                        checkwin=checkwin+1;
                    }
                }
            }
        }
        if(checkwin==0){
            return true;
        }
        return false;
    }

    public static mymove maximizing(int player, String[][] board, int[][] checkplayer, int depth){
        //storing the moved place
        ArrayList<String> validmove1 ;
        int best =-9999999;
        String mymove = null;
        validmove1=clonevaluemovearray(player,board ,checkplayer);
        Log.d("arraylist size",":"+validmove1.size());
        for(int x=0;x<validmove1.size();x++){
            theboard getthedata=changetheboard(player,board,checkplayer,x,validmove1);
            mymove getmymove = minimax(changeplayer(player),getthedata.getBoard(),getthedata.getCheckplayer(),depth-1,false);
            if(getmymove.getScore()>best){
                best=getmymove.getScore();
                mymove=validmove1.get(x);
            }
        }
        return new mymove(mymove,best);
    }
    public static mymove minimizing(int player, String[][] board, int[][] checkplayer, int depth){
        //storing the moved place
        ArrayList<String> validmove1;

        int best =9999999;
        String mymove = null;
        validmove1=clonevaluemovearray(player,board ,checkplayer);

        for(int x=0;x<validmove1.size();x++) {
            theboard getthedata=changetheboard(player,board,checkplayer,x,validmove1);
            mymove getmymove = minimax(changeplayer(player), getthedata.getBoard(), getthedata.getCheckplayer(), depth-1, true);

            if(getmymove.getScore()<best){
                best=getmymove.getScore();
                mymove=validmove1.get(x);
            }
        }
        return new mymove(mymove,best);
    }

    // evaluate the baord
    public static int evaluatetheboard1(String[][] cloneboard1, int[][] clonecheckplayer1){
        int totalmark=0;
        for(int x=0;x<=7;x++){
            for(int y=0;y<=7;y++){

                String chess= cloneboard1[x][y];
                int player = clonecheckplayer1[x][y];
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
                    piecemarks=kingmark*playermarks;
                }
                if(chess==chessboardclass.Queen){
                    piecemarks=queenmark*playermarks;
                }
                if(chess==chessboardclass.Bishop){
                    piecemarks=bishopmark*playermarks;
                }
                if(chess==chessboardclass.Knight){
                    piecemarks=knightmark*playermarks;
                }
                if(chess==chessboardclass.Rook){
                    piecemarks=rookmark*playermarks;
                }
                if(chess==chessboardclass.Pawn){
                    piecemarks=pawnmark*playermarks;
                }
                totalmark=totalmark+piecemarks;
/*                Log.d("score","array chessboard: "+x+""+y+":"+cloneboard1[x][y]+""+clonecheckplayer1[x][y]);
                Log.d("score","totalmark: "+totalmark);*/

            }
        }
       // Log.d("score","end");
        return totalmark;
    }

    //change the board(fake)(for minimax testing)
    public static theboard changetheboard(int player, String[][] board, int[][] checkplayer, int position, ArrayList<String> validmove){

            String data=validmove.get(position);
            int x=Integer.parseInt(data.substring(0,1));
            int y=Integer.parseInt(data.substring(1,2));
            int x1=Integer.parseInt(data.substring(2,3));
            int y1=Integer.parseInt(data.substring(3,4));
            String[][]cloneboard = new String[chessmoveclass.chessboardxvalue][chessmoveclass.chessboardyvalue];
            int[][]clonecheckplayer = new int[chessmoveclass.chessboardxvalue][chessmoveclass.chessboardyvalue];
            for(int xloop=0;xloop<chessmoveclass.chessboardxvalue;xloop++){
                for(int yloop=0;yloop<chessmoveclass.chessboardyvalue;yloop++){
                    cloneboard[xloop][yloop]=board[xloop][yloop];
                    clonecheckplayer[xloop][yloop]=checkplayer[xloop][yloop];
                }
            }



          //make the moves
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
                theboard storetheboard= new theboard(cloneboard,clonecheckplayer);
            return  storetheboard;
    }


    //for computer giving advice(a bit different with the normal version, it will store the first move data. )
    public static ArrayList<minimax_score> computeradvice(int player,String [][] board , int[][] checkplayer ,int depth ){
        theboard clone = clonetheboard(board,checkplayer);
        //if the depth ended

        if(player==1){
           return sorting(maximizing1(player,board,checkplayer,depth),false);
        }else if(player==2) {
           return sorting(minimizing1(player,board,checkplayer,depth),true);
        }
        return null;
    }
    public static ArrayList<minimax_score> maximizing1(int player, String[][] board, int[][] checkplayer, int depth){
        //storing the moved place
        ArrayList<String> validmove1 ;
        //storing the moved score
        ArrayList<mymove> scoreofthemove1 = new ArrayList<mymove>();
        validmove1=clonevaluemovearray(player,board ,checkplayer);
        Log.d("arraylist size",":"+validmove1.size());
        for(int x=0;x<validmove1.size();x++){
            theboard getthedata=changetheboard(player,board,checkplayer,x,validmove1);
            mymove getmymove = minimax(changeplayer(player),getthedata.getBoard(),getthedata.getCheckplayer(),depth-1,false);
            scoreofthemove1.add(getmymove);
        }
        return returnthemovescore(player,validmove1,scoreofthemove1);
    }
    public static ArrayList<minimax_score> minimizing1(int player, String[][] board, int[][] checkplayer, int depth){
        //storing the moved place
        ArrayList<String> validmove1;
        //storing the moved score
        ArrayList<mymove> scoreofthemove1 = new ArrayList<mymove>();
        validmove1=clonevaluemovearray(player,board ,checkplayer);

        for(int x=0;x<validmove1.size();x++) {
            theboard getthedata=changetheboard(player,board,checkplayer,x,validmove1);
            mymove getmymove = minimax(changeplayer(player), getthedata.getBoard(), getthedata.getCheckplayer(), depth-1, true);
            scoreofthemove1.add(getmymove);
        }
        return returnthemovescore(player,validmove1,scoreofthemove1);
    }
    public static ArrayList<minimax_score> returnthemovescore(int player,ArrayList<String> getvalidmove,ArrayList<mymove> getscoreofthemove){
        ArrayList<minimax_score> minimaxscorelist= new ArrayList<>();
        for(int position=0;position<getvalidmove.size();position++) {
            String data=getvalidmove.get(position);
            int x=Integer.parseInt(data.substring(0,1));
            int y=Integer.parseInt(data.substring(1,2));
            int x1=Integer.parseInt(data.substring(2,3));
            int y1=Integer.parseInt(data.substring(3,4));
            String predictdata=getscoreofthemove.get(position).getMove();
            int predictx=Integer.parseInt(predictdata.substring(0,1));
            int predicty=Integer.parseInt(predictdata.substring(1,2));
            int predictx1=Integer.parseInt(predictdata.substring(2,3));
            int predicty1=Integer.parseInt(predictdata.substring(3,4));

            String predictchesstype1=predictnextmovevalue(chessboardclass.board,chessboardclass.checkplayerclone,x,y,x1,y1,predictx,predicty);
            String predictchesstype2=predictnextmovevalue(chessboardclass.board,chessboardclass.checkplayerclone,x,y,x1,y1,predictx1,predicty1);
            minimaxscorelist.add(new minimax_score(player, chessboardclass.board[x][y], x, y, chessboardclass.board[x1][y1], x1, y1, false, getscoreofthemove.get(position).getScore(),predictchesstype1, predictchesstype2,predictx,predicty,predictx1,predicty1));
        }
        return minimaxscorelist;
    }
    private static String predictnextmovevalue(String[][] getboard, int[][] getcheckplayer, int x, int y, int x1, int y1, int askingvaluex, int askingvaluey) {
        theboard clone = clonetheboard(getboard,getcheckplayer);
        String [][] board= clone.getBoard();

        board[x1][y1] = board[x][y];
        //remove the last pieces
        board[x][y] = chessboardclass.empty;
        return board[askingvaluex][askingvaluey];
    }


    public static ArrayList<minimax_score> sorting(ArrayList<minimax_score> arraylist,boolean bigtosmall){
        if(bigtosmall){
            Collections.sort(arraylist, minimax_score.ascendingminimaxscore);
        }else if(!bigtosmall){
            Collections.sort(arraylist, minimax_score.descendingingminimaxscore);
        }
        return arraylist;
    }

    //setting up the data
    public static theboard clonetheboard(String [][] board , int[][] checkplayer){
        String [][] cloneboard= new String[chessboardclass.chessboardxvalue][chessboardclass.chessboardyvalue];
        int[][] clonecheckplayer = new int[chessboardclass.chessboardxvalue][chessboardclass.chessboardyvalue];
        for(int x=0;x<=7;x++){
            for(int y=0;y<=7;y++){
                cloneboard[x][y]=board[x][y];
                clonecheckplayer[x][y]=checkplayer[x][y];
            }
        }
        clonewhitecastling=chessboardclass.whitecastling;
        cloneblackcastling=chessboardclass.blackcastling;
        return new theboard(cloneboard,clonecheckplayer);
    }
    public static ArrayList<String> clonevaluemovearray(int player, String[][] board, int[][] checkplayer){
        ArrayList getarraylist = chessmoveclass.getallpossiblemove(player,board ,checkplayer);
        ArrayList returnarray = new ArrayList();
        for (int x=0;x<getarraylist.size();x++){
            returnarray.add(getarraylist.get(x));
        }
        chessmoveclass.clearvalidmovearray();
        return returnarray;
    }
    public static int changeplayer(int player){
        if(player==chessboardclass.player1){
            return chessboardclass.player2;
        }
        if(player==chessboardclass.player2){
            return chessboardclass.player1;
        }
        return 0;
    }


    //make the moves(real)
    public void makethemove1(int player,String move){
        int firstpositionx=Integer.parseInt(move.substring(0,1));
        int firstpositiony=Integer.parseInt(move.substring(1,2));
        int secondpositionx=Integer.parseInt(move.substring(2,3));
        int secondpositiony=Integer.parseInt(move.substring(3,4));

        computerchangechessposition(player,firstpositionx,firstpositiony,secondpositionx,secondpositiony);
        chessmoveclass.clearvalidmovearray();
    }
    public  void computerchangechessposition(int player, int x, int y, int x1, int y1){
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





}
