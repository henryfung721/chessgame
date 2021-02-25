package com.example.chessgame.mainpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.chessgame.R;
import com.example.chessgame.mainpage.matching_mode.chesssetting;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class main_fragment extends Fragment {

    Button edit_board_mode,single_match,tutorial_mode,end_game_mode;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    String userid;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fregment_main,container,false);
        tutorial_mode = view.findViewById(R.id.tutorial_mode);
        tutorial_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tutorial_mode_Fragment tutorial_mode_fragment=new tutorial_mode_Fragment();
                FragmentTransaction transaction =getFragmentManager().beginTransaction();
                transaction.replace(R.id.framework,tutorial_mode_fragment);
                transaction.commit();
            }
        });


        single_match = view.findViewById(R.id.matching_mode);
        single_match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), chesssetting.class);
                startActivity(intent);
            }
        });


        end_game_mode = view.findViewById(R.id.end_game_mode);
        end_game_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), profile_fragment.class);
                startActivity(intent);
            }
        });


        edit_board_mode = view.findViewById(R.id.edit_board_mode);
        edit_board_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


        firebaseAuth= FirebaseAuth.getInstance();//firebase
        firestore=FirebaseFirestore.getInstance();
        userid=firebaseAuth.getCurrentUser().getUid();
        final FirebaseUser user= firebaseAuth.getCurrentUser();
        return view;
    }

}
