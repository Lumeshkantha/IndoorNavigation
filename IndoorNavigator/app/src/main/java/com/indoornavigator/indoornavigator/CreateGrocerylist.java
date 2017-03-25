package com.indoornavigator.indoornavigator;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static com.indoornavigator.indoornavigator.R.id.Username;

public class CreateGrocerylist extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_grocerylist);
        CreateGrocerylist cgl = this;
        listView = (ListView) findViewById(R.id.listview);
        DBAccessCreateGrocerylist dba = new DBAccessCreateGrocerylist();
        dba.getCreateGrocerylistobject(cgl);
        ArrayList itemlist = null;
        try {
            itemlist = dba.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        adapter = new ArrayAdapter<String>(this, R.layout.item_list, R.id.itemname, itemlist);
        listView.setAdapter(adapter);
    }
}

class DBAccessCreateGrocerylist extends AsyncTask<Void,Void,ArrayList> {

    CreateGrocerylist cgl;
    void getCreateGrocerylistobject(CreateGrocerylist cgl)
    {
        this.cgl=cgl;
    }
    @Override
    protected ArrayList doInBackground(Void... params) {

        InputStream is = null;
        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://"+Login.serverip+"/itemlist.php");
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
            final JSONArray jsonArray = new JSONArray(result);
            ArrayList<String> itemlist = new ArrayList<>();
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject= jsonArray.getJSONObject(i);
                itemlist.add(jsonObject.getString("name"));
            }
            return itemlist;
        }catch (Exception ex)
        {
            Log.e("Error",ex.getMessage());
            Handler handler =  new Handler(cgl.getMainLooper());
            handler.post( new Runnable(){
                public void run(){
                    Toast.makeText(cgl,"Connection lost",Toast.LENGTH_LONG).show();
                }
            });
            return null;
        }
    }
}