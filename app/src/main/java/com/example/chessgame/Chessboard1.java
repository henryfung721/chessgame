package com.example.chessgame;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Chessboard1 extends Fragment implements View.OnClickListener{
    private static final int[] idarray = {R.id.x1y1,R.id.x1y2,R.id.x1y3,R.id.x1y4,R.id.x1y5,R.id.x1y6,R.id.x1y7,R.id.x1y8,R.id.x2y1,R.id.x2y2,R.id.x2y3,R.id.x2y4,R.id.x2y5,R.id.x2y6,R.id.x2y7,R.id.x2y8,R.id.x3y1,R.id.x3y2,R.id.x3y3,R.id.x3y4,R.id.x3y5,R.id.x3y6,R.id.x3y7,R.id.x3y8,R.id.x4y1,R.id.x4y2,R.id.x4y3,R.id.x4y4,R.id.x4y5,R.id.x4y6,R.id.x4y7,R.id.x4y8,R.id.x5y1,R.id.x5y2,R.id.x5y3,R.id.x5y4,R.id.x5y5,R.id.x5y6,R.id.x5y7,R.id.x5y8,R.id.x6y1,R.id.x6y2,R.id.x6y3,R.id.x6y4,R.id.x6y5,R.id.x6y6,R.id.x6y7,R.id.x6y8,R.id.x7y1,R.id.x7y2,R.id.x7y3,R.id.x7y4,R.id.x7y5,R.id.x7y6,R.id.x7y7,R.id.x7y8,R.id.x8y1,R.id.x8y2,R.id.x8y3,R.id.x8y4,R.id.x8y5,R.id.x8y6,R.id.x8y7,R.id.x8y8,};
    private static final int[] coordinate ={11,12,13,14,15,16,17,18,21,22,23,24,25,26,27,28,31,32,33,34,35,36,37,38,41,42,43,44,45,46,47,48,51,52,53,54,55,56,57,58,61,62,63,64,65,66,67,68,71,72,73,74,75,76,77,78,81,82,83,84,85,86,87,88,};
    private ImageButton [] imageButtonarray = new ImageButton[idarray.length];
    chessboard chessboardclass = new chessboard();


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chessboard1, container, false);

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        for(int i =0;i<idarray.length;i++){
            imageButtonarray[i] = (ImageButton) getView().findViewById(idarray[i]);
            imageButtonarray[i].setOnClickListener(this);
        }
        // or  (ImageView) view.findViewById(R.id.foo);
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

    }
}
