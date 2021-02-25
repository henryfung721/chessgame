package com.example.chessgame.mainpage;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.chessgame.R;
import com.example.chessgame.mainpage.matching_mode.chesssetting;


public class tutorial_mode_Fragment extends Fragment {

    Button king_move,queen_move,bishop_move,knight_move,rook_move,pawn_move,castling,promotion,En_Passant,win_game,lose_game;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_tutorial_mode,container,false);

        king_move = view.findViewById(R.id.king_move);
        king_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), chesssetting.class);
                startActivity(intent);
            }
        });

        queen_move = view.findViewById(R.id.queen_move);
        queen_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        bishop_move = view.findViewById(R.id.bishop_move);
        bishop_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        knight_move = view.findViewById(R.id.knight_move);
        knight_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        rook_move = view.findViewById(R.id.rook_move);
        rook_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        pawn_move = view.findViewById(R.id.pawn_move);
        pawn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        castling = view.findViewById(R.id.castling);
        castling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        promotion = view.findViewById(R.id.promotion);
        promotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        En_Passant = view.findViewById(R.id.En_Passant);
        En_Passant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        win_game = view.findViewById(R.id.win_game);
        win_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        lose_game = view.findViewById(R.id.lose_game);
        lose_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });





        return view;
    }
}
