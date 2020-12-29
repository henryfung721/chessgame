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

public class vscomputerchessgame extends AppCompatActivity implements View.OnClickListener {

    Button startgame;


    chessboard chessboardclass = new chessboard();
    Chessboard1 Chessboard1class = new Chessboard1();
    private static final int[] idarray = {R.id.x1y1,R.id.x1y2,R.id.x1y3,R.id.x1y4,R.id.x1y5,R.id.x1y6,R.id.x1y7,R.id.x1y8,R.id.x2y1,R.id.x2y2,R.id.x2y3,R.id.x2y4,R.id.x2y5,R.id.x2y6,R.id.x2y7,R.id.x2y8,R.id.x3y1,R.id.x3y2,R.id.x3y3,R.id.x3y4,R.id.x3y5,R.id.x3y6,R.id.x3y7,R.id.x3y8,R.id.x4y1,R.id.x4y2,R.id.x4y3,R.id.x4y4,R.id.x4y5,R.id.x4y6,R.id.x4y7,R.id.x4y8,R.id.x5y1,R.id.x5y2,R.id.x5y3,R.id.x5y4,R.id.x5y5,R.id.x5y6,R.id.x5y7,R.id.x5y8,R.id.x6y1,R.id.x6y2,R.id.x6y3,R.id.x6y4,R.id.x6y5,R.id.x6y6,R.id.x6y7,R.id.x6y8,R.id.x7y1,R.id.x7y2,R.id.x7y3,R.id.x7y4,R.id.x7y5,R.id.x7y6,R.id.x7y7,R.id.x7y8,R.id.x8y1,R.id.x8y2,R.id.x8y3,R.id.x8y4,R.id.x8y5,R.id.x8y6,R.id.x8y7,R.id.x8y8,};
    private static final int[] coordinate ={11,12,13,14,15,16,17,18,21,22,23,24,25,26,27,28,31,32,33,34,35,36,37,38,41,42,43,44,45,46,47,48,51,52,53,54,55,56,57,58,61,62,63,64,65,66,67,68,71,72,73,74,75,76,77,78,81,82,83,84,85,86,87,88,};
    private static ImageView[] imageButtonarray = new ImageView[idarray.length];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vscomputerchessgame);

        startgame=findViewById(R.id.startgame);

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
                chessboardclass.input(x,y);


            }
        }
        switch (v.getId()) {
            case R.id.startgame:
                chessboardclass.startgame();
                setfigure();

                break;
        }
    }

    public void setfigure(){
        for(int i =0;i<idarray.length;i++){

            int intdata = coordinate[i];
            int x=intdata/10;
            int y=intdata%10;
            x=x-1;
            y=y-1;

            if(chessboardclass.returnchesspiece(x,y)=="empty"){
                imageButtonarray[i].setVisibility(View.INVISIBLE);
            } else if(chessboardclass.board[x][y]==chessboardclass.Pawn&&chessboardclass.checkplayer[x][y]==1){
                imageButtonarray[i].setImageResource(R.drawable.whitepawn);
            }else if(chessboardclass.board[x][y]==chessboardclass.Rook&&chessboardclass.checkplayer[x][y]==1){
                imageButtonarray[i].setImageResource(R.drawable.whiterook);
            }else if(chessboardclass.board[x][y]==chessboardclass.Knight&&chessboardclass.checkplayer[x][y]==1){
                imageButtonarray[i].setImageResource(R.drawable.whiteknight);
            }else if(chessboardclass.board[x][y]==chessboardclass.Bishop&&chessboardclass.checkplayer[x][y]==1){
                imageButtonarray[i].setImageResource(R.drawable.whitebishop);
            }else if(chessboardclass.board[x][y]==chessboardclass.Queen&&chessboardclass.checkplayer[x][y]==1){
                imageButtonarray[i].setImageResource(R.drawable.whitequeen);
            }else if(chessboardclass.board[x][y]==chessboardclass.King&&chessboardclass.checkplayer[x][y]==1){
                imageButtonarray[i].setImageResource(R.drawable.whiteking);
            }else if(chessboardclass.board[x][y]==chessboardclass.Pawn&&chessboardclass.checkplayer[x][y]==2){
                imageButtonarray[i].setImageResource(R.drawable.blackpawn);
            }else if(chessboardclass.board[x][y]==chessboardclass.Rook&&chessboardclass.checkplayer[x][y]==2){
                imageButtonarray[i].setImageResource(R.drawable.blackrook);
            }else if(chessboardclass.board[x][y]==chessboardclass.Knight&&chessboardclass.checkplayer[x][y]==2){
                imageButtonarray[i].setImageResource(R.drawable.blackknight);
            }else if(chessboardclass.board[x][y]==chessboardclass.Bishop&&chessboardclass.checkplayer[x][y]==2){
                imageButtonarray[i].setImageResource(R.drawable.blackbishop);
            }else if(chessboardclass.board[x][y]==chessboardclass.Queen&&chessboardclass.checkplayer[x][y]==2){
                imageButtonarray[i].setImageResource(R.drawable.blackqueen);
            }else if(chessboardclass.board[x][y]==chessboardclass.King&&chessboardclass.checkplayer[x][y]==2){
                imageButtonarray[i].setImageResource(R.drawable.blackking);
            }/*else if(){
                imageButtonarray[i].setImageResource(R.drawable.point);
            }*/
        }
    }
}
