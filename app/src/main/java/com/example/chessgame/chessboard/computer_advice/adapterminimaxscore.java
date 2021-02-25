package com.example.chessgame.chessboard.computer_advice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chessgame.R;
import com.example.chessgame.chessboard.chessboard;
import com.example.chessgame.constructor.minimax_score;

import java.util.ArrayList;

import static com.example.chessgame.chessboard.chessboard.empty;


public class adapterminimaxscore extends RecyclerView.Adapter<adapterminimaxscore.ViewHolder> {
    private ArrayList<minimax_score> showminimaxscore;
    public chessboard chessboardclass= new chessboard();

    public static class ViewHolder extends RecyclerView.ViewHolder{


        public TextView mstep;
        public ImageView mimageView;
        public TextView mTextview1;
        public TextView mTextview2;
        public TextView minimaxscore;
        public ImageView meatimageView;

        public ImageView predictedmimageView;
        public ImageView predictedmeatimageView;
        public TextView predictedmTextview1;
        public TextView predictedmTextview2;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mstep=itemView.findViewById(R.id.steps);
            mTextview1=itemView.findViewById(R.id.textView1);
            mTextview2=itemView.findViewById(R.id.textView2);
            mimageView=itemView.findViewById(R.id.imageview);
            meatimageView=itemView.findViewById(R.id.imagevieweatedpiece);
            minimaxscore=itemView.findViewById(R.id.minimaxscore);
            predictedmTextview1=itemView.findViewById(R.id.predictedtextView1);
            predictedmTextview2=itemView.findViewById(R.id.predictedtextView2);
            predictedmimageView=itemView.findViewById(R.id.predictedimageview);
            predictedmeatimageView=itemView.findViewById(R.id.predictedimagevieweatedpiece);
        }
    }
    public adapterminimaxscore(ArrayList<minimax_score> minimax_score){
        showminimaxscore=minimax_score;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.showminimaxscore,parent,false);
        ViewHolder viewholder=new ViewHolder(v);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            minimax_score currentitem = showminimaxscore.get(position);

            holder.mstep.setText(Integer.toString(position+1)+")");
            if(currentitem.getFirstchesstype()!=empty) {
                holder.mimageView.setImageResource(getphoto(currentitem.getFirstchesstype(), currentitem.getPlayer()));
            }
            if(currentitem.getSecondchesstype()!=empty) {
                holder.meatimageView.setBackgroundResource(getphoto(currentitem.getSecondchesstype(), changeplayer(currentitem.getPlayer())));
                holder.meatimageView.setImageResource(R.drawable.cross);
            }
            holder.mTextview1.setText("from "+chessboardclass.getposition(currentitem.getFirstpositionx(),currentitem.getFirstpositiony()));
            holder.mTextview2.setText("to: "+chessboardclass.getposition(currentitem.getSecondpositionx(),currentitem.getSecondpositiony()));
            holder.minimaxscore.setText("computer score:"+Integer.toString(currentitem.getMinimaxscrore()));
            holder.predictedmTextview1.setText("from "+chessboardclass.getposition(currentitem.getpredictFirstpositionx(),currentitem.getpredictFirstpositiony()));
            holder.predictedmTextview2.setText("to: "+chessboardclass.getposition(currentitem.getpredictSecondpositionx(),currentitem.getpredictSecondpositiony()));
            if(currentitem.getpredictFirstchesstype()!=empty) {
                holder.predictedmimageView.setImageResource(getphoto(currentitem.getpredictFirstchesstype(), changeplayer(currentitem.getPlayer())));
            }
            if(currentitem.getpredictSecondchesstype()!=empty) {
                holder.predictedmeatimageView.setBackgroundResource(getphoto(currentitem.getpredictSecondchesstype(), currentitem.getPlayer()));
                holder.predictedmeatimageView.setImageResource(R.drawable.cross);
            }

    }

    @Override
    public int getItemCount() {
        return showminimaxscore.size();
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
