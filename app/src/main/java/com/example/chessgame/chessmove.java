package com.example.chessgame;

import android.util.Log;

import java.util.ArrayList;

public class chessmove {
       static chessboard chessboardclass = new chessboard();
      public static ArrayList<String> validmove = new ArrayList<String>();
      static int mymove =1;
      static int empty =0;
      static int opponentmove =2;
      static int canmove =3;

    public void bishopmove(int x,int y) {
        changeopponent(x,y);
        int  columnex,columney;
        for(columnex=x+1,columney=y+1;columnex<=7&&columney<=7;columnex++,columney++) {
                if (chessboardclass.checkplayer[columnex][columney] == chessboardclass.noone) {
                    addpossiblemoves(columnex,columney);
                } else if (chessboardclass.checkplayer[columnex][columney] == opponentmove) {
                    addpossiblemoves(columnex,columney);
                    break;
                } else {
                    break;
                }
        }
        for(columnex=x+1,columney=y-1;columnex<=7&&columney>=0;columnex++,columney--) {

                if (chessboardclass.checkplayer[columnex][columney] == chessboardclass.noone) {
                    addpossiblemoves(columnex,columney);
                } else if (chessboardclass.checkplayer[columnex][columney] == opponentmove) {
                    addpossiblemoves(columnex,columney);
                    break;
                } else {
                    break;
                }

        }
        for(columnex=x-1,columney=y+1;columnex>=0&&columney<=7;columnex--,columney++) {

                if (chessboardclass.checkplayer[columnex][columney] == chessboardclass.noone) {
                    addpossiblemoves(columnex,columney);
                } else if (chessboardclass.checkplayer[columnex][columney] == opponentmove) {
                    addpossiblemoves(columnex,columney);
                    break;
                } else {
                    break;
                }

        }
        for(columnex=x-1,columney=y-1;columnex>=0&&columney>=0;columnex--,columney--) {

                if (chessboardclass.checkplayer[columnex][columney] == chessboardclass.noone) {
                    addpossiblemoves(columnex,columney);
                } else if (chessboardclass.checkplayer[columnex][columney] == opponentmove) {
                    addpossiblemoves(columnex,columney);
                    break;
                } else {
                    break;
                }
        }
    }
    public void queenmove(int x,int y) {
        changeopponent(x,y);
        for(int columnex=x+1;columnex<=7;columnex++) {
            if(chessboardclass.checkplayer[columnex][y]==chessboardclass.noone){
                addpossiblemoves(columnex,y);
            } else if (chessboardclass.checkplayer[columnex][y]==opponentmove) {
                addpossiblemoves(columnex,y);
                break;
            } else{
                break;
            }
        }
        for(int columnex=x-1;columnex>=0;columnex--) {
            if(chessboardclass.checkplayer[columnex][y]==chessboardclass.noone){
                addpossiblemoves(columnex,y);
            } else if (chessboardclass.checkplayer[columnex][y]==opponentmove) {
                addpossiblemoves(columnex,y);
                break;
            } else{
                break;
            }
        }
        for(int columney=y+1;columney<=7;columney++) {
            if(chessboardclass.checkplayer[x][columney]==chessboardclass.noone){
                addpossiblemoves(x,columney);
            } else if (chessboardclass.checkplayer[x][columney]==opponentmove) {
                addpossiblemoves(x,columney);
                break;
            } else{
                break;
            }
        }
        for(int columney=y-1;columney>=0;columney--) {
            if(chessboardclass.checkplayer[x][columney]==chessboardclass.noone){
                addpossiblemoves(x,columney);
            } else if (chessboardclass.checkplayer[x][columney]==opponentmove) {
                addpossiblemoves(x,columney);
                break;
            } else{
                break;
            }
        }
        for(int columnex=x+1,columney=y+1;columnex<=7&&columney<=7;columnex++,columney++) {
            if (chessboardclass.checkplayer[columnex][columney] == chessboardclass.noone) {
                addpossiblemoves(columnex,columney);
            } else if (chessboardclass.checkplayer[columnex][columney] == opponentmove) {
                addpossiblemoves(columnex,columney);
                break;
            } else {
                break;
            }
        }
        for(int columnex=x+1,columney=y-1;columnex<=7&&columney>=0;columnex++,columney--) {
            if (chessboardclass.checkplayer[columnex][columney] == chessboardclass.noone) {
                addpossiblemoves(columnex,columney);
            } else if (chessboardclass.checkplayer[columnex][columney] == opponentmove) {
                addpossiblemoves(columnex,columney);
                break;
            } else {
                break;
            }

        }
        for(int columnex=x-1,columney=y+1;columnex>=0&&columney<=7;columnex--,columney++) {

            if (chessboardclass.checkplayer[columnex][columney] == chessboardclass.noone) {
                addpossiblemoves(columnex,columney);
            } else if (chessboardclass.checkplayer[columnex][columney] == opponentmove) {
                addpossiblemoves(columnex,columney);
                break;
            } else {
                break;
            }

        }
        for(int columnex=x-1,columney=y-1;columnex>=0&&columney>=0;columnex--,columney--) {

            if (chessboardclass.checkplayer[columnex][columney] == chessboardclass.noone) {
                addpossiblemoves(columnex,columney);
            } else if (chessboardclass.checkplayer[columnex][columney] == opponentmove) {
                addpossiblemoves(columnex,columney);
                break;
            } else {
                break;
            }
        }
    }
    public void pawnmove(int x,int y) {
             changeopponent(x,y);
            if (chessboardclass.checkplayer[x][y] == chessboardclass.player1) {
                //the chess is opponent's side
                if (x == 1) {
                    //to check if the pawn are already moved
                    if(chessboardclass.checkplayer[x+1][y]==chessboardclass.noone) {
                        addpossiblemoves(x+1,y);
                        if(chessboardclass.checkplayer[x+2][y]==chessboardclass.noone) {
                            addpossiblemoves(x+2,y);
                        }
                    }
                }else if(chessboardclass.checkplayer[x+1][y]==chessboardclass.noone) {
                    addpossiblemoves(x+1,y);
                }
                if(y!=7){
                    if(chessboardclass.checkplayer[x+1][y+1]==opponentmove){
                        //pawn eat opponent chess
                        addpossiblemoves(x+1,y+1);
                    }
                }
                if(y!=0) {
                    if (chessboardclass.checkplayer[x + 1][y - 1] == opponentmove) {
                        //pawn eat opponent chess
                        addpossiblemoves(x+1,y-1);
                    }
                }
            } else if (chessboardclass.checkplayer[x][y] == chessboardclass.player2) {
                //the chess is opponent's side
                if (x == 6) {
                    //to check if the pawn are already moved
                     if(chessboardclass.checkplayer[x-1][y]==chessboardclass.noone) {
                         addpossiblemoves(x-1,y);
                         if(chessboardclass.checkplayer[x-2][y]==chessboardclass.noone) {
                             addpossiblemoves(x-2,y);
                         }
                     }
                }else  if(chessboardclass.checkplayer[x-1][y]==chessboardclass.noone) {
                    addpossiblemoves(x-1,y);
                }
                if(y!=7) {
                    if (chessboardclass.checkplayer[x - 1][y + 1] == opponentmove) {
                        //pawn eat opponent chess
                        addpossiblemoves(x-1,y+1);
                    }
                }
                if(y!=0) {
                    if (chessboardclass.checkplayer[x - 1][y - 1] == opponentmove) {
                        //pawn eat opponent chess
                        addpossiblemoves(x-1,y-1);
                    }
                }
            }
        }
    public void rookmove(int x,int y) {
        changeopponent(x,y);
        for(int columnex=x+1;columnex<=7;columnex++) {
            if(chessboardclass.checkplayer[columnex][y]==chessboardclass.noone){
                addpossiblemoves(columnex,y);
            } else if (chessboardclass.checkplayer[columnex][y]==opponentmove) {
                addpossiblemoves(columnex,y);
                break;
            } else{
                break;
            }
        }
        for(int columnex=x-1;columnex>=0;columnex--) {
            if(chessboardclass.checkplayer[columnex][y]==chessboardclass.noone){
                addpossiblemoves(columnex,y);
            } else if (chessboardclass.checkplayer[columnex][y]==opponentmove) {
                addpossiblemoves(columnex,y);
                break;
            } else{
                break;
            }
        }
        for(int columney=y+1;columney<=7;columney++) {
            if(chessboardclass.checkplayer[x][columney]==chessboardclass.noone){
                addpossiblemoves(x,columney);
            } else if (chessboardclass.checkplayer[x][columney]==opponentmove) {
                addpossiblemoves(x,columney);
                break;
            } else{
                break;
            }
        }
        for(int columney=y-1;columney>=0;columney--) {
            if(chessboardclass.checkplayer[x][columney]==chessboardclass.noone){
                addpossiblemoves(x,columney);
            } else if (chessboardclass.checkplayer[x][columney]==opponentmove) {
                addpossiblemoves(x,columney);
                break;
            } else{
                break;
            }
        }
        castling(x,y);
    }
    public void knightmove(int x,int y) {
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
    }
    public void kingmove(int x,int y) {
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
            if(chessboardclass.checkplayer[x][y]==chessboardclass.checkplayer[x1][y1]){
                validmove.remove(i);
                i=i-1;
            }
        }
    }
    public void changeopponent(int x ,int y){
        //check which player moves
        if(chessboardclass.checkplayer[x][y]==chessboardclass.player1){
            mymove =1;
            opponentmove =2;
        } else if(chessboardclass.checkplayer[x][y]==chessboardclass.player2){
            mymove =2;
            opponentmove =1;
        }
    }
    public static void putarraydatatobaord() {
        //read the arraylist
        for (int i = 0; i < validmove.size(); i++) {
            String data = validmove.get(i);
            int intdata = Integer.parseInt(data);
            int x=intdata/10;
            int y=intdata%10;
            chessboardclass.checkplayerclone[x][y]=canmove;
        }
    }
    public static void clearvalidmovearray() {
        //clear the arraylist
        validmove.clear();
    }
    public void castling(int x, int y){
        //castling
        //check which player
        if(chessboardclass.checkplayer[x][y]==chessboardclass.player1){
            //check if the king is in the right position
            if (chessboardclass.checkplayer[x][4] == chessboardclass.player1 && chessboardclass.board[x][4] == chessboardclass.King) {
                //check if rook is in left hand sided
                if((x==0&&y==0)) {
                    //check if there are any pieces between king and rook
                    if(chessboardclass.checkplayer[x][1] == chessboardclass.noone&&chessboardclass.checkplayer[x][2] == chessboardclass.noone&&chessboardclass.checkplayer[x][3] == chessboardclass.noone){
                        if(chessboardclass.whitecastling==false){
                            addpossiblemoves(x,4);
                        }
                    }
                }else
                    //check if rook is in left hand sided
                    if (x==0&&y==7){
                        if(chessboardclass.checkplayer[x][6] == chessboardclass.noone&&chessboardclass.checkplayer[x][5] == chessboardclass.noone){
                            if(chessboardclass.whitecastling==false){
                                addpossiblemoves(x,4);
                            }
                        }
                    }
            }
        }else if(chessboardclass.checkplayer[x][y]==chessboardclass.player2){
            //check if the king is in the right position
            if (chessboardclass.checkplayer[x][4] == chessboardclass.player2 && chessboardclass.board[x][4] == chessboardclass.King) {
                //check if rook is in left hand sided
                if((x==7&&y==0)) {
                    //check if there are any pieces between king and rook
                    if(chessboardclass.checkplayer[x][1] == chessboardclass.noone&&chessboardclass.checkplayer[x][2] == chessboardclass.noone&&chessboardclass.checkplayer[x][3] == chessboardclass.noone){
                        if(chessboardclass.blackcastling==false){
                            addpossiblemoves(x,4);
                        }
                    }
                }else
                    //check if rook is in left hand sided
                    if (x==7&&y==7){
                        if(chessboardclass.checkplayer[x][6] == chessboardclass.noone&&chessboardclass.checkplayer[x][5] == chessboardclass.noone){
                            if(chessboardclass.blackcastling==false){
                                addpossiblemoves(x,4);
                            }
                        }
                    }
            }
        }
    }

}
