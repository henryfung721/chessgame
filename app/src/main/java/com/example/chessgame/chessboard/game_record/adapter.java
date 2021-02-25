package com.example.chessgame.chessboard.game_record;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chessgame.R;
import com.example.chessgame.constructor.move_record;

import java.util.ArrayList;


public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {
        private ArrayList<com.example.chessgame.constructor.move_record> move_record;
        public static class ViewHolder extends RecyclerView.ViewHolder{


        public TextView mstep;
        public ImageView mimageView;
        public TextView mTextview1;
        public TextView mTextview2;
        public ImageView meatimageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mstep=itemView.findViewById(R.id.steps);
            mimageView=itemView.findViewById(R.id.imageview);
            mTextview1=itemView.findViewById(R.id.textView1);
            mTextview2=itemView.findViewById(R.id.textView2);
            meatimageView=itemView.findViewById(R.id.imagevieweatedpiece);
        }
    }
    public adapter(ArrayList<com.example.chessgame.constructor.move_record> mymove_record){
        move_record=mymove_record;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.showmoverecord,parent,false);
        ViewHolder viewholder=new ViewHolder(v);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            move_record currentitem = move_record.get(position);

            holder.mstep.setText(currentitem.getstep());
            holder.mimageView.setImageResource(currentitem.getImageResource());
            holder.mTextview1.setText(currentitem.getText1());
            holder.mTextview2.setText(currentitem.getText2());
            if(currentitem.geteatImageResource()!=0) {
                holder.meatimageView.setBackgroundResource(currentitem.geteatImageResource());
                holder.meatimageView.setImageResource(R.drawable.cross);
            }
    }

    @Override
    public int getItemCount() {
        return move_record.size();
    }
}
