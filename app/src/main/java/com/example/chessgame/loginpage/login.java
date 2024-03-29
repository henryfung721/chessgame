package com.example.chessgame.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chessgame.R;
import com.example.chessgame.mainpage.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    TextView login,registerbutton;
    EditText username,password;
    Button loginbutton;
    ProgressBar ProgressBar;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.login);
        username  = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginbutton = findViewById(R.id.loginbutton);
        registerbutton = findViewById(R.id.registerbutton);

        ProgressBar = findViewById(R.id.progressBar);

        firebaseAuth= FirebaseAuth.getInstance();//firebase

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = username.getText().toString().trim();
                String Password = password.getText().toString().trim();

                /*verify user's input data*/
                if(TextUtils.isEmpty(Email)){
                    username.setError("please input email");
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

                firebaseAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(login.this, "Logged in successfully",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(login.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(login.this, "Error" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            ProgressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this,registeraccount.class);
                startActivity(intent);
            }
        });
        checkalreadyhaveaccount();
    }
    public void checkalreadyhaveaccount(){
        /*check if the user already login*/
        if(firebaseAuth.getCurrentUser()!=null){

            Intent intent = new Intent(login.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
/*    public void onclick(View view){
        switch (view.getId()) {
            case R.id.loginbutton:

            case R.id.registerbutton:
        }
    }*/
}
