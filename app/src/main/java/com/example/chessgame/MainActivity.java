package com.example.chessgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class MainActivity  extends AppCompatActivity  {


    Button vscomputer,online,playerprofile;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        vscomputer = findViewById(R.id.vscomputer);
        online = findViewById(R.id.online);
        playerprofile = findViewById(R.id.playerprofile);


        firebaseAuth= FirebaseAuth.getInstance();//firebase
        firestore=FirebaseFirestore.getInstance();
        userid=firebaseAuth.getCurrentUser().getUid();
        final FirebaseUser user= firebaseAuth.getCurrentUser();


    }



    public void onclick(View v){
        switch (v.getId()) {
            case R.id.vscomputer:
                Intent intent = new Intent(MainActivity.this,vscomputerchessgame.class);
                startActivity(intent);
                break;
            case R.id.online:
                Intent intent1 = new Intent(MainActivity.this,vscomputerchessgame.class);
                startActivity(intent1);
                break;
            case R.id.playerprofile:
                Intent intent2 = new Intent(MainActivity.this,useraccount.class);
                startActivity(intent2);
                break;

        }
    }

}
