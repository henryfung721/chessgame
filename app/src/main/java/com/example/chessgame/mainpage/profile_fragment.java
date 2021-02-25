package com.example.chessgame.mainpage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.chessgame.R;
import com.example.chessgame.loginpage.login;
import com.example.chessgame.mainpage.feedback_page.user_login_questionaire;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class profile_fragment extends Fragment {

    static TextView verify,name,email,dateofbirth,level;
    Button verifybutton,logout,userfeedback;
    ImageView photo;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    String userid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fregment_profile,container,false);


        verify = (TextView)view.findViewById(R.id.verify);
        name  =(TextView) view.findViewById(R.id.name);
        email = (TextView)view.findViewById(R.id.email);
        dateofbirth =(TextView) view.findViewById(R.id.dateofbirth);
        level =(TextView) view.findViewById(R.id.level);
        verifybutton = view.findViewById(R.id.verifybutton);
        userfeedback=view.findViewById(R.id.userfeedback);
        logout = view.findViewById(R.id.logout);



        photo = view.findViewById(R.id.photo);
        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            firebaseAuth = FirebaseAuth.getInstance();//firebase
        }
        firestore=FirebaseFirestore.getInstance();
        userid=firebaseAuth.getCurrentUser().getUid();
        final FirebaseUser user= firebaseAuth.getCurrentUser();


        userfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), user_login_questionaire.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FirebaseAuth.getInstance().getCurrentUser() != null){
                    alertsignout();
                }
            }
        });


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
        documentReference.addSnapshotListener( new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                name.setText(documentSnapshot.getString("name"));
                email.setText(documentSnapshot.getString("email"));
            }
        });
/*
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                name.setText(documentSnapshot.getString("name"));
                email.setText(documentSnapshot.getString("email"));
                //Doing work
            }
        });
*/

        return view;

    }



    public void alertsignout()
    {
        AlertDialog.Builder alertDialog2 = new
                AlertDialog.Builder(
                getActivity());

        // Setting Dialog Title
        alertDialog2.setTitle("Confirm SignOut");

        // Setting Dialog Message
        alertDialog2.setMessage("Are you sure you want to Signout?");

        // Setting Positive "Yes" Btn
        alertDialog2.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        firebaseAuth.getInstance().signOut();
                        Intent i = new Intent(getActivity(),
                                login.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                });

        // Setting Negative "NO" Btn
        alertDialog2.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        Toast.makeText(getContext(),
                                "You clicked on NO", Toast.LENGTH_SHORT)
                                .show();
                        dialog.cancel();
                    }
                });

        // Showing Alert Dialog
        alertDialog2.show();


    }


}
