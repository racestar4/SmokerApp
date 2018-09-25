package com.example.smokeapp.smokeapp;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import static android.app.DownloadManager.STATUS_RUNNING;

public class DataBaseIntent extends IntentService {

    private static final String TAG = "IntentService";

    public static final int STATUS_FINISHED = 1;
    public static final int STATUS_ERROR = 2;

    public static final String POST_PARAM_KEYVALUE_SEPARATOR = "=";
    public static final String POST_PARAM_SEPARATOR = "&";

    private URLConnection conn;


    public DataBaseIntent(){
        super(DataBaseIntent.class.getName());

    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d(TAG,"service started!");

        final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        String method = intent.getStringExtra("method");
        String username = intent.getStringExtra("username");

        Bundle bundle = new Bundle();

            try {
                String results = openConnection(method, username);

                /* Sending result back to activity */
                if (null != results) {
                    bundle.putString("result", results);
                    receiver.send(STATUS_FINISHED, bundle);
                }
            } catch (Exception e) {

                /* Sending error message back to activity */
                bundle.putString(Intent.EXTRA_TEXT, e.toString());
                receiver.send(STATUS_ERROR, bundle);
            }

        Log.d(TAG, "Service Stopping!");
        this.stopSelf();

    }

    private String openConnection(String method, String username) throws IOException {
        //StringBuffer für das zusammensetzen der URL
        StringBuffer dataBuffer = new StringBuffer();

        dataBuffer.append(URLEncoder.encode("method", "UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(method, "UTF-8"));

        dataBuffer.append(POST_PARAM_SEPARATOR);

        dataBuffer.append(URLEncoder.encode("username","UTF-8"));
        dataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        dataBuffer.append(URLEncoder.encode(username, "UTF-8"));

        //Adresse der PHP Schnittstelle für die Verbindung zur MySQL Datenbank
        URL url = new URL("https://informatik.hs-bremerhaven.de/pkampschoeer/app/reader.php");
        conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(dataBuffer.toString());
        wr.flush();

        return readResult();
    }

    private String readResult()throws IOException{
        String result = null;
        //Lesen der Rückgabewerte vom Server
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = null;
        //Solange Daten bereitstehen werden diese gelesen.
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

}
