package com.example.chessgame;

import android.util.Log;

import java.util.ArrayList;

public class chessmove {
    public static chessboard chessboardclass = new chessboard();
    public static ArrayList<String> validmove = new ArrayList<String>();


    public static void knightmove(int x,int y) {
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

    public static int checkwhiteblack(int x, int y) {
        int player = chessboardclass.checkplayer[x][y];
        return player;
    }
    public static void checkvalid(int player) {
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
    public static boolean checkalreadyhavepiece(int x, int y,int player) {
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
