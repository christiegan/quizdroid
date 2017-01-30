package edu.washington.clgan.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class TopicDescr extends AppCompatActivity {
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_descr);

        Intent intent = getIntent();
        id = intent.getIntExtra("MyData", 0);
        TextView description = (TextView)findViewById(R.id.textView);
        if(id == 0){
            description.setText("You will begin taking a Mathematics quiz. Mathematics is the study of topics such as quantity (numbers), structure, space, and change. There are 5 questions.");
        }else if(id == 1){
            description.setText("You will begin taking an English quiz. The meaning of English as a subject is to educate on the English language in " +
                    "general and to aid in the understanding and employment of the language. The subject of English is most often split into two main topics; English Literature and English Language. There are 5 questions");
        }else{
            description.setText("You will begin taking a Physics quiz. Physics is the branch of science concerned with the nature and properties of matter and energy. There are 5 questions");
        }
    }

    public void beginQuiz(View view){
        Intent intent = new Intent(TopicDescr.this, question.class);
        intent.putExtra("MyData", id);
        startActivity(intent);
    }

}
