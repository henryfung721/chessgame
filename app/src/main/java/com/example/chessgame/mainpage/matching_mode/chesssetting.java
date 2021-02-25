package com.example.chessgame.mainpage.matching_mode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chessgame.R;

public class chesssetting extends AppCompatActivity implements View.OnClickListener {
        Spinner whiteplayer,blackplayer,numberforminimax;
        Button submitbutton,cancelbutton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chesssetting);
        submitbutton = findViewById(R.id.submitbutton);
        submitbutton.setOnClickListener(this);
        cancelbutton = findViewById(R.id.cancelbutton);
        cancelbutton.setOnClickListener(this);
        whiteplayer=(Spinner) findViewById(R.id.whiteplayer);
        blackplayer=(Spinner) findViewById(R.id.blackplayer);
        numberforminimax=(Spinner)findViewById(R.id.numberforminimax);
        ArrayAdapter <String> whoplay = new ArrayAdapter<String>(chesssetting.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.whoplay));
        whoplay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter <String> getnumber = new ArrayAdapter<String>(chesssetting.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.numberofminimax));
        getnumber.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        whiteplayer.setAdapter(whoplay);
        blackplayer.setAdapter(whoplay);
        numberforminimax.setAdapter(getnumber);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submitbutton: {
                final String getwhiteplayer = whiteplayer.getSelectedItem().toString();
                final String getblackplayer = blackplayer.getSelectedItem().toString();
                final String getminimax =  numberforminimax.getSelectedItem().toString();

                Toast toast = Toast.makeText(getApplicationContext(), "Generated the baord", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent2 = new Intent(chesssetting.this, vscomputerchessgame.class);
                intent2.putExtra("white", getwhiteplayer);
                intent2.putExtra("black", getblackplayer);
                intent2.putExtra("number", getminimax);
                startActivity(intent2);
                break;
            }
            case R.id.cancelbutton:{
                finish();
                break;
            }
        }
    }
}
