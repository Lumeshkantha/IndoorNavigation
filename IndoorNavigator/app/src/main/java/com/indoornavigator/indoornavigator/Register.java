package com.indoornavigator.indoornavigator;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Button regbtn = (Button) findViewById(R.id.Register);
        final EditText Firstname = (EditText) findViewById(R.id.Firstname);
        final EditText Lastname = (EditText) findViewById(R.id.Lastname);
        final EditText Username = (EditText) findViewById(R.id.Username);
        final EditText Password = (EditText) findViewById(R.id.Password);
        final Register reg = this;
        regbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DBAccessRegister dba = new DBAccessRegister();
                dba.getLoginobject(reg);
                try {
                    String data[]={Firstname.getText().toString(),Lastname.getText().toString(),Username.getText().toString(),Password.getText().toString()};
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
    }
}

class DBAccessRegister extends AsyncTask<String,String,Boolean> {

    Register reg;
    void getLoginobject(Register reg)
    {
        this.reg=reg;
    }
    @Override
    protected Boolean doInBackground(String... params) {

        InputStream is = null;
        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://"+Login.serverip+"/register.php");

            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("fname",params[0]));
            nameValuePairs.add(new BasicNameValuePair("lname",params[1]));
            nameValuePairs.add(new BasicNameValuePair("uname",params[2]));
            nameValuePairs.add(new BasicNameValuePair("pwd",params[3]));

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

            if(jObject.getString("status").equals("Registerd"))
            {
                return true;
            }
            else {
                Handler handler = new Handler(reg.getMainLooper());
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            Toast.makeText(reg, jObject.getString("status"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                return false;
            }
        }catch (Exception ex)
        {final String e = ex.getMessage();
            Log.e("Error",ex.getMessage());
            Handler handler =  new Handler(reg.getMainLooper());
            handler.post( new Runnable(){
                public void run(){
                    //Toast.makeText(reg,"Connection lost",Toast.LENGTH_LONG).show();
                    Toast.makeText(reg,e,Toast.LENGTH_LONG).show();
                }
            });
            return false;
        }
    }
}