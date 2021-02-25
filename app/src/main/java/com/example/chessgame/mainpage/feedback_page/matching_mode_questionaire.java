package com.example.chessgame.mainpage.feedback_page;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chessgame.R;

public class matching_mode_questionaire extends AppCompatActivity {


    TextView Q1questoin,Q1description;
    TextView Q2questoin,Q2description;
    TextView Q3questoin,Q3description;
    SeekBar Q1seekBar,Q2seekBar,Q3seekBar;

    Button nextbutton,canecelbutton;

    TextView currentsection;
    public static String section,q1description,q2description,q3description;
    public static String Q1answer,Q2answer,Q3answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionaire2_page);

        currentsection=(TextView)findViewById(R.id.current_mode);
        Q1questoin=(TextView)findViewById(R.id.Q2_1_question);
        Q2questoin=(TextView)findViewById(R.id.Q2_2_question);
        Q3questoin=(TextView)findViewById(R.id.Q2_3_question);

        Q1description=(TextView)findViewById(R.id.Q2_1_description);
        Q2description=(TextView)findViewById(R.id.Q2_2_description);
        Q3description=(TextView)findViewById(R.id.Q2_3_description);

        Q1seekBar=(SeekBar)findViewById(R.id.Q2_1_seekBar);
        Q2seekBar=(SeekBar)findViewById(R.id.Q2_2_seekBar);
        Q3seekBar=(SeekBar)findViewById(R.id.Q2_3_seekBar);

        nextbutton=(Button)findViewById(R.id.next_button2);
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next_button_function();
            }
        });
        canecelbutton=(Button)findViewById(R.id.cancel_button2);
        canecelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetpage();
            }
        });


        resetpage();

    }

    private void next_button_function() {
        section=currentsection.getText().toString();
        q1description=Q1questoin.getText().toString().substring(0, Q1questoin.getText().toString().length() - 1);
        q2description=Q2questoin.getText().toString().substring(0, Q2questoin.getText().toString().length() - 1);
        q3description=Q3questoin.getText().toString().substring(0, Q3questoin.getText().toString().length() - 1);

        Q1answer=Q1description.getText().toString();
        Q2answer=Q2description.getText().toString();
        Q3answer=Q3description.getText().toString();

        Intent intent = new Intent(matching_mode_questionaire.this,chess_engine_questionaire.class);
        startActivity(intent);
    }

    private void resetpage() {
        setseekbarfunctions(Q1seekBar,Q1description);
        setseekbarfunctions(Q2seekBar,Q2description);
        setseekbarfunctions(Q3seekBar,Q3description);
    }

    private void setseekbarfunctions(SeekBar get_seek_bar, final TextView get_test_view) {
        get_seek_bar.setProgress(0);
        get_seek_bar.incrementProgressBy(1);
        get_seek_bar.setMax(5);
        get_seek_bar.setProgress(0);
        get_test_view.setText("no comment");
        get_seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress==0){
                    get_test_view.setText("no comment");
                }
                if(progress==1){
                    get_test_view.setText("highly dissatisfied");
                }
                if(progress==2){
                    get_test_view.setText("dissatisfied");
                }
                if(progress==3){
                    get_test_view.setText("neutral");
                }
                if(progress==4){
                    get_test_view.setText("satisfied");
                }
                if(progress==5){
                    get_test_view.setText("highly satisfied");
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
