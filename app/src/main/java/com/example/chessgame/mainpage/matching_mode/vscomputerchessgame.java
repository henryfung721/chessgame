package com.example.chessgame.mainpage.matching_mode;

import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chessgame.R;
import com.example.chessgame.chessboard.Minimax;
import com.example.chessgame.chessboard.chessboard;
import com.example.chessgame.chessboard.game_record.gamerecord;
import com.example.chessgame.chessboard.save_game_record_to_firebase.dialog;
import com.example.chessgame.chessboard.computer_advice.adapterminimaxscore;
import com.example.chessgame.constructor.minimax_score;
import com.example.chessgame.constructor.themove;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;

public class vscomputerchessgame extends AppCompatActivity implements  com.example.chessgame.chessboard.game_record.gamerecord.PreviousMoveListener,View.OnClickListener, dialog.dialoglistener{

    private RecyclerView mrecyclerview;
    private RecyclerView.Adapter madapter;
    private RecyclerView.LayoutManager mlayoutmanager;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    String userid;
    FirebaseDatabase firebasedatabase;
    DatabaseReference databasereference;
    boolean firebasehaverecord;

    OvershootInterpolator interpolator = new OvershootInterpolator();
    Boolean buttonextended=false;
    ExtendedFloatingActionButton startgamebutton,computersuggestion,gamerecord,undobutton,upload,menubutton;

    TextView whiteplayer,blackplayer;

    BottomSheetBehavior mbottomSheetBehavior;
    TextView mTextView;

    Boolean gamestarted;
    String getwhiteplayer;
    String getblackplayer;
    int minimaxlooptime;

    public static TextView turn;
    public static String Whiteturn="White Turn";
    public static String Blackturn="Black Turn";
    public static String Whitewin="White Win";
    public static String Blackwin="Black Win";

    static Minimax minimaxclass=new Minimax();
    static chessboard chessboardclass = new chessboard();

    private static final int[] idarray = {R.id.x1y1,R.id.x1y2,R.id.x1y3,R.id.x1y4,R.id.x1y5,R.id.x1y6,R.id.x1y7,R.id.x1y8,R.id.x2y1,R.id.x2y2,R.id.x2y3,R.id.x2y4,R.id.x2y5,R.id.x2y6,R.id.x2y7,R.id.x2y8,R.id.x3y1,R.id.x3y2,R.id.x3y3,R.id.x3y4,R.id.x3y5,R.id.x3y6,R.id.x3y7,R.id.x3y8,R.id.x4y1,R.id.x4y2,R.id.x4y3,R.id.x4y4,R.id.x4y5,R.id.x4y6,R.id.x4y7,R.id.x4y8,R.id.x5y1,R.id.x5y2,R.id.x5y3,R.id.x5y4,R.id.x5y5,R.id.x5y6,R.id.x5y7,R.id.x5y8,R.id.x6y1,R.id.x6y2,R.id.x6y3,R.id.x6y4,R.id.x6y5,R.id.x6y6,R.id.x6y7,R.id.x6y8,R.id.x7y1,R.id.x7y2,R.id.x7y3,R.id.x7y4,R.id.x7y5,R.id.x7y6,R.id.x7y7,R.id.x7y8,R.id.x8y1,R.id.x8y2,R.id.x8y3,R.id.x8y4,R.id.x8y5,R.id.x8y6,R.id.x8y7,R.id.x8y8,};
    private static final int[] coordinate ={11,12,13,14,15,16,17,18,21,22,23,24,25,26,27,28,31,32,33,34,35,36,37,38,41,42,43,44,45,46,47,48,51,52,53,54,55,56,57,58,61,62,63,64,65,66,67,68,71,72,73,74,75,76,77,78,81,82,83,84,85,86,87,88};
    private static ImageView[] imageButtonarray = new ImageView[idarray.length];
    public static String [][] board ; // to store the piece
    public static int[][] checkplayer ; //to check who owned the pieces

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //identify board variables
        setContentView(R.layout.activity_vscomputerchessgame);
        menubutton=(ExtendedFloatingActionButton)findViewById(R.id.menubuttom);
        startgamebutton=(ExtendedFloatingActionButton)findViewById(R.id.startgamebutton);
        gamerecord=(ExtendedFloatingActionButton)findViewById(R.id.gamerecord);
        computersuggestion=(ExtendedFloatingActionButton)findViewById(R.id.computersuggestion);
        undobutton=(ExtendedFloatingActionButton)findViewById(R.id.undobutton);
        upload=(ExtendedFloatingActionButton)findViewById(R.id.upload);
        turn=findViewById(R.id.turn);


