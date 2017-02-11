package edu.washington.clgan.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listview = (ListView) findViewById(R.id.listView);

        QuizApp mApplication = (QuizApp)getApplicationContext();
        ArrayList<Topic> topics = mApplication.getTopics();
        String[] values = new String[3];
        int[] images = new int[3];
        for(int i = 0; i < topics.size(); i++){
            values[i] = topics.get(i).getTitle();
            images[i] = topics.get(i).getImageID();
        }

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        CustomList adapter = new CustomList(MainActivity.this, values, images);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, TopicDescr.class);
                intent.putExtra("MyData", position);
                startActivity(intent);
            }
        });
    }
}
