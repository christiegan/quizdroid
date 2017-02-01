package edu.washington.clgan.quizdroid;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import layout.TopicFragment;

public class TopicDescr extends AppCompatActivity {
    private int id;
    private int currentq; // current questions
    private int totalq;   // the number of answered questions
    private int correctq; // the number of correctly answer questions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_descr);

        Intent intent = getIntent();
        id = intent.getIntExtra("MyData", 0);

        TopicFragment topicfrag = TopicFragment.newInstance(id, "junk");
        FragmentTransaction tx = getFragmentManager().beginTransaction();
        tx.replace(R.id.fragment_placeholder, topicfrag);
        tx.commit();
    }


}
