package edu.washington.clgan.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class question extends AppCompatActivity {
    private int id;
    private int numOfCorrect;
    private int numOfAnswer;
    private RadioGroup radioGroup;

    @Override
    protected void onRestart(){
        super.onRestart();
        setQuestion();
    }

    @Override
    protected void onResume(){
        super.onResume();
        setQuestion();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent intent = getIntent();
        numOfAnswer = intent.getIntExtra("numOfAnswer", 0);
        setQuestion();
    }

    private void setQuestion(){
        Intent intent = getIntent();
        id = intent.getIntExtra("MyData", 0);
        TextView description = (TextView)findViewById(R.id.textView2);
        RadioButton answer1 = (RadioButton)findViewById(R.id.radioButton1);
        answer1.setChecked(false);
        RadioButton answer2 = (RadioButton)findViewById(R.id.radioButton2);
        answer2.setChecked(false);
        RadioButton answer3 = (RadioButton)findViewById(R.id.radioButton3);
        answer3.setChecked(false);
        RadioButton answer4 = (RadioButton)findViewById(R.id.radioButton4);
        answer4.setChecked(false);
        Button submitButton = (Button)findViewById(R.id.button2);
        submitButton.setEnabled(false);
        if(id == 0) {
            description.setText("QUESTION " + Integer.toString(numOfAnswer+1)+": Maggie made 4 trips to visit her grandmother. She drove 303.4 miles in all. How far did Maggie drive on each trip?");
            answer1.setText("74.05");
            answer2.setText("75.85");
            answer3.setText("62.9");
            answer4.setText("67.3");
        }else if(id == 1){
            description.setText("QUESTION " + Integer.toString(numOfAnswer+1)+": Sorry, you can't borrow my pencil. I ..... it myself.");
            answer1.setText("was using");
            answer2.setText("using");
            answer3.setText("use");
            answer4.setText("am using");
        }else{
            description.setText("QUESTION " + Integer.toString(numOfAnswer+1)+": The tendency of objects to resist changes in motion.");
            answer1.setText("Inertia");
            answer2.setText("Force");
            answer3.setText("Mechanical Equilibrium");
            answer4.setText("Equilibrium rule");
        }
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Button submitButton = (Button)findViewById(R.id.button2);
                submitButton.setEnabled(true);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(numOfAnswer == 0) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }else{
            Intent intent = new Intent(getApplicationContext(), question.class);
            intent.putExtra("numOfAnswer", --numOfAnswer);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        return;
    }


    public void submitAnswer(View view){
        Intent intent = new Intent(question.this, answer.class);
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        View radioButton = radioGroup.findViewById(radioButtonID);
        int userAnswer = radioGroup.indexOfChild(radioButton);
        intent.putExtra("MyData", id);
        intent.putExtra("selectedAnswer", userAnswer);
        numOfAnswer++;
        if(id == 0){
            if(userAnswer == 1){
                numOfCorrect++;
            }
        }else if(id == 1){
            if(userAnswer == 3){
                numOfCorrect++;
            }
        }else{
            if(userAnswer == 0){
                numOfCorrect++;
            }
        }
        intent.putExtra("numOfAnswer", numOfAnswer);
        intent.putExtra("numOfCorrect", numOfCorrect);
        startActivity(intent);
    }
}
