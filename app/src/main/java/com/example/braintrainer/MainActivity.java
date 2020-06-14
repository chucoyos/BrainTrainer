package com.example.braintrainer;
import android.os.CountDownTimer;
import android.os.ResultReceiver;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    ArrayList<Integer> answers = new ArrayList<>();

    TextView scoreTextView, sumTextView, resultTextView, timerTextView;
    Button button0, button1, button2, button3, playAgainButton;
    int score = 0;
    int numberOfQuestions = 0;
    int locationOfCorrectAnswer;
    ConstraintLayout gameLayout;
    GridLayout gridLayout;

    public void playAgain(View view){
        numberOfQuestions = 0;
        score = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score) + " / " + Integer.toString(numberOfQuestions));
        playAgainButton.setVisibility(View.GONE);
        resultTextView.setText("");
        gridLayout.setVisibility(View.VISIBLE);
        goButton.setVisibility(View.GONE);
        newQuestion();

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                gridLayout.setVisibility(View.GONE);
                goButton.setVisibility(View.VISIBLE);
                resultTextView.setText("");

            }
        }.start();
    }

    public void chooseAnswer(View view){
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            score++;
            resultTextView.setText("Correct!  :)");

        } else {
            Log.i("Wrong", "You fail!");
            resultTextView.setText("Wrong!  :(");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();
    }

    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.timerTextView));
        gameLayout.setVisibility(View.VISIBLE);
    }

    public void newQuestion(){
        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);

        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));
        locationOfCorrectAnswer= random.nextInt(4);
        answers.clear();

        for (int i = 0; i < 4; i++){
            if (i == locationOfCorrectAnswer){
                answers.add(a + b);
            } else {
                int wrongAnswer = random.nextInt(41);

                while (wrongAnswer == a+b){
                    wrongAnswer = random.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView = findViewById(R.id.sumTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        goButton = findViewById(R.id.goButton);
        playAgainButton = findViewById(R.id.playAgainButton);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoretTextView);
        timerTextView = findViewById(R.id.timerTextView);
        gameLayout = findViewById(R.id.gameLayout);
        gridLayout = findViewById(R.id.gridLayout);
        gameLayout.setVisibility(View.INVISIBLE);


    }
}
