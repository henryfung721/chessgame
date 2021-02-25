package com.example.chessgame.mainpage.feedback_page;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chessgame.R;
import com.example.chessgame.mainpage.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class suggest_function_section extends AppCompatActivity {

    EditText feedback_edittext;
    Button nextbutton,canecelbutton;
    public static String section, suggest_function_feedback;
    TextView currentsection;

    FirebaseAuth firebaseAuth;
    String userid;
    FirebaseDatabase firebasedatabase;
    DatabaseReference databasereference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionaire5_page);

        firebasedatabase = FirebaseDatabase.getInstance();
        firebaseAuth= FirebaseAuth.getInstance();
        userid = firebaseAuth.getCurrentUser().getUid();

        currentsection=(TextView)findViewById(R.id.current_mode);
        feedback_edittext=(EditText)findViewById(R.id.feedback2);

        nextbutton=(Button)findViewById(R.id.submit_button);
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                section=currentsection.getText().toString();
                suggest_function_feedback=feedback_edittext.getText().toString();
                sent_to_database();
            }
        });
        canecelbutton=(Button)findViewById(R.id.cancel_button5);
        canecelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetpage();
            }
        });
        

    }

    private void sent_to_database() {
        DatabaseReference reference=firebasedatabase.getInstance().getReference().child("gamerecord").child(userid);
        databasereference = firebasedatabase.getReference("user feedback");
        databasereference.child(userid).child(user_login_questionaire.section).child(user_login_questionaire.q1description).setValue(user_login_questionaire.Q1answer);
        databasereference.child(userid).child(user_login_questionaire.section).child(user_login_questionaire.q2description).setValue(user_login_questionaire.Q2answer);
        databasereference.child(userid).child(user_login_questionaire.section).child(user_login_questionaire.q3description).setValue(user_login_questionaire.Q3answer);

        databasereference.child(userid).child(matching_mode_questionaire.section).child(matching_mode_questionaire.q1description).setValue(matching_mode_questionaire.Q1answer);
        databasereference.child(userid).child(matching_mode_questionaire.section).child(matching_mode_questionaire.q2description).setValue(matching_mode_questionaire.Q2answer);
        databasereference.child(userid).child(matching_mode_questionaire.section).child(matching_mode_questionaire.q3description).setValue(matching_mode_questionaire.Q3answer);

        databasereference.child(userid).child(chess_engine_questionaire.section).child(chess_engine_questionaire.q1description).setValue(chess_engine_questionaire.Q1answer);
        databasereference.child(userid).child(chess_engine_questionaire.section).child(chess_engine_questionaire.q2description).setValue(chess_engine_questionaire.Q2answer);
        databasereference.child(userid).child(chess_engine_questionaire.section).child(chess_engine_questionaire.q3description).setValue(chess_engine_questionaire.Q3answer);
        databasereference.child(userid).child(chess_engine_questionaire.section).child(chess_engine_questionaire.q4description).setValue(chess_engine_questionaire.Q4answer);

        databasereference.child(userid).child(feedback_section.section).setValue(feedback_section.getfeedback);

        databasereference.child(userid).child(section).setValue(suggest_function_feedback);

        Log.d("output",feedback_section.getfeedback);
        Toast.makeText(suggest_function_section.this, "message sent, thank you for your participate",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(suggest_function_section.this, MainActivity.class);
        startActivity(intent);
    }

    private void resetpage() {
        feedback_edittext.getText().clear();
    }

}
