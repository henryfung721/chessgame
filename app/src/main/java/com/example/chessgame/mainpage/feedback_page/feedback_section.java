package com.example.chessgame.mainpage.feedback_page;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chessgame.R;

public class feedback_section extends AppCompatActivity {

    EditText feedback_edittext;
    Button nextbutton,canecelbutton;
    public static String section,getfeedback;
    TextView currentsection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionaire4_page);

        currentsection=(TextView)findViewById(R.id.current_mode);
        feedback_edittext=(EditText)findViewById(R.id.feedback);
        nextbutton=(Button)findViewById(R.id.next_button4);
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { next_button_function(); }
        });
        canecelbutton=(Button)findViewById(R.id.cancel_button4);
        canecelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetpage();
            }
        });
        

    }

    private void next_button_function() {
        section= currentsection.getText().toString();
        getfeedback=feedback_edittext.getText().toString();
        Intent intent = new Intent(feedback_section.this,suggest_function_section.class);
        startActivity(intent);
    }

    private void resetpage() {
        feedback_edittext.getText().clear();
    }

}
