package edu.washington.clgan.quizdroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class SettingsActivity extends AppCompatActivity {
    EditText urlView;
    EditText minView;

    private String url;
    private int minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        urlView = (EditText)findViewById(R.id.urlVal);
        minView = (EditText)findViewById(R.id.refreshid);
    }



    public void onClick(View view){
        url = urlView.getText().toString();
        minutes = Integer.parseInt(minView.getText().toString());
        Timer t = new Timer(false);
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Checking for new downloads...", Toast.LENGTH_LONG).show();
                    }
                });

            }
        }, minutes * 60 * 1000, minutes * 60 * 1000);
        finish();
    }
}
