package com.example.chessgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class vscomputerchessgame extends AppCompatActivity implements View.OnClickListener {

    Button startgame;
    public static TextView turn;
    public static String Whiteturn="White Turn";
    public static String Blackturn="Black Turn";
    public static String Whitewin="White Win";
    public static String Blackwin="Black Win";

    static chessboard chessboardclass = new chessboard();
    chessmove chessmove = new chessmove();
    private static final int[] idarray = {R.id.x1y1,R.id.x1y2,R.id.x1y3,R.id.x1y4,R.id.x1y5,R.id.x1y6,R.id.x1y7,R.id.x1y8,R.id.x2y1,R.id.x2y2,R.id.x2y3,R.id.x2y4,R.id.x2y5,R.id.x2y6,R.id.x2y7,R.id.x2y8,R.id.x3y1,R.id.x3y2,R.id.x3y3,R.id.x3y4,R.id.x3y5,R.id.x3y6,R.id.x3y7,R.id.x3y8,R.id.x4y1,R.id.x4y2,R.id.x4y3,R.id.x4y4,R.id.x4y5,R.id.x4y6,R.id.x4y7,R.id.x4y8,R.id.x5y1,R.id.x5y2,R.id.x5y3,R.id.x5y4,R.id.x5y5,R.id.x5y6,R.id.x5y7,R.id.x5y8,R.id.x6y1,R.id.x6y2,R.id.x6y3,R.id.x6y4,R.id.x6y5,R.id.x6y6,R.id.x6y7,R.id.x6y8,R.id.x7y1,R.id.x7y2,R.id.x7y3,R.id.x7y4,R.id.x7y5,R.id.x7y6,R.id.x7y7,R.id.x7y8,R.id.x8y1,R.id.x8y2,R.id.x8y3,R.id.x8y4,R.id.x8y5,R.id.x8y6,R.id.x8y7,R.id.x8y8,};
    private static final int[] coordinate ={11,12,13,14,15,16,17,18,21,22,23,24,25,26,27,28,31,32,33,34,35,36,37,38,41,42,43,44,45,46,47,48,51,52,53,54,55,56,57,58,61,62,63,64,65,66,67,68,71,72,73,74,75,76,77,78,81,82,83,84,85,86,87,88,};
    private static ImageView[] imageButtonarray = new ImageView[idarray.length];
    static ArrayList<String> validmove = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vscomputerchessgame);

        startgame=findViewById(R.id.startgame);
        turn=findViewById(R.id.turn);
        for(int i =0;i<idarray.length;i++){
            imageButtonarray[i] = findViewById(idarray[i]);
            imageButtonarray[i].setOnClickListener(this);
        }


    }


    @Override
    public void onClick(View v) {
        for(int i =0;i<idarray.length;i++){
            if(v.getId()==idarray[i]){
                int clickcoordinate = coordinate[i];

                int x=clickcoordinate/10;
                int y=clickcoordinate%10;
                x=x-1;
                y=y-1;

                if(chessboardclass.checkplayerclone[x][y] == chessboardclass.canmove){
                    changechessposition(x,y);
                    removepossiblemove();
                }else {
                    removepossiblemove();
                    chessboardclass.input(x, y);
                }
                setfigure();
            }
        }
        switch (v.getId()) {
            case R.id.startgame:
                chessboardclass.startgame();
                setfigure();
                break;
        }
    }
    public void changechessposition(int x, int y){
        //variable of last clicked pieces
        int xvalue=chessboardclass.lastmovex;
        int yvalue=chessboardclass.lastmovey;
        int lastplayer=chessboardclass.lastpressedplayer;
        String lastmove=chessboardclass.lastmove;
        if(chessboardclass.board[x][y]==chessboardclass.King&&chessboardclass.board[xvalue][yvalue]==chessboardclass.Rook){
            if(chessboardclass.checkplayer[x][y]==lastplayer&&chessboardclass.checkplayer[xvalue][yvalue]==lastplayer) {
                if(lastplayer==chessboardclass.player1) {
                    if (chessboardclass.whitecastling == false) {
                        if ((y == 0 && yvalue == 4) || (y == 4 && yvalue == 0)) {
                            //put the pieces to the new place
                            chessboardclass.checkplayer[x][2] = lastplayer;
                            chessboardclass.board[x][2] = chessboardclass.King;
                            chessboardclass.checkplayer[x][3] = lastplayer;
                            chessboardclass.board[x][3] = chessboardclass.Rook;
                            //remove the last pieces
                            chessboardclass.checkplayer[xvalue][yvalue] = chessboardclass.noone;
                            chessboardclass.board[xvalue][yvalue] = chessboardclass.empty;
                            chessboardclass.checkplayer[x][y] = chessboardclass.noone;
                            chessboardclass.board[x][y] = chessboardclass.empty;
                            chessboardclass.whitecastling = true;
                        } else if ((y == 7 && yvalue == 4) || (y == 4 && yvalue == 7)) {
                            //put the pieces to the new place
                            chessboardclass.checkplayer[x][6] = lastplayer;
                            chessboardclass.board[x][6] = chessboardclass.King;
                            chessboardclass.checkplayer[x][5] = lastplayer;
                            chessboardclass.board[x][5] = chessboardclass.Rook;
                            //remove the last pieces
                            chessboardclass.checkplayer[xvalue][yvalue] = chessboardclass.noone;
                            chessboardclass.board[xvalue][yvalue] = chessboardclass.empty;
                            chessboardclass.checkplayer[x][y] = chessboardclass.noone;
                            chessboardclass.board[x][y] = chessboardclass.empty;
                            chessboardclass.whitecastling = true;
                        }
                    }
                }else if(lastplayer==chessboardclass.player2) {
                    if (chessboardclass.blackcastling == false) {
                        if ((y == 0 && yvalue == 4) || (y == 4 && yvalue == 0)) {
                            //put the pieces to the new place
                            chessboardclass.checkplayer[x][2] = lastplayer;
                            chessboardclass.board[x][2] = chessboardclass.King;
                            chessboardclass.checkplayer[x][3] = lastplayer;
                            chessboardclass.board[x][3] = chessboardclass.Rook;
                            //remove the last pieces
                            chessboardclass.checkplayer[xvalue][yvalue] = chessboardclass.noone;
                            chessboardclass.board[xvalue][yvalue] = chessboardclass.empty;
                            chessboardclass.checkplayer[x][y] = chessboardclass.noone;
                            chessboardclass.board[x][y] = chessboardclass.empty;
                            chessboardclass.blackcastling = true;
                        } else if ((y == 7 && yvalue == 4) || (y == 4 && yvalue == 7)) {
                            //put the pieces to the new place
                            chessboardclass.checkplayer[x][6] = lastplayer;
                            chessboardclass.board[x][6] = chessboardclass.King;
                            chessboardclass.checkplayer[x][5] = lastplayer;
                            chessboardclass.board[x][5] = chessboardclass.Rook;
                            //remove the last pieces
                            chessboardclass.checkplayer[xvalue][yvalue] = chessboardclass.noone;
                            chessboardclass.board[xvalue][yvalue] = chessboardclass.empty;
                            chessboardclass.checkplayer[x][y] = chessboardclass.noone;
                            chessboardclass.board[x][y] = chessboardclass.empty;
                            chessboardclass.blackcastling = true;
                        }
                    }
                }
            }
        }else {
            //put the pieces to the new place
            chessboardclass.checkplayer[x][y] = lastplayer;
            chessboardclass.board[x][y] = lastmove;
            //remove the last pieces
            chessboardclass.checkplayer[xvalue][yvalue] = chessboardclass.noone;
            chessboardclass.board[xvalue][yvalue] = chessboardclass.empty;
        }
            switchplayer();
            chessboardclass.checkwin();
    }
    public void switchplayer(){
        if(chessboardclass.currentplayer==chessboardclass.player1){
            chessboardclass.currentplayer=chessboardclass.player2;
            turn.setText(Blackturn);
        }else if(chessboardclass.currentplayer==chessboardclass.player2){
            chessboardclass.currentplayer=chessboardclass.player1;
            turn.setText(Whiteturn);
        }
    }


    public void removepossiblemove(){

        for(int i =0;i<idarray.length;i++) {
            int intdata = coordinate[i];
            int x = intdata / 10;
            int y = intdata % 10;
            x = x - 1;
            y = y - 1;
            chessboardclass.checkplayerclone[x][y] = chessboardclass.checkplayer[x][y];

        }
    }
    public void setfigure(){
        for(int i =0;i<idarray.length;i++){

            int intdata = coordinate[i];
            int x=intdata/10;
            int y=intdata%10;
            x=x-1;
            y=y-1;
            if(chessboardclass.checkplayer[x][y]==chessboardclass.noone){
                imageButtonarray[i].setVisibility(View.INVISIBLE);
            } else if(chessboardclass.board[x][y]==chessboardclass.Pawn&&chessboardclass.checkplayer[x][y]==chessboardclass.player1){
                addimage(i,R.drawable.whitepawn);
            }else if(chessboardclass.board[x][y]==chessboardclass.Rook&&chessboardclass.checkplayer[x][y]==chessboardclass.player1){
                addimage(i,R.drawable.whiterook);
            }else if(chessboardclass.board[x][y]==chessboardclass.Knight&&chessboardclass.checkplayer[x][y]==chessboardclass.player1){
                addimage(i,R.drawable.whiteknight);
            }else if(chessboardclass.board[x][y]==chessboardclass.Bishop&&chessboardclass.checkplayer[x][y]==chessboardclass.player1){
                addimage(i,R.drawable.whitebishop);
            }else if(chessboardclass.board[x][y]==chessboardclass.Queen&&chessboardclass.checkplayer[x][y]==chessboardclass.player1){
                addimage(i,R.drawable.whitequeen);
            }else if(chessboardclass.board[x][y]==chessboardclass.King&&chessboardclass.checkplayer[x][y]==chessboardclass.player1){
                addimage(i,R.drawable.whiteking);
            }else if(chessboardclass.board[x][y]==chessboardclass.Pawn&&chessboardclass.checkplayer[x][y]==chessboardclass.player2){
                addimage(i,R.drawable.blackpawn);
            }else if(chessboardclass.board[x][y]==chessboardclass.Rook&&chessboardclass.checkplayer[x][y]==chessboardclass.player2){
                addimage(i,R.drawable.blackrook);
            }else if(chessboardclass.board[x][y]==chessboardclass.Knight&&chessboardclass.checkplayer[x][y]==chessboardclass.player2){
                addimage(i,R.drawable.blackknight);
            }else if(chessboardclass.board[x][y]==chessboardclass.Bishop&&chessboardclass.checkplayer[x][y]==chessboardclass.player2){
                addimage(i,R.drawable.blackbishop);
            }else if(chessboardclass.board[x][y]==chessboardclass.Queen&&chessboardclass.checkplayer[x][y]==chessboardclass.player2){
                addimage(i,R.drawable.blackqueen);
            }else if(chessboardclass.board[x][y]==chessboardclass.King&&chessboardclass.checkplayer[x][y]==chessboardclass.player2){
                addimage(i,R.drawable.blackking);
            }
            if(chessboardclass.checkplayerclone[x][y]==chessboardclass.canmove){
                imageButtonarray[i].setVisibility(View.VISIBLE);
                imageButtonarray[i].setImageResource(R.drawable.point);
            }
        }
    }
    public void addimage(int i ,int x ){
        imageButtonarray[i].setVisibility(View.VISIBLE);
        imageButtonarray[i].setBackgroundResource(x);
        imageButtonarray[i].setImageResource(0);
    }

}
