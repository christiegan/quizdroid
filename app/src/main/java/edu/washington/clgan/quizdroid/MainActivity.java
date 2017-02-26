package edu.washington.clgan.quizdroid;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    static final int AIRPLANE_REQUEST = 1;  // The request code
    boolean gotPermission = false;
    private String url;
    private static ArrayList<Topic> topics;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_EXTERNAL_STORAGE) {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                gotPermission = true;
                if (topics==null)
                    downloadAndDisplay();

            } else {

                // permission denied, boo! Disable the
                // functionality that depends on this permission.
            }
            return;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        SharedPreferences sharedPref = getSharedPreferences("settings", Context.MODE_PRIVATE);
        url = sharedPref.getString("myUrl", "http://tednewardsandbox.site44.com/questions.json");
        int minutes = sharedPref.getInt("myMinutes", 30);

        Intent intent = getIntent();
        if (intent!=null) {
            String newstart = intent.getStringExtra("Anything");
            if (newstart!=null && newstart.equals("ok"))
                downloadAndDisplay();
        }

        boolean checkAirplaneMode = Settings.System.getInt(
                MainActivity.this.getContentResolver(),
                Settings.System.AIRPLANE_MODE_ON, 0) == 1;
        if(checkAirplaneMode){
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Quizdroid")
                    .setMessage("Do you want to turn Airplane mode off?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), AIRPLANE_REQUEST);
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else {
            //wait for permission

            if (gotPermission) {
                downloadAndDisplay();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == AIRPLANE_REQUEST) {
                downloadAndDisplay();
        }
    }

    private void downloadAndDisplay() {
        int retry = 0;
        boolean success = false;
        while (retry <=1 && !success ) {
            String result = "";
            try {
                result = new DownloadFileFromURL(MainActivity.this).execute(url).get();
            } catch (ExecutionException ex) {
                Log.e("quizdroidlog", ex.getMessage());
                success = false;
            } catch (InterruptedException in) {
                Log.e("quizdroidlog", in.getMessage());
                success = false;
            }
            if (result == "failed") {
                if (retry ==0 ) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Quizdroid")
                            .setMessage("Download failed. Would you like to retry?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                    System.exit(0);
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }

            } else {
                success = true;
            }
            retry++;
        }

        if(success) {
            TopicRepository tr = new TopicRepository();
            topics = tr.getTopics();

            final ListView listview = (ListView) findViewById(R.id.listView);

            QuizApp mApplication = (QuizApp) getApplicationContext();
            mApplication.setTopics(topics);
            String[] values = new String[3];
            int[] images = new int[3];
            for (int i = 0; i < topics.size(); i++) {
                values[i] = topics.get(i).getTitle();
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


    private static boolean isNetworkStatusAvialable (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
        {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if(netInfos != null)
                if(netInfos.isConnected())
                    if (netInfos.isAvailable())
                        return true;
        }
        return false;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
