package edu.washington.clgan.quizdroid;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by GanHome on 2/25/2017.
 */
public class DownloadFileFromURL extends AsyncTask<String, String, String> {
    boolean fSuccess = false;
    private Context mContext;

    public DownloadFileFromURL (Context context){
        mContext = context;
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
    protected void onPreExecute() {
        super.onPreExecute();
        boolean checkInternet = isNetworkStatusAvialable(mContext);
        if(checkInternet == false){
            Log.e("quizdroidlog", "no internet");
            new AlertDialog.Builder(mContext)
                    .setTitle("Quizdroid")
                    .setMessage("You do not have Internet. Please turn on the Internet.")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else
            Toast.makeText(mContext, "Download started", Toast.LENGTH_LONG).show();
    }


    protected String doInBackground(String... f_url) {
        int count;
        try {
            URL url = new URL(f_url[0]);
            URLConnection connection = url.openConnection();
            connection.connect();

            // this will be useful so that you can show a tipical 0-100%
            // progress bar
            int lengthOfFile = connection.getContentLength();
            if (lengthOfFile == -1) {
                fSuccess = false;
                Log.e("quizdroidlog", "download failed");
                return "failed";
            }
            // download the file
            InputStream input = new BufferedInputStream(url.openStream(),
                    8192);

            // Output stream
            OutputStream output = new FileOutputStream(Environment.getExternalStorageDirectory().toString() + "/questions.json");

            byte data[] = new byte[1024];

            long total = 0;

            while ((count = input.read(data)) != -1) {
                total += count;

                // writing data to file
                output.write(data, 0, count);
            }

            // flushing output
            output.flush();

            // closing streams
            output.close();
            input.close();

        }catch(Exception e){
            fSuccess = false;
            Log.e("quizdroidlog", e.getMessage());
            return "failed";
        }

        fSuccess = true;
        Log.i("quizdroidlog", "Download finished");
        return "succeeded";
    }

    protected void onProgressUpdate(String... progress) {
        // setting progress percentage
        Toast.makeText(mContext, "Download in progress", Toast.LENGTH_LONG).show();

    }


    @Override
    protected void onPostExecute(String file_url) {
        // dismiss the dialog after the file was downloaded
        if (fSuccess) {
            Toast.makeText(mContext, "Download was successful", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(mContext, "Download failed", Toast.LENGTH_LONG).show();
        }

    }
}