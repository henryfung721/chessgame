package com.example.chessgame.chessboard.game_record;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chessgame.R;
import com.example.chessgame.chessboard.chessboard;
import com.example.chessgame.constructor.move_record;
import com.example.chessgame.constructor.themove;
import com.example.chessgame.mainpage.matching_mode.vscomputerchessgame;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class gamerecord extends BottomSheetDialogFragment {

    private RecyclerView mrecyclerview;
    private RecyclerView.Adapter madapter;
    private RecyclerView.LayoutManager mlayoutmanager;

    chessboard chessboardclass= new chessboard();
    private PreviousMoveListener mlistener;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.gamerecord,container,false);
        vscomputerchessgame vscomputerchessgameclass=new vscomputerchessgame();
        ArrayList<themove> storethemove= chessboardclass.storethemove;
        ArrayList<move_record> moverecord = new ArrayList<>();

        for(int runningtime=0;runningtime<storethemove.size();runningtime++){
            themove move = storethemove.get(runningtime);
             int player =move.getPlayer();
             String firstchesstype=move.getFirstchesstype();
             int firstpositionx=move.getFirstpositionx();
             int firstpositiony=move.getFirstpositiony();
             String secondchesstype=move.getSecondchesstype();
             int secondpositionx=move.getSecondpositionx();
             int secondpositiony=move.getSecondpositiony();
             boolean castling=move.isCastling();


             int photo =getphoto(firstchesstype,player);
             int eatphoto=getphoto(secondchesstype,changeplayer(player));
             String firstposition =chessboardclass.getposition(firstpositionx,firstpositiony);
             String secondposition =chessboardclass.getposition(secondpositionx,secondpositiony);
             String numberofstep=String.valueOf(runningtime+1);
             moverecord.add(new move_record(numberofstep,photo,firstposition,secondposition,eatphoto));



        }




        mrecyclerview = v.findViewById(R.id.recyclerview1234);
        mrecyclerview.setHasFixedSize(true);
        mlayoutmanager= new LinearLayoutManager(vscomputerchessgameclass);
        madapter= new adapter(moverecord);

        mrecyclerview.setLayoutManager(mlayoutmanager);
        mrecyclerview.setAdapter(madapter);



        return v;
    }



    public interface PreviousMoveListener{
        void OnButtonClicked(String text);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mlistener = (PreviousMoveListener) context;
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString()+" must implement BottomSheetListener");
        }
    }

    private int changeplayer(int player) {
        if(player==1){
            return 2;
        }else if(player==2){
            return 1;
        }
        return 0;
    }
    public int getphoto(String chesstype, int player){
        int photo =0;
        if(chesstype==chessboardclass.King && player==chessboardclass.player1){
            photo=R.drawable.whiteking;
        }
        if(chesstype==chessboardclass.Queen && player==chessboardclass.player1){
            photo=R.drawable.whitequeen;
        }
        if(chesstype==chessboardclass.Bishop && player==chessboardclass.player1){
            photo=R.drawable.whitebishop;
        }
        if(chesstype==chessboardclass.Knight && player==chessboardclass.player1){
            photo=R.drawable.whiteknight;
        }
        if(chesstype==chessboardclass.Rook && player==chessboardclass.player1){
            photo=R.drawable.whiterook;
        }
        if(chesstype==chessboardclass.Pawn && player==chessboardclass.player1){
            photo=R.drawable.whitepawn;
        }
        if(chesstype==chessboardclass.King && player==chessboardclass.player2){
            photo=R.drawable.blackking;
        }
        if(chesstype==chessboardclass.Queen && player==chessboardclass.player2){
            photo=R.drawable.blackqueen;
        }
        if(chesstype==chessboardclass.Bishop && player==chessboardclass.player2){
            photo=R.drawable.blackbishop;
        }
        if(chesstype==chessboardclass.Knight && player==chessboardclass.player2){
            photo=R.drawable.blackknight;
        }
        if(chesstype==chessboardclass.Rook && player==chessboardclass.player2){
            photo=R.drawable.blackrook;
        }
        if(chesstype==chessboardclass.Pawn && player==chessboardclass.player2){
            photo=R.drawable.blackpawn;
        }
        return photo;
    }


}
