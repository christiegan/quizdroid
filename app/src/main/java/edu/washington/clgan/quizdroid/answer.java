package edu.washington.clgan.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class answer extends AppCompatActivity {
    private int id;
    private int choiceId;
    private int numOfAnswer;
    private int numOfCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        Intent intent = getIntent();
        choiceId = intent.getIntExtra("selectedAnswer", 0);
        TextView selectedAnswer = (TextView)findViewById(R.id.textView3);
        id = intent.getIntExtra("MyData", 0);
        TextView answer = (TextView)findViewById(R.id.textView4);
        if(id == 0){
            if(choiceId == 0){
                selectedAnswer.setText("Your selected answer is choice A: 74.05");
            }else if(choiceId == 1){
                selectedAnswer.setText("Your selected answer is choice B: 75.85");
            }else if(choiceId == 2){
                selectedAnswer.setText("Your selected answer is choice C: 62.9");
            }else if(choiceId == 3){
                selectedAnswer.setText("Your selected answer is choice D: 67.3");
            }
            answer.setText("The correct answer is choice B: 75.85");
        }else if(id == 1){
            if(choiceId == 0){
                selectedAnswer.setText("Your selected answer is choice A: was using");
            }else if(choiceId == 1){
                selectedAnswer.setText("Your selected answer is choice B: using");
            }else if(choiceId == 2){
                selectedAnswer.setText("Your selected answer is choice C: use");
            }else if(choiceId == 3){
                selectedAnswer.setText("Your selected answer is choice D: am using");
            }
            answer.setText("The correct answer is choice D: am using");
        }else if(id == 2){
            if(choiceId == 0){
                selectedAnswer.setText("Your selected answer is choice A: Inertia");
            }else if(choiceId == 1){
                selectedAnswer.setText("Your selected answer is choice B: Force");
            }else if(choiceId == 2){
                selectedAnswer.setText("Your selected answer is choice C: Mechanical Equilibrium");
            }else if(choiceId == 3){
                selectedAnswer.setText("Your selected answer is choice D: Equilibrium rule");
            }
            answer.setText("The correct answer is choice A: Inertia");
        }

        numOfAnswer = intent.getIntExtra("numOfAnswer", 0);
        numOfCorrect = intent.getIntExtra("numOfCorrect", 0);
        TextView score = (TextView)findViewById(R.id.textView5);
        String displayScore = "You have " + numOfCorrect + " out of " + numOfAnswer + " correct";
        Button next = (Button)findViewById(R.id.button3);
        if(numOfAnswer == 5){
            next.setText("Finish");
        }
        score.setText(displayScore);
    }

    public void finishQuiz(View view){
        if(numOfAnswer < 5){
            finish();
        }else {
            Intent intent = new Intent(answer.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
