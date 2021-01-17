package com.example.chessgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class vscomputerchessgame extends AppCompatActivity implements View.OnClickListener {

    Button startgame;
    TextView whiteplayer;
    TextView blackplayer;

    Boolean gamestarted;
    String white;
    String black;

    public static TextView turn;
    public static String Whiteturn="White Turn";
    public static String Blackturn="Black Turn";
    public static String Whitewin="White Win";
    public static String Blackwin="Black Win";

    static Minimax minimaxclass=new Minimax();
    static chessboard chessboardclass = new chessboard();

    private static final int[] idarray = {R.id.x1y1,R.id.x1y2,R.id.x1y3,R.id.x1y4,R.id.x1y5,R.id.x1y6,R.id.x1y7,R.id.x1y8,R.id.x2y1,R.id.x2y2,R.id.x2y3,R.id.x2y4,R.id.x2y5,R.id.x2y6,R.id.x2y7,R.id.x2y8,R.id.x3y1,R.id.x3y2,R.id.x3y3,R.id.x3y4,R.id.x3y5,R.id.x3y6,R.id.x3y7,R.id.x3y8,R.id.x4y1,R.id.x4y2,R.id.x4y3,R.id.x4y4,R.id.x4y5,R.id.x4y6,R.id.x4y7,R.id.x4y8,R.id.x5y1,R.id.x5y2,R.id.x5y3,R.id.x5y4,R.id.x5y5,R.id.x5y6,R.id.x5y7,R.id.x5y8,R.id.x6y1,R.id.x6y2,R.id.x6y3,R.id.x6y4,R.id.x6y5,R.id.x6y6,R.id.x6y7,R.id.x6y8,R.id.x7y1,R.id.x7y2,R.id.x7y3,R.id.x7y4,R.id.x7y5,R.id.x7y6,R.id.x7y7,R.id.x7y8,R.id.x8y1,R.id.x8y2,R.id.x8y3,R.id.x8y4,R.id.x8y5,R.id.x8y6,R.id.x8y7,R.id.x8y8,};
    private static final int[] coordinate ={11,12,13,14,15,16,17,18,21,22,23,24,25,26,27,28,31,32,33,34,35,36,37,38,41,42,43,44,45,46,47,48,51,52,53,54,55,56,57,58,61,62,63,64,65,66,67,68,71,72,73,74,75,76,77,78,81,82,83,84,85,86,87,88,};
    private static ImageView[] imageButtonarray = new ImageView[idarray.length];
    public static String [][] board ; // to store the piece
    public static int[][] checkplayer ; //to check who owned the pieces

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
        whiteplayer=findViewById(R.id.white);
        blackplayer=findViewById(R.id.black);
        white = getIntent().getStringExtra("white");
        black = getIntent().getStringExtra("black");
        whiteplayer.setText("white: "+white);
        blackplayer.setText("black: "+black);
        addchessplayer();
        addchesspiece();
        gamestarted=false;
    }
    @Override
    public void onClick(View v) {
        //if user press other buttons
        switch (v.getId()) {
            case R.id.startgame:
                chessboardclass.startgame(white,black,board,checkplayer,checkplayer);
                turn.setText(vscomputerchessgame.Whiteturn);
                if(white.equals(chessboardclass.computer)){
                    minimaxclass.computermove(chessboardclass.player1,chessboardclass.board ,chessboardclass.checkplayerclone);
                    switchplayer();
                }
                gamestarted=true;
                break;
        }
        // if user click the the boardwithout clicking startgame
        if(gamestarted==false){
            chessboardclass.startgame(white,black,board,checkplayer,checkplayer);
            turn.setText(vscomputerchessgame.Whiteturn);
            if(white.equals(chessboardclass.computer)){
                minimaxclass.computermove(chessboardclass.player1,chessboardclass.board ,chessboardclass.checkplayerclone);
                switchplayer();
            }
            gamestarted=true;
        }
        // if user click the pieces of chess
        for(int i =0;i<idarray.length;i++){
            if(v.getId()==idarray[i]){
                int clickcoordinate = coordinate[i];
                int x=clickcoordinate/10;
                int y=clickcoordinate%10;
                x=x-1;
                y=y-1;

                if(chessboardclass.checkplayerclone[x][y] == chessboardclass.canmove){
                    //if user want to move the piece
                    chessboardclass.changechessposition(x,y);
                    chessboardclass.removepossiblemoveimage();
                    switchplayer();

                }else {
                    //if user want to select the piece
                    chessboardclass.removepossiblemoveimage();
                    chessboardclass.input(x, y);
                }
                setfigure();
            }
        }
    }
    public void switchplayer(){
        if(checkifendgame()==false) {
            setfigure();
            setboardswitchplayer();
            if (chessboardclass.currentplayer == chessboardclass.player1) {
                //player2
                chessboardclass.currentplayer = chessboardclass.player2;
                if (chessboardclass.blackplayer.equals(chessboardclass.computer)) {
                    minimaxclass.computermove(chessboardclass.player2, chessboardclass.board, chessboardclass.checkplayerclone);
                    setfigure();
                    setboardswitchplayer();
                    if (checkifendgame() == false) {
                        switchplayer();
                    }
                }
            } else if (chessboardclass.currentplayer == chessboardclass.player2) {
                //player1f
                chessboardclass.currentplayer = chessboardclass.player1;
                if (chessboardclass.whiteplayer.equals(chessboardclass.computer)) {
                    minimaxclass.computermove(chessboardclass.player1, chessboardclass.board, chessboardclass.checkplayerclone);
                    setfigure();
                    setboardswitchplayer();
                    if (checkifendgame() == false) {
                        switchplayer();
                    }
                }
            }
        }
    }

    public static void setfigure(){
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
    public static void addimage(int i ,int x ){
        imageButtonarray[i].setVisibility(View.VISIBLE);
        imageButtonarray[i].setBackgroundResource(x);
        imageButtonarray[i].setImageResource(0);
    }
    public void setboardswitchplayer(){
        if(chessboardclass.currentplayer==chessboardclass.player1){
            turn.setText(Whiteturn);
        }else if(chessboardclass.currentplayer==chessboardclass.player2){
            turn.setText(Blackturn);
        }
    }
    public static boolean checkifendgame(){
        if(chessboardclass.checkwin(chessboardclass.player1)==true){
            turn.setText(Blackwin);
            return true;
        }
        if(chessboardclass.checkwin(chessboardclass.player2)==true){
            turn.setText(Whitewin);
            return true;
        }
        return false;
    }

    public void addchessplayer(){
        checkplayer = new int[chessboardclass.chessboardxvalue][chessboardclass.chessboardyvalue];
        for(int x=0;x<8;x++){
            for(int y=0;y<8;y++){
                checkplayer[x][y]=0;
            }
        }
        for(int y=0;y<8;y++){
            checkplayer[0][y]=1;
            checkplayer[1][y]=1;
            checkplayer[6][y]=2;
            checkplayer[7][y]=2;
        }
    }
    public void addchesspiece(){
        board = new String[chessboardclass.chessboardxvalue][chessboardclass.chessboardyvalue];
        for(int x=0;x<8;x++){
            for(int y=0;y<8;y++){
                board [x][y]= chessboardclass.empty;
            }
        }
        for(int addtimes=0;addtimes<=10;addtimes=addtimes+7) {
            board [addtimes][0]= chessboardclass.Rook;
            board [addtimes][1]= chessboardclass.Knight;
            board [addtimes][2]= chessboardclass.Bishop;
            board [addtimes][3]= chessboardclass.Queen;
            board [addtimes][4]= chessboardclass.King;
            board [addtimes][5]= chessboardclass.Bishop;
            board [addtimes][6]= chessboardclass.Knight;
            board [addtimes][7]= chessboardclass.Rook;
        }
        for (int y = 0; y < chessboardclass.chessboardyvalue; y++) {
            board [1][y]= chessboardclass.Pawn;
            board [6][y]= chessboardclass.Pawn;
        }
    }


}
