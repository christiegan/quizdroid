package edu.washington.clgan.quizdroid;

import android.content.Context;
import android.content.SharedPreferences;
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
    private Timer t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        urlView = (EditText)findViewById(R.id.urlVal);
        minView = (EditText)findViewById(R.id.refreshid);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        url = sharedPref.getString("myUrl", "");
        minutes = sharedPref.getInt("myMinutes", 0);
        urlView.setText(url);
        String temp =  Integer.toString(minutes);
        minView.setText(temp);
    }



    public void onClick(View view){
        url = urlView.getText().toString();
        minutes = Integer.parseInt(minView.getText().toString());
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("myUrl", url);
        editor.putInt("myMinutes", minutes);
        editor.commit();
        if(t != null){
            t.cancel();
        }
        t = new Timer(false);
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
