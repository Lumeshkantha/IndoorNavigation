package com.indoornavigator.indoornavigator;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Login extends AppCompatActivity {

    static public String serverip="172.31.4.105";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button loginbtn = (Button) findViewById(R.id.Login);
        final TextView Username = (TextView) findViewById(R.id.Username);
        final TextView Password = (TextView) findViewById(R.id.Password);
        final Login login = this;
        loginbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DBAccessLogin dba = new DBAccessLogin();
                dba.getLoginobject(login);
                try {
                    String data[]={Username.getText().toString(),Password.getText().toString()};
                    if(dba.execute(data).get()) {
                        startActivity(new Intent(getBaseContext(), FloorLayout.class));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });


        final Button regbtn = (Button) findViewById(R.id.Register);
        regbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Register.class));
            }
        });
    }
}

class DBAccessLogin extends AsyncTask<String,String,Boolean>{

    Login login;
    void getLoginobject(Login login)
    {
        this.login=login;
    }
    @Override
    protected Boolean doInBackground(String... params) {

        InputStream is = null;
        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://"+Login.serverip+"/login.php");

            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("uname",params[0]));
            nameValuePairs.add(new BasicNameValuePair("pwd",params[1]));

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line+"\n");
            }
            is.close();
            String result = sb.toString();
            final JSONObject jObject = new JSONObject(result);

            if(jObject.getString("status").equals("Authenticated"))
            {
                return true;
            }
            else {
                Handler handler = new Handler(login.getMainLooper());
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            Toast.makeText(login, jObject.getString("status"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                return false;
            }
        }catch (Exception ex)
        {
            Log.e("Error",ex.getMessage());
            Handler handler =  new Handler(login.getMainLooper());
            handler.post( new Runnable(){
                public void run(){
                    Toast.makeText(login,"Connection lost",Toast.LENGTH_LONG).show();
                }
            });
            return false;
        }
    }
}