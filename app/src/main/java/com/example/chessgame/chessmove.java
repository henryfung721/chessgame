package com.example.chessgame;

import android.util.Log;

import java.util.ArrayList;

public class chessmove {
       static chessboard chessboardclass = new chessboard();
      public static ArrayList<String> validmove = new ArrayList<String>();
     static int player1 =1;
     static int player2 =2;
     static int noone =0;
      static int mymove =1;
      static int opponentmove =2;
      static int canmove =3;
     static String empty="empty";
     static String Pawn ="Pawn";
     static String Rook ="Rook";
     static String Knight ="Knight";
     static String Bishop ="Bishop";
     static String Queen ="Queen";
     static String King ="King";
     static boolean whitecastling=false;
     static boolean blackcastling=false;

      public static String [][] board ; // to store the piece
      public static int[][] checkplayer ; //to check who owned the pieces ******* <only final changes>
    static int chessboardxvalue = 8;
    static int chessboardyvalue = 8;

    public int[][] bishopmove(int x,int y,String [][] getboard , int[][] getcheckplayer ) {
        createarraylist(getboard,getcheckplayer);
        changeopponent(x,y);
        int columnex,columney;
        for(columnex=x+1,columney=y+1;columnex<=7&&columney<=7;columnex++,columney++) {
                if (checkplayer[columnex][columney] == noone) {
                    addpossiblemoves(columnex,columney);
                } else if (checkplayer[columnex][columney] == opponentmove) {
                    addpossiblemoves(columnex,columney);
                    break;
                } else {
                    break;
                }
        }
        for(columnex=x+1,columney=y-1;columnex<=7&&columney>=0;columnex++,columney--) {
                if (checkplayer[columnex][columney] == noone) {
                    addpossiblemoves(columnex,columney);
                } else if (checkplayer[columnex][columney] == opponentmove) {
                    addpossiblemoves(columnex,columney);
                    break;
                } else {
                    break;
                }
        }
        for(columnex=x-1,columney=y+1;columnex>=0&&columney<=7;columnex--,columney++) {
                if (checkplayer[columnex][columney] == noone) {
                    addpossiblemoves(columnex,columney);
                } else if (checkplayer[columnex][columney] == opponentmove) {
                    addpossiblemoves(columnex,columney);
                    break;
                } else {
                    break;
                }
        }
        for(columnex=x-1,columney=y-1;columnex>=0&&columney>=0;columnex--,columney--) {
                if (checkplayer[columnex][columney] == noone) {
                    addpossiblemoves(columnex,columney);
                } else if (checkplayer[columnex][columney] == opponentmove) {
                    addpossiblemoves(columnex,columney);
                    break;
                } else {
                    break;
                }
        }
        putarraydatatobaord();
        return checkplayer;
    }
    public int[][] queenmove(int x,int y,String [][] getboard , int[][] getcheckplayer ) {
        createarraylist(getboard,getcheckplayer);
        changeopponent(x,y);
        for(int columnex=x+1;columnex<=7;columnex++) {
            if(checkplayer[columnex][y]==noone){
                addpossiblemoves(columnex,y);
            } else if (checkplayer[columnex][y]==opponentmove) {
                addpossiblemoves(columnex,y);
                break;
            } else{
                break;
            }
        }
        for(int columnex=x-1;columnex>=0;columnex--) {
            if(checkplayer[columnex][y]==noone){
                addpossiblemoves(columnex,y);
            } else if (checkplayer[columnex][y]==opponentmove) {
                addpossiblemoves(columnex,y);
                break;
            } else{
                break;
            }
        }
        for(int columney=y+1;columney<=7;columney++) {
            if(checkplayer[x][columney]==noone){
                addpossiblemoves(x,columney);
            } else if (checkplayer[x][columney]==opponentmove) {
                addpossiblemoves(x,columney);
                break;
            } else{
                break;
            }
        }
        for(int columney=y-1;columney>=0;columney--) {
            if(checkplayer[x][columney]==noone){
                addpossiblemoves(x,columney);
            } else if (checkplayer[x][columney]==opponentmove) {
                addpossiblemoves(x,columney);
                break;
            } else{
                break;
            }
        }
        for(int columnex=x+1,columney=y+1;columnex<=7&&columney<=7;columnex++,columney++) {
            if (checkplayer[columnex][columney] == noone) {
                addpossiblemoves(columnex,columney);
            } else if (checkplayer[columnex][columney] == opponentmove) {
                addpossiblemoves(columnex,columney);
                break;
            } else {
                break;
            }
        }
        for(int columnex=x+1,columney=y-1;columnex<=7&&columney>=0;columnex++,columney--) {
            if (checkplayer[columnex][columney] == noone) {
                addpossiblemoves(columnex,columney);
            } else if (checkplayer[columnex][columney] == opponentmove) {
                addpossiblemoves(columnex,columney);
                break;
            } else {
                break;
            }
        }
        for(int columnex=x-1,columney=y+1;columnex>=0&&columney<=7;columnex--,columney++) {
            if (checkplayer[columnex][columney] == noone) {
                addpossiblemoves(columnex,columney);
            } else if (checkplayer[columnex][columney] == opponentmove) {
                addpossiblemoves(columnex,columney);
                break;
            } else {
                break;
            }
        }
        for(int columnex=x-1,columney=y-1;columnex>=0&&columney>=0;columnex--,columney--) {
            if (checkplayer[columnex][columney] == noone) {
                addpossiblemoves(columnex,columney);
            } else if (checkplayer[columnex][columney] == opponentmove) {
                addpossiblemoves(columnex,columney);
                break;
            } else {
                break;
            }
        }
        putarraydatatobaord();
        return checkplayer;
    }
    public int[][] pawnmove(int x,int y,String [][] getboard , int[][] getcheckplayer ) {
        createarraylist(getboard,getcheckplayer);
             changeopponent(x,y);
            if (checkplayer[x][y] == player1) {
                //the chess is opponent's side
                if (x == 1) {
                    //to check if the pawn are already moved
                    if(checkplayer[x+1][y]==noone) {
                        addpossiblemoves(x+1,y);
                        if(checkplayer[x+2][y]==noone) {
                            addpossiblemoves(x+2,y);
                        }
                    }
                }else if(checkplayer[x+1][y]==noone) {
                    addpossiblemoves(x+1,y);
                }
                if(y!=7){
                    if(checkplayer[x+1][y+1]==opponentmove){
                        //pawn eat opponent chess
                        addpossiblemoves(x+1,y+1);
                    }
                }
                if(y!=0) {
                    if (checkplayer[x + 1][y - 1] == opponentmove) {
                        //pawn eat opponent chess
                        addpossiblemoves(x+1,y-1);
                    }
                }
            } else if (checkplayer[x][y] == player2) {
                //the chess is opponent's side
                if (x == 6) {
                    //to check if the pawn are already moved
                     if(checkplayer[x-1][y]==noone) {
                         addpossiblemoves(x-1,y);
                         if(checkplayer[x-2][y]==noone) {
                             addpossiblemoves(x-2,y);
                         }
                     }
                }else  if(checkplayer[x-1][y]==noone) {
                    addpossiblemoves(x-1,y);
                }
                if(y!=7) {
                    if (checkplayer[x - 1][y + 1] == opponentmove) {
                        //pawn eat opponent chess
                        addpossiblemoves(x-1,y+1);
                    }
                }
                if(y!=0) {
                    if (checkplayer[x - 1][y - 1] == opponentmove) {
                        //pawn eat opponent chess
                        addpossiblemoves(x-1,y-1);
                    }
                }
            }
        putarraydatatobaord();
        return checkplayer;
        }
    public int[][] rookmove(int x,int y,String [][] getboard , int[][] getcheckplayer ) {
        createarraylist(getboard,getcheckplayer);
        changeopponent(x,y);
        for(int columnex=x+1;columnex<=7;columnex++) {
            if(checkplayer[columnex][y]==noone){
                addpossiblemoves(columnex,y);
            } else if (checkplayer[columnex][y]==opponentmove) {
                addpossiblemoves(columnex,y);
                break;
            } else{
                break;
            }
        }
        for(int columnex=x-1;columnex>=0;columnex--) {
            if(checkplayer[columnex][y]==noone){
                addpossiblemoves(columnex,y);
            } else if (checkplayer[columnex][y]==opponentmove) {
                addpossiblemoves(columnex,y);
                break;
            } else{
                break;
            }
        }
        for(int columney=y+1;columney<=7;columney++) {
            if(checkplayer[x][columney]==noone){
                addpossiblemoves(x,columney);
            } else if (checkplayer[x][columney]==opponentmove) {
                addpossiblemoves(x,columney);
                break;
            } else{
                break;
            }
        }
        for(int columney=y-1;columney>=0;columney--) {
            if(checkplayer[x][columney]==noone){
                addpossiblemoves(x,columney);
            } else if (checkplayer[x][columney]==opponentmove) {
                addpossiblemoves(x,columney);
                break;
            } else{
                break;
            }
        }
        castling(x,y);
        putarraydatatobaord();
        return checkplayer;
    }
    public int[][] knightmove(int x,int y,String [][] getboard , int[][] getcheckplayer ) {
        createarraylist(getboard,getcheckplayer);
        for(int x1=0;x1<=7;x1++) {
            for(int y1=0;y1<=7;y1++) {
                if(x1-x==2&&y1-y==1) {
                    String place = Integer.toString(x1)+Integer.toString(y1);
                    validmove.add(place);
                }
                if(x1-x==2&&y-y1==1) {
                    String place = Integer.toString(x1)+Integer.toString(y1);
                    validmove.add(place);
                }
                if(x-x1==2&&y1-y==1) {
                    String place = Integer.toString(x1)+Integer.toString(y1);
                    validmove.add(place);
                }

                if(x-x1==2&&y-y1==1) {
                    String place = Integer.toString(x1)+Integer.toString(y1);
                    validmove.add(place);
                }

                if(x1-x==1&&y1-y==2) {
                    String place = Integer.toString(x1)+Integer.toString(y1);
                    validmove.add(place);
                }
                if(x1-x==1&&y-y1==2) {
                    String place = Integer.toString(x1)+Integer.toString(y1);
                    validmove.add(place);
                }
                if(x-x1==1&&y1-y==2) {
                    String place = Integer.toString(x1)+Integer.toString(y1);
                    validmove.add(place);
                }

                if(x-x1==1&&y-y1==2) {
                    String place = Integer.toString(x1)+Integer.toString(y1);
                    validmove.add(place);
                }
            }
        }
        checkvalid(x,y);
        putarraydatatobaord();
        return checkplayer;
    }
    public int[][] kingmove(int x,int y,String [][] getboard , int[][] getcheckplayer ) {
        createarraylist(getboard,getcheckplayer);
        for(int x1=0;x1<=7;x1++) {
            for(int y1=0;y1<=7;y1++) {
                if(x1-x==1||x-x1==1) {
                    if(y1-y==1||y-y1==1) {
                        addpossiblemoves(x1,y1);
                    }
                }
                if(x-x1==1&&y-y1==0) {
                    addpossiblemoves(x1,y1);
                }
                if(x-x1==-1&&y-y1==0) {
                    addpossiblemoves(x1,y1);
                }
                if(x-x1==0&&y-y1==-1) {
                    addpossiblemoves(x1,y1);
                }
                if(x-x1==0&&y-y1==1) {
                    addpossiblemoves(x1,y1);
                }
            }
        }
        checkvalid(x,y);
        putarraydatatobaord();
        return checkplayer;
    }
    public void addpossiblemoves(int x ,int y){
        //adding the possible moves to arraylist
        String place = Integer.toString(x)+Integer.toString(y);
        validmove.add(place);
    }
    public void checkvalid(int x, int y) {
        // only for knight and king
        for (int i = 0; i < validmove.size(); i++) {
            String data = validmove.get(i);
            int intdata = Integer.parseInt(data);
            int x1=intdata/10;
            int y1=intdata%10;
            if(checkplayer[x][y]==checkplayer[x1][y1]){
                validmove.remove(i);
                i=i-1;
            }
        }
    }
    public void changeopponent(int x ,int y){
        //check which player moves
        if(checkplayer[x][y]==player1){
            mymove =1;
            opponentmove =2;
        } else if(checkplayer[x][y]==player2){
            mymove =2;
            opponentmove =1;
        }
    }
    public void createarraylist(String [][] getboard , int[][] getcheckplayer ){
        board = new String[chessboardxvalue][chessboardyvalue];
        checkplayer = new int[chessboardxvalue][chessboardyvalue];
        board=getboard;
        checkplayer=getcheckplayer;
        whitecastling=chessboard.whitecastling;
        blackcastling=chessboard.blackcastling;
    }
    public static void putarraydatatobaord() {
        //read the arraylist
        for (int i = 0; i < validmove.size(); i++) {
            String data = validmove.get(i);
            int intdata = Integer.parseInt(data);
            int x=intdata/10;
            int y=intdata%10;
            checkplayer[x][y]=canmove;
        }
    }
    public static void clearvalidmovearray() {
        //clear the arraylist
        validmove.clear();
    }
    public void castling(int x, int y){
        //castling
        //check which player
        if(checkplayer[x][y]==player1){
            //check if the king is in the right position
            if (checkplayer[x][4] == player1 && board[x][4] == King) {
                //check if rook is in left hand sided
                if((x==0&&y==0)) {
                    //check if there are any pieces between king and rook
                    if(checkplayer[x][1] == noone&&checkplayer[x][2] == noone&&checkplayer[x][3] == noone){
                        if(whitecastling==false){
                            addpossiblemoves(x,4);
                        }
                    }
                }else
                    //check if rook is in left hand sided
                    if (x==0&&y==7){
                        if(checkplayer[x][6] == noone&&checkplayer[x][5] == noone){
                            if(whitecastling==false){
                                addpossiblemoves(x,4);
                            }
                        }
                    }
            }
        }else if(checkplayer[x][y]==player2){
            //check if the king is in the right position
            if (checkplayer[x][4] == player2 && board[x][4] == King) {
                //check if rook is in left hand sided
                if((x==7&&y==0)) {
                    //check if there are any pieces between king and rook
                    if(checkplayer[x][1] == noone&&checkplayer[x][2] == noone&&checkplayer[x][3] == noone){
                        if(blackcastling==false){
                            addpossiblemoves(x,4);
                        }
                    }
                }else
                    //check if rook is in left hand sided
                    if (x==7&&y==7){
                        if(checkplayer[x][6] == noone&&checkplayer[x][5] == noone){
                            if(blackcastling==false){
                                addpossiblemoves(x,4);
                            }
                        }
                    }
            }
        }
    }

}
