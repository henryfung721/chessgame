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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterPage extends AppCompatActivity {


    public static final String TAG = "TAG";
    TextView login,registerbutton;
    EditText username,email,password,confirmedpassword;
    Button loginbutton;
    ProgressBar ProgressBar;
    String userid;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        login = findViewById(R.id.login);
        username  = findViewById(R.id.email1);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmedpassword = findViewById(R.id.confirmepassword);
        loginbutton = findViewById(R.id.loginbutton);
        registerbutton = findViewById(R.id.registerbutton);

        ProgressBar = findViewById(R.id.progressBar);
        firebaseAuth= FirebaseAuth.getInstance();//firebase
        firestore=FirebaseFirestore.getInstance();


        /*check if the user already login*/
        if(firebaseAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        /*user click the loginbutton*/
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Email = email.getText().toString().trim();
                final String Password = password.getText().toString().trim();
                final String name = username.getText().toString();
                /*verify user's input data*/
                if(TextUtils.isEmpty(Email)){
                    email.setError("please input email");
                    return;
                }
                if(TextUtils.isEmpty(Password)){
                    password.setError("please input Password");
                    return;
                }else if(Password.length()< 8){
                    password.setError("Password should be more than 8 letters");
                    return;
                }
                ProgressBar.setVisibility(View.VISIBLE);

                /*send data to firebase*/
                firebaseAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterPage.this, "User Created.",Toast.LENGTH_SHORT).show();
                            userid = firebaseAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = firestore.collection("users").document(userid);
                            Map<String,Object> user = new HashMap<>();
                            user.put("name",name);
                            user.put("email",Email);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG,"onSuccess: user profile is created for "+userid);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG,"onfailure: "+ e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(RegisterPage.this, "Error" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            ProgressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });


        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginPage.class));
            }});
        }
    }

