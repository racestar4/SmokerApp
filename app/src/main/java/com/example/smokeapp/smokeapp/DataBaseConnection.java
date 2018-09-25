package com.example.smokeapp.smokeapp;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class DataBaseConnection extends AsyncTask<String, Void, String> {

    public static final String POST_PARAM_KEYVALUE_SEPARATOR = "=";
    public static final String POST_PARAM_SEPARATOR = "&";

    private String result;

    private URLConnection conn;

    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
        String username = params[1];
        try {
            openConnection(method,username);
            result = readResult();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Öffnet eine Verbindung {@link URLConnection}.
     * @throws IOException
     */
    private void openConnection(String method, String username) throws IOException{
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
    }

    /**
     * Ließt das Ergebnis aus der geöffneten Verbindung.
     * @return liefert ein String mit dem gelesenen Werten.
     * @throws IOException
     */
    public String readResult()throws IOException{
        //Lesen der Rückgabewerte vom Server
        System.out.println("INPUTSTREAM");
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = null;
        //Solange Daten bereitstehen werden diese gelesen.
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        System.out.println("RESULT: "+result);
        if(!isBlank(result)) {
            //MainActivity.result = result;

        }
    }

    private boolean isBlank(String value){
        return value == null || value.trim().isEmpty();
    }


}
