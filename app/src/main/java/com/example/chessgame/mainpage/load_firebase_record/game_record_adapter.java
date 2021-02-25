package com.example.chessgame.mainpage.load_firebase_record;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chessgame.R;
import com.example.chessgame.constructor.game_record_firebase;

import java.util.ArrayList;


public class game_record_adapter extends RecyclerView.Adapter<game_record_adapter.ViewHolder> {
        private ArrayList<game_record_firebase> game_record;
        private OnItemClickListener mListener;
        public interface OnItemClickListener{
            void onItemClick(int position);
        }

        public void setOnItemClickListener(OnItemClickListener listener){
            mListener=listener;
        }

        public static class ViewHolder extends RecyclerView.ViewHolder{


        public TextView name;
        public TextView description;
        public TextView white;
        public TextView black;
        public TextView uploadtime;


        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            name=itemView.findViewById(R.id.Name);
            description=itemView.findViewById(R.id.description);
            white=itemView.findViewById(R.id.white);
            black=itemView.findViewById(R.id.black);
            uploadtime=itemView.findViewById(R.id.uploadtime);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position =getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
    public game_record_adapter(ArrayList<game_record_firebase> mgame_record){
        game_record=mgame_record;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.get_firebase_record,parent,false);
        ViewHolder viewholder=new ViewHolder(v,mListener);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            game_record_firebase currentitem = game_record.get(position);

            holder.name.setText("Name:"+currentitem.getName());
            holder.description.setText("Description:"+currentitem.getDescription());
            holder.white.setText("White player:"+currentitem.getWhite());
            holder.black.setText("Black player:"+currentitem.getBlack());
            holder.uploadtime.setText("Uploaded time:"+currentitem.getTime());
    }

    @Override
    public int getItemCount() {
        return game_record.size();
    }
}
