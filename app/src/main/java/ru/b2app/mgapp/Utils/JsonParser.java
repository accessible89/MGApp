package ru.b2app.mgapp.Utils;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by acces on 13.06.2016.
 */
public class JsonParser {
    final String TAG = "JsonParser.java";
    static InputStream inputStream = null;
    static JSONObject jObj = null;
    static String json = "";

    public JSONObject getJSONFromUrl(String urlSource) {
        //make HTTP request
        try {
            URL url = new URL(urlSource);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);
            inputStream = new BufferedInputStream(urlConnection.getInputStream());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Read JSON data from inputStream
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            inputStream.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e(TAG, "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e(TAG, "Error parsing data " + e.toString());
        }

        return jObj;// return JSON String
    }
}
