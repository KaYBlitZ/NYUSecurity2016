package org.hacknyu.nyusecurity2016.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.hacknyu.nyusecurity2016.Constants;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Kenneth on 2/20/2016.
 */
public class RegisterTask extends AsyncTask<String, Integer, String> {

    private Context context;

    public RegisterTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try{
            URL url = new URL("http://104.236.121.142/register.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            writer.write(String.format("first_name=%s&last_name=%s&password=%s&email=%s",
                    URLEncoder.encode(params[0], "UTF-8"), URLEncoder.encode(params[1], "UTF-8"),
                    URLEncoder.encode(params[2], "UTF-8"), URLEncoder.encode(params[3], "UTF-8")));
            writer.flush();
            writer.close();

            String response = "";
            String line;
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null) {
                response += line;
            }
            return response;
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (connection != null){
                connection.disconnect();
            }
            try {
                if(reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        if (result == null) {
            Log.d(Constants.TAG, "Register failed!");
            Toast.makeText(context, "Register failed!", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            JSONObject object = new JSONObject(result);
            Toast.makeText(context, object.getString("message"), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d(Constants.TAG, result);
    }
    /*
    public void show(String code, String message, String time){
        MyAlert myAlert = new MyAlert(code, message, time);
        myAlert.show(getFragmentManager(), "dialog");
    }
    */
    private void _(String s){
        Log.d("MyApp", "MainActivity" + "####" + s);
    }
}