        for(int i =0;i<idarray.length;i++){
            imageButtonarray[i] = findViewById(idarray[i]);
            imageButtonarray[i].setOnClickListener(this);
        }
        whiteplayer=findViewById(R.id.white);
        blackplayer=findViewById(R.id.black);

        //identify game variables
        gamestarted=false;
        getwhiteplayer = getIntent().getStringExtra("white");
        getblackplayer = getIntent().getStringExtra("black");
        minimaxlooptime=Integer.parseInt(getIntent().getStringExtra("number"));
        whiteplayer.setText("white: "+getwhiteplayer);
        blackplayer.setText("black: "+getblackplayer);

        View bottomSheet = findViewById(R.id.bottomsheet);
        mbottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mTextView = findViewById(R.id.textView1);

        //firebase
        firebasedatabase = FirebaseDatabase.getInstance();
        firebaseAuth= FirebaseAuth.getInstance();
        userid = firebaseAuth.getCurrentUser().getUid();

        //add chess board value
        addchessplayer();
        addchesspiece();

    }
    @Override
    public void onClick(View v) {
        //if user press other buttons
        switch (v.getId()) {
            case R.id.menubuttom: {
                //start the game
                menubuttonfunction();
                break;
            }

            case R.id.startgamebutton: {
                //start the game
                menubuttonfunction();
                startgame();
                break;
            }
            case R.id.undobutton: {
                menubuttonfunction();
                undothemove();
                break;
            }
            case R.id.gamerecord: {
                menubuttonfunction();
                //read the game record
                gamerecord();
                break;
            }
            case R.id.computersuggestion: {
                menubuttonfunction();
                computersuggestuinfunstion();
                break;
            }
            case R.id.upload: {
                menubuttonfunction();
                //store the game record
                openuploaddialog();
                break;
            }

        }
        // if user click the the boardwithout clicking startgame
        if(gamestarted==false){
            chessboardclass.startgame(getwhiteplayer,getblackplayer,board,checkplayer,checkplayer);
            turn.setText(vscomputerchessgame.Whiteturn);
            checkfirstplayeriscomputer();
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
                    //if user already selected the piece and want to move it
                    chessboardclass.changechessposition(x,y);
                    chessboardclass.removepossiblemoveimage();
                    switchplayer();

                }else {
                    //if user have not select the piece
                    chessboardclass.removepossiblemoveimage();
                    chessboardclass.input(x, y);
                }
                setfigure();
            }
        }
    }

    //default chess board
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



    private void menubuttonfunction() {
        if(buttonextended==false){
            startgamebutton.setVisibility(View.VISIBLE);
            computersuggestion.setVisibility(View.VISIBLE);
            gamerecord.setVisibility(View.VISIBLE);
            undobutton.setVisibility(View.VISIBLE);
            upload.setVisibility(View.VISIBLE);
            buttonextended=true;
        }else if(buttonextended==true){
            startgamebutton.setVisibility(View.GONE);
            computersuggestion.setVisibility(View.GONE);
            gamerecord.setVisibility(View.GONE);
            undobutton.setVisibility(View.GONE);
            upload.setVisibility(View.GONE);
            buttonextended=false;
        }
    }

    private void startgame() {
        chessboardclass.startgame(getwhiteplayer,getblackplayer, board, checkplayer, checkplayer);
        turn.setText(vscomputerchessgame.Whiteturn);
        gamestarted = true;

        //if white is computer
        checkfirstplayeriscomputer();
        //update the board image
        setfigure();
        firebasehaverecord=false;
    }

    private void undothemove() {
        ArrayList<themove> storethemove= chessboardclass.storethemove;
        if(storethemove.size()==0) {
            outputcannotundo();
        }else{
            themove getlastmove = storethemove.get(storethemove.size() - 1);
            int player = getlastmove.getPlayer();
            String firstchesstype = getlastmove.getFirstchesstype();
            int firstpositionx = getlastmove.getFirstpositionx();
            int firstpositiony = getlastmove.getFirstpositiony();
            String secondchesstype = getlastmove.getSecondchesstype();
            int secondpositionx = getlastmove.getSecondpositionx();
            int secondpositiony = getlastmove.getSecondpositiony();
            boolean castling = getlastmove.isCastling();
            chessboardclass.board[firstpositionx][firstpositiony] = firstchesstype;
            chessboardclass.checkplayer[firstpositionx][firstpositiony] = player;
            if(secondchesstype!=chessboardclass.empty) {
                chessboardclass.board[secondpositionx][secondpositiony] = secondchesstype;
                chessboardclass.checkplayer[secondpositionx][secondpositiony] = changeplayer(player);
            }
            if(secondchesstype==chessboardclass.empty){
                chessboardclass.board[secondpositionx][secondpositiony] = chessboardclass.empty;
                chessboardclass.checkplayer[secondpositionx][secondpositiony] = chessboardclass.noone;
            }
            chessboardclass.recordremovelastmove();
            setfigure();
            switchplayer();
        }
    }

    private void outputcannotundo() {
        Toast toast = Toast.makeText(getApplicationContext(), "can not undo", Toast.LENGTH_SHORT);
        toast.show();
    }

    private void gamerecord() {

        gamerecord record= new gamerecord();
        record.show(getSupportFragmentManager(),"previousmove0");
    }

    private void computersuggestuinfunstion() {
        mbottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED );
        ArrayList<minimax_score> minimaxscorelist= minimaxclass.computeradvice(chessboardclass.currentplayer,chessboardclass.board ,chessboardclass.checkplayer,3);
        mrecyclerview=findViewById(R.id.recyclerviewminimaxscore);
        mrecyclerview.setHasFixedSize(true);
        mlayoutmanager= new LinearLayoutManager(this);
        madapter= new adapterminimaxscore(minimaxscorelist);

        mrecyclerview.setLayoutManager(mlayoutmanager);
        mrecyclerview.setAdapter(madapter);

    }

    //upload to firebase
    public void openuploaddialog(){
        if(gamestarted==true) {
            dialog dialog = new dialog();
            dialog.show(getSupportFragmentManager(), "dialog");
        }
    }
    @Override
    public void applyTexts(String name, String description) {
        uploadtofirebase( name,  description);
    }
    private void uploadtofirebase(final String name, final String description) {
        ArrayList<themove> storethemove= chessboardclass.storethemove;
        String output="";
        for(int runningtime=0;runningtime<storethemove.size();runningtime++){
            themove move = storethemove.get(runningtime);
            int firstpositionx=move.getFirstpositionx();
            int firstpositiony=move.getFirstpositiony();
            int secondpositionx=move.getSecondpositionx();
            int secondpositiony=move.getSecondpositiony();
            output=output+String.valueOf(firstpositionx)+String.valueOf(firstpositiony)+String.valueOf(secondpositionx)+String.valueOf(secondpositiony)+",";
        }
        DatabaseReference reference=firebasedatabase.getInstance().getReference().child("gamerecord").child(userid);
        final String finalOutput = output;
        final String currentTime = Calendar.getInstance().getTime().toString();
        if(!firebasehaverecord){
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    final String finalcount = String.valueOf(snapshot.getChildrenCount());
                    databasereference = firebasedatabase.getReference("gamerecord");
                    databasereference.child(userid).child(finalcount).child("uploadtime").setValue(currentTime);
                    databasereference.child(userid).child(finalcount).child("move").setValue(finalOutput);
                    databasereference.child(userid).child(finalcount).child("white").setValue(getwhiteplayer);
                    databasereference.child(userid).child(finalcount).child("black").setValue(getblackplayer);
                    databasereference.child(userid).child(finalcount).child("starting chess board").setValue(chessboardclass.returnoriginalchessboard());
                    databasereference.child(userid).child(finalcount).child("name").setValue(name);
                    databasereference.child(userid).child(finalcount).child("description").setValue(description);
                    databasereference.child(userid).child(finalcount).child("endgame chess board").setValue(chessboardclass.returnendgamechessboard());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            firebasehaverecord=true;
        }else{
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    final String finalcount = String.valueOf(snapshot.getChildrenCount()-1);
                    databasereference = firebasedatabase.getReference("gamerecord");
                    databasereference.child(userid).child(finalcount).child("uploadtime").setValue(currentTime);
                    databasereference.child(userid).child(finalcount).child("move").setValue(finalOutput);
                    databasereference.child(userid).child(finalcount).child("white").setValue(getwhiteplayer);
                    databasereference.child(userid).child(finalcount).child("black").setValue(getblackplayer);
                    databasereference.child(userid).child(finalcount).child("starting chess board").setValue(chessboardclass.returnoriginalchessboard());
                    databasereference.child(userid).child(finalcount).child("name").setValue(name);
                    databasereference.child(userid).child(finalcount).child("description").setValue(description);
                    databasereference.child(userid).child(finalcount).child("endgame chess board").setValue(chessboardclass.returnendgamechessboard());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            firebasehaverecord=true;
        }
    }



    public void checkfirstplayeriscomputer(){
        if(getwhiteplayer.equals(chessboardclass.computer)){
            minimaxclass.computermove(chessboardclass.player1,chessboardclass.board ,chessboardclass.checkplayerclone,minimaxlooptime);
            switchplayer();
        }
    }

    public void switchplayer(){
        if(checkifendgame()==false) {
            setfigure();

            if (chessboardclass.currentplayer == chessboardclass.player1) {
                //player2
                chessboardclass.currentplayer = chessboardclass.player2;
                setboardswitchplayer();
                if (chessboardclass.blackplayer.equals(chessboardclass.computer)) {
                    minimaxclass.computermove(chessboardclass.player2, chessboardclass.board, chessboardclass.checkplayerclone,minimaxlooptime);
                    setfigure();
                    setboardswitchplayer();
                    if (checkifendgame() == false) {
                        switchplayer();
                    }
                }
            } else if (chessboardclass.currentplayer == chessboardclass.player2) {
                //player1f
                chessboardclass.currentplayer = chessboardclass.player1;
                setboardswitchplayer();
                if (chessboardclass.whiteplayer.equals(chessboardclass.computer)) {
                    minimaxclass.computermove(chessboardclass.player1, chessboardclass.board, chessboardclass.checkplayerclone,minimaxlooptime);
                    setfigure();
                    setboardswitchplayer();
                    if (checkifendgame() == false) {
                        switchplayer();
                    }
                }
            }
        }
    }
    public void setboardswitchplayer(){
        if(chessboardclass.currentplayer==chessboardclass.player1){
            turn.setText(Whiteturn);
        }else if(chessboardclass.currentplayer==chessboardclass.player2){
            turn.setText(Blackturn);
        }
    }

    public static void setfigure(){
        removepreviousfigure();
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
    public static void removepreviousfigure(){
        for(int i =0;i<idarray.length;i++) {
            addimage(i,0);
        }
    }
    public static void addimage(int i ,int x ){
        imageButtonarray[i].setVisibility(View.VISIBLE);
        imageButtonarray[i].setBackgroundResource(x);
        imageButtonarray[i].setImageResource(0);
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
    private int changeplayer(int player) {
        if(player==1){
            return 2;
        }else if(player==2){
            return 1;
        }
        return 0;
    }


    @Override
    public void OnButtonClicked(String text) {

    }
}
