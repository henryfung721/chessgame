package com.example.chessgame.mainpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chessgame.R;
import com.example.chessgame.mainpage.load_firebase_record.game_record_adapter;
import com.example.chessgame.constructor.game_record_firebase;
import com.example.chessgame.mainpage.load_firebase_record.load_record;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class gamerecord_fragment extends Fragment {
    private RecyclerView recyclerView;
    private game_record_adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    String userid;
    FirebaseDatabase firebasedatabase;
    DatabaseReference databasereference;
    View v ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.fregment_gamerecord,container,false);
        retrieve_firebase_record();


        return v;
    }

    private void retrieve_firebase_record() {
        //firebase
        firebasedatabase = FirebaseDatabase.getInstance();
        firebaseAuth= FirebaseAuth.getInstance();
        userid = firebaseAuth.getCurrentUser().getUid();

        DatabaseReference reference=firebasedatabase.getInstance().getReference().child("gamerecord").child(userid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                  final ArrayList<game_record_firebase> game_record_firebases=new ArrayList<>();

                for (int run =0;run<snapshot.getChildrenCount();run++) {
                    String name = snapshot.child(String.valueOf(run)).child("name").getValue().toString();
                    String descripton = snapshot.child(String.valueOf(run)).child("description").getValue().toString();
                    String move = snapshot.child(String.valueOf(run)).child("move").getValue().toString();
                    String time = snapshot.child(String.valueOf(run)).child("uploadtime").getValue().toString();
                    String endgameboard = snapshot.child(String.valueOf(run)).child("endgame chess board").getValue().toString();
                    String startgameboard = snapshot.child(String.valueOf(run)).child("starting chess board").getValue().toString();
                    String black = snapshot.child(String.valueOf(run)).child("black").getValue().toString();
                    String white = snapshot.child(String.valueOf(run)).child("white").getValue().toString();
                    game_record_firebases.add(new game_record_firebase(String.valueOf(run),name, descripton, move, time, startgameboard, endgameboard, black, white));
                }

                recyclerView = v.findViewById(R.id.getfirebaserecord);
                recyclerView.setHasFixedSize(true);
                layoutManager= new LinearLayoutManager(getActivity());
                adapter= new game_record_adapter(game_record_firebases);

                recyclerView.setLayoutManager (layoutManager);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new game_record_adapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(getActivity(), load_record.class);
                        intent.putExtra("white",game_record_firebases.get(position).getWhite());
                        intent.putExtra("black",game_record_firebases.get(position).getBlack());
                        intent.putExtra("startboard",game_record_firebases.get(position).getStartchessboard());
                        intent.putExtra("endboard",game_record_firebases.get(position).getEndchessboard());
                        intent.putExtra("moverecord",game_record_firebases.get(position).getMove());
                        intent.putExtra("computer calculate","3");
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
