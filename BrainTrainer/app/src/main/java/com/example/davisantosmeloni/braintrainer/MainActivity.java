package com.example.davisantosmeloni.braintrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;

    public void start (View view) {
        startButton = (Button) findViewById(R.id.startButton);
        startButton.setVisibility(view.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        TextView sumTextView = (TextView) findViewById(R.id.sumTextView);
        Button button0 = (Button) findViewById(R.id.button0);

        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);
        button0.setText(Integer.toString(answers.get(0)));
        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = random.nextInt(4);

        int incorrectAnswer;

        for (int i=0; 0 < 4; i++) {

            if(i == locationOfCorrectAnswer) {

                answers.add(a + b);

            } else {

                incorrectAnswer = random.nextInt(41);

                while(incorrectAnswer == a + b) {

                    incorrectAnswer = random.nextInt(41);
                }

                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));



    }
}
