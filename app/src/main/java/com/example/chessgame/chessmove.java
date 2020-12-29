package com.example.chessgame;

import android.util.Log;

import java.util.ArrayList;

public class chessmove {
     static chessboard chessboardclass = new chessboard();
     static ArrayList<String> validmove = new ArrayList<String>();
     static int mymove =1;
     static int opponentmove =2;
    public void bishopmove(int x,int y) {
        int  columnex,columney;
        for(columnex=x;columnex<=7;columnex++) {
            for(columney=y;columney<=7;columney++) {

                Log.d("added", "bishop placeX :"+x );
                Log.d("added", "bishop placeY :"+y );
                if (chessboardclass.checkplayer[columnex][columney] == mymove) {
                    String place = Integer.toString(columnex) + Integer.toString(columney);
                    validmove.add(place);
                } else if (chessboardclass.checkplayer[columnex][columney] == opponentmove) {
                    String place = Integer.toString(columnex) + Integer.toString(columney);
                    validmove.add(place);
                    break;
                } else {
                    break;
                }
            }
        }
        for(columnex=x;columnex<=7;columnex++) {
            for(columney=y;columney>=0;columney--) {
                if (chessboardclass.checkplayer[columnex][columney] == mymove) {
                    String place = Integer.toString(columnex) + Integer.toString(columney);
                    validmove.add(place);
                } else if (chessboardclass.checkplayer[columnex][columney] == opponentmove) {
                    String place = Integer.toString(columnex) + Integer.toString(columney);
                    validmove.add(place);
                    break;
                } else {
                    break;
                }
            }
        }
        for(columnex=x;columnex>=0;columnex--) {
            for(columney=y;columney<=7;columney++) {
                if (chessboardclass.checkplayer[columnex][columney] == mymove) {
                    String place = Integer.toString(columnex) + Integer.toString(columney);
                    validmove.add(place);
                } else if (chessboardclass.checkplayer[columnex][columney] == opponentmove) {
                    String place = Integer.toString(columnex) + Integer.toString(columney);
                    validmove.add(place);
                    break;
                } else {
                    break;
                }
            }
        }
        for(columnex=x;columnex>=0;columnex--) {
            for(columney=y;columney>=0;columney--) {
                if (chessboardclass.checkplayer[columnex][columney] == mymove) {
                    String place = Integer.toString(columnex) + Integer.toString(columney);
                    validmove.add(place);
                } else if (chessboardclass.checkplayer[columnex][columney] == opponentmove) {
                    String place = Integer.toString(columnex) + Integer.toString(columney);
                    validmove.add(place);
                    break;
                } else {
                    break;
                }
            }
        }
        int player = checkwhiteblack(x,y);
        checkvalid(player);
        outputvalidmove();
        clearvalidmovearray();
    }

    public  void rookmove(int x,int y) {
        Log.d("calculating", "rookmove" );
        int columnex,columney;
        for(columnex=x;columnex<=7;columnex++) {
            if(chessboardclass.checkplayer[columnex][y]==mymove){
                String place = Integer.toString(columnex)+Integer.toString(y);
                validmove.add(place);
                Log.d("added", "rookmove" );
            }else if (chessboardclass.checkplayer[columnex][y]==opponentmove) {
                String place = Integer.toString(columnex)+Integer.toString(y);
                validmove.add(place);
                Log.d("added", "rookmove" );
                break;
            } else {
                break;
            }
        }
        for(columnex=x;columnex>=0;columnex--) {
            if(chessboardclass.checkplayer[columnex][y]==mymove){
                String place = Integer.toString(columnex)+Integer.toString(y);
                validmove.add(place);
                Log.d("added", "rookmove" );
            }else if (chessboardclass.checkplayer[columnex][y]==opponentmove) {
                String place = Integer.toString(columnex)+Integer.toString(y);
                validmove.add(place);
                Log.d("added", "rookmove" );
                break;
            } else {
                break;
            }
        }
        for(columney=x;columney<=7;columney++) {
            if(chessboardclass.checkplayer[x][columney]==mymove){
                String place = Integer.toString(x)+Integer.toString(columney);
                validmove.add(place);
                Log.d("added", "rookmove" );
            }else if (chessboardclass.checkplayer[columnex][y]==opponentmove) {
                String place = Integer.toString(x)+Integer.toString(columney);
                validmove.add(place);
                Log.d("added", "rookmove" );
                break;
            } else {
                break;
            }
        }
        for(columney=x;columney>=0;columney--) {
            if(chessboardclass.checkplayer[x][columney]==mymove){
                String place = Integer.toString(x)+Integer.toString(columney);
                validmove.add(place);
                Log.d("added", "rookmove" );
            }else if (chessboardclass.checkplayer[columnex][y]==opponentmove) {
                String place = Integer.toString(x)+Integer.toString(columney);
                validmove.add(place);
                Log.d("added", "rookmove" );
                break;
            } else {
                break;
            }
        }
        outputvalidmove();
        clearvalidmovearray();
    }



    public  void knightmove(int x,int y) {
        for(int x1=0;x1<=7;x1++) {
            for(int y1=0;y1<=7;y1++) {
                if(x1-x==2||x-x1==2) {
                    if(y1-y==1||y-y1==1) {
                        String place = Integer.toString(x1)+Integer.toString(y1);
                        validmove.add(place);
                    }
                }
                if(x1-x==1||x-x1==1) {
                    if(y1-y==2||y-y1==2) {
                        String place = Integer.toString(x1)+Integer.toString(y1);
                        validmove.add(place);
                    }
                }
            }
        }
        int player = checkwhiteblack(x,y);
        checkvalid(player);
        outputvalidmove();
        clearvalidmovearray();
    }

    public  int checkwhiteblack(int x, int y) {
        int player = chessboardclass.checkplayer[x][y];
        return player;
    }
    public  void checkvalid(int player) {
        for (int i = 0; i < validmove.size(); i++) {
            String data = validmove.get(i);
            int intdata = Integer.parseInt(data);
            int x=intdata/10;
            int y=intdata%10;
            if(checkalreadyhavepiece(x,y,player)==true){
                validmove.remove(i);
            }
        }
    }
    public  boolean checkalreadyhavepiece(int x, int y,int player) {
        int checkplayer =chessboardclass.checkplayer[x][y];
        if(player==checkplayer){
            return true;
        }return false;
    }
    public static void outputvalidmove() {
        for (int i = 0; i < validmove.size(); i++) {
            Log.d("congrat", "possible moves :" + validmove.get(i));

        }
    }

    public static void clearvalidmovearray() {
        validmove.clear();

    }



}
