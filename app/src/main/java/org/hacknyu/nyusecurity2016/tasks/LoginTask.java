package org.hacknyu.nyusecurity2016.tasks;

import android.os.AsyncTask;
import android.util.Log;

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
public class LoginTask extends AsyncTask<String, Integer, String> {

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try{
            URL url = new URL("http://104.236.121.142/login.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            writer.write(String.format("email=%s&password=%s", URLEncoder.encode(params[0], "UTF-8"), URLEncoder.encode(params[1], "UTF-8")));
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
            Log.d(Constants.TAG, "Login failed!");
            return;
        }
        try {
            JSONObject jsonObject = new JSONObject(result);
            int code = jsonObject.getInt("success");
            String message = jsonObject.getString("message");
            String first_name = jsonObject.getString("first_name");
            String last_name = jsonObject.getString("last_name");
            _(code + message +first_name +last_name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
