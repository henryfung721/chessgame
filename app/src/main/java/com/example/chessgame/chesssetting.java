package com.example.chessgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class chesssetting extends AppCompatActivity implements View.OnClickListener {
        Spinner whiteplayer,blackplayer;
        Button submitbutton,cancelbutton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chesssetting);
        submitbutton = findViewById(R.id.submittbutton);
        submitbutton.setOnClickListener(this);
        cancelbutton = findViewById(R.id.cancelbutton);
        cancelbutton.setOnClickListener(this);
        whiteplayer=(Spinner) findViewById(R.id.whiteplayer);
        blackplayer=(Spinner) findViewById(R.id.blackplayer);
        ArrayAdapter <String> whoplay = new ArrayAdapter<String>(chesssetting.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.whoplay));
        whoplay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        whiteplayer.setAdapter(whoplay);
        blackplayer.setAdapter(whoplay);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submittbutton: {
                final String getwhiteplayer = whiteplayer.getSelectedItem().toString();
                final String getblackplayer = blackplayer.getSelectedItem().toString();
                Log.d("changedplayer", "Value:computermove" + getwhiteplayer);
                Toast toast = Toast.makeText(getApplicationContext(), getwhiteplayer, Toast.LENGTH_SHORT);
                toast.show();
                String whiteiscomputer ="";
                String blackiscomputer="";
                if(whiteplayer.equals("human")) {
                     whiteiscomputer= "human";
                }else if(whiteplayer.equals("computer")) {
                     whiteiscomputer = "computer";
                }
                if(blackplayer.equals("human")) {
                    blackiscomputer = "human";
                }else if(blackplayer.equals("computer")) {
                    blackiscomputer = "computer";
                }
                Intent intent2 = new Intent(chesssetting.this, vscomputerchessgame.class);
                intent2.putExtra("white", getwhiteplayer);
                intent2.putExtra("black", getblackplayer);
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
