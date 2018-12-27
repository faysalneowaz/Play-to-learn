package com.alopex.faysalneowaz.playtolearn;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button playbutton,button1,button2,button3,button4,playAgainbutton;
    TextView sumtext,resultextview,pointsTextView,timerTextview;
    ArrayList<Integer> answers = new ArrayList<Integer>();

    int locationOfCorrectanswer,incorrectAnswer;
    int score=0;
    int numberofqustion=0;
    RelativeLayout gameRelativelayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.alopex.faysalneowaz.playtolearn.R.layout.activity_main);
        playbutton = (Button) findViewById(R.id.start);
        sumtext=(TextView)findViewById(com.alopex.faysalneowaz.playtolearn.R.id.sumTextView);
        resultextview=(TextView)findViewById(R.id.resulttext);
        pointsTextView=(TextView)findViewById(R.id.scoreTextView);
        timerTextview=(TextView)findViewById(R.id.timerTextView);
         button1=(Button)findViewById(R.id.resultbutton1);
         button2=(Button)findViewById(R.id.resultbutton2);
         button3=(Button)findViewById(R.id.resultbutton3);
         button4=(Button)findViewById(R.id.resultbutton4);
         playAgainbutton=(Button)findViewById(R.id.playAgainButton);
         gameRelativelayout=(RelativeLayout)findViewById(R.id.gameRelativelayout);








        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playbutton.setVisibility(View.INVISIBLE);
                gameRelativelayout.setVisibility(RelativeLayout.VISIBLE);

                playAgain(findViewById(R.id.playAgainButton));
            }
        });


    }

    public void choseanswer(View view){

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectanswer))){

            score++;
            resultextview.setText("Correct");

        }else {

            resultextview.setText("Wrong");
        }
        numberofqustion++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberofqustion));
        generateQustion();
    }

    public void generateQustion(){

        Random rand = new Random();
        int x = rand.nextInt(41);
        int y = rand.nextInt(41);

        answers.clear();

        sumtext.setText(Integer.toString(x) + " + " + Integer.toString(y));

        locationOfCorrectanswer=rand.nextInt(4);

        for (int i =0; i<4;i++){

            if (i==locationOfCorrectanswer){

                answers.add(x+y);
            }else {
                incorrectAnswer=rand.nextInt(81);

                while (incorrectAnswer==x+y){
                    incorrectAnswer=rand.nextInt(81);

                }

                answers.add(incorrectAnswer);
            }

        }
        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));


    }


    public void playAgain(View view){

        score=0;
        numberofqustion=0;
        timerTextview.setText("30s");
        pointsTextView.setText("0/0");
        resultextview.setText("");
        playAgainbutton.setVisibility(View.INVISIBLE);

        generateQustion();

        new CountDownTimer(30100, 1000){


            @Override
            public void onTick(long millsUntilFinished) {

                timerTextview.setText(String.valueOf(millsUntilFinished/1000) + "s");

            }

            @Override
            public void onFinish() {
                playAgainbutton.setVisibility(View.VISIBLE);
                timerTextview.setText("0s");

                if (score <= 10){
                    resultextview.setText("Poor: " + Integer.toString(score) + "/" + Integer.toString(numberofqustion));
                }else if (score <= 20){
                    resultextview.setText("Not bad: " + Integer.toString(score) + "/" + Integer.toString(numberofqustion));
                }else {
                    resultextview.setText("Excellent: " + Integer.toString(score) + "/" + Integer.toString(numberofqustion));
                }


            }
        }.start();


    }




}
