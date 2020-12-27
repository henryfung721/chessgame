
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

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


import javax.annotation.Nullable;

public class useraccount  extends AppCompatActivity  {

    TextView verify,name,email,dateofbirth,level;
    Button verifybutton,logout;
    ImageView photo;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useraccount);

        verify = findViewById(R.id.verify);
        name  = findViewById(R.id.name);
        email = findViewById(R.id.email);
        dateofbirth = findViewById(R.id.dateofbirth);
        level = findViewById(R.id.level);
        verifybutton = findViewById(R.id.verifybutton);
        logout = findViewById(R.id.vscomputer);
        photo = findViewById(R.id.photo);
        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            firebaseAuth = FirebaseAuth.getInstance();//firebase
        }
        firestore=FirebaseFirestore.getInstance();
        userid=firebaseAuth.getCurrentUser().getUid();
        final FirebaseUser user= firebaseAuth.getCurrentUser();

        if(user.isEmailVerified()){
            verify.setVisibility(View.VISIBLE);
            verifybutton.setVisibility(View.VISIBLE);
            verifybutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(verify.getContext(), "email sent" , Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("TAG","fail"+e.getMessage());
                        }
                    });
                }
            });

        }
        DocumentReference documentReference = firestore.collection("users").document(userid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                name.setText(documentSnapshot.getString("name"));
                email.setText(documentSnapshot.getString("email"));
            }
        });
    }



    public void onclick(View v){
        switch (v.getId()) {
            case R.id.vscomputer:
                if(FirebaseAuth.getInstance().getCurrentUser() != null){
                firebaseAuth.getInstance().signOut();
                onStop();
                Intent intent = new Intent(useraccount.this, login.class);
                startActivity(intent);

                // the code keep trying to access the data, need to destroy the current page to prevent the app crashing
                }
                    /*AuthUI.getInstance()
                            .signOut(this)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                public void onComplete(@NonNull Task<Void> task) {
                                    // user is now signed out
                                    Intent intent = new Intent(useraccount.this, vscomputerchessgame.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });*/



                break;

        }
    }

}
