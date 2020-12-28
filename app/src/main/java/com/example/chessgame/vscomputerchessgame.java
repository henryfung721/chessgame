package com.example.chessgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class vscomputerchessgame extends AppCompatActivity implements View.OnClickListener {

    Button startgame;
    ConstraintLayout chessboard;
    chessboard chessboardclass = new chessboard();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vscomputerchessgame);

        startgame=findViewById(R.id.startgame);
        chessboard=findViewById(R.id.chessboard);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startgame:
                chessboardclass.startgame();
                break;
        }
    }
}
