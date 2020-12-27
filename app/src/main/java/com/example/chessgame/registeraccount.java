package com.example.chessgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;

public class registeraccount extends AppCompatActivity {


    public static final String TAG = "TAG";
    TextView login,registerbutton;
    EditText username,email,password,confirmedpassword;
    Button loginbutton;
    ProgressBar ProgressBar;
    String userid;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    boolean verification =false;
    String Email ;
    String Password ;
    String name ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeraccount);

        login = findViewById(R.id.login);
        username  = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmedpassword = findViewById(R.id.confirmepassword);
        loginbutton = findViewById(R.id.loginbutton);
        registerbutton = findViewById(R.id.registerbutton);

        ProgressBar = findViewById(R.id.progressBar);
        firebaseAuth= FirebaseAuth.getInstance();//firebase
        firestore=FirebaseFirestore.getInstance();

        //checkalreadyhaveaccount();


        /*user click the loginbutton*/
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //change input data to string
                Email = email.getText().toString().trim();
                Password = password.getText().toString().trim();
                name = username.getText().toString();

                /*verify user's input data*/
                dataverification(Email,Password,name);
                if(verification==true) {
                    addfirebaseauthentication(Email,Password,name);
                }

            }
        });


        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(registeraccount.this,login.class);
                startActivity(intent);
            }});
    }

    public void checkalreadyhaveaccount(){
        /*check if the user already login*/
        if(firebaseAuth.getCurrentUser()!=null){

            Intent intent = new Intent(registeraccount.this,useraccount.class);
            startActivity(intent);
            finish();
        }
    }
    public void dataverification(String Email,String Password,String name){
        /*check user input correct data */
        if(TextUtils.isEmpty(Email)){
            email.setError("please input email");
            return;
        }else if(TextUtils.isEmpty(Password)){
            password.setError("please input Password");
            return;
        }else if(Password.length()< 8){
            password.setError("Password should be more than 8 letters");
            return;
        }/*else if(password!=confirmedpassword){
                confirmedpassword.setError("confirmed password should be same as passowrd");
                return;
            }*/else{
            ProgressBar.setVisibility(View.VISIBLE);
            verification=true;
        }
    }



    public void addfirebaseauthentication(final String Email, String Password, final String name){
        /*send the data to friebase*/
        firebaseAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser firebaseuser = firebaseAuth.getCurrentUser();
                    firebaseuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(registeraccount.this, "email sent", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "fail" + e.getMessage());
                        }
                    });
                    Toast.makeText(registeraccount.this, "User Created.", Toast.LENGTH_SHORT).show();


                    userid = firebaseAuth.getCurrentUser().getUid();

                    DocumentReference documentReference = firestore.collection("users").document(userid);
                    Map<String, Object> user = new HashMap<>();
                    user.put("name", name);
                    user.put("email", Email);
                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "onSuccess: user profile is created for " + userid);
                            Intent intent = new Intent(registeraccount.this,MainActivity.class);
                            startActivity(intent);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onfailure: " + e.toString());
                        }
                    });


                } else {
                    ProgressBar.setVisibility(View.GONE);
                    Toast.makeText(registeraccount.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}

