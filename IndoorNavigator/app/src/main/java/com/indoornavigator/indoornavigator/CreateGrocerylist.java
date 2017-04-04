package com.indoornavigator.indoornavigator;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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
    Items itemlist = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_grocerylist);
        CreateGrocerylist cgl = this;
        listView = (ListView) findViewById(R.id.listview);
        DBAccessCreateGrocerylist dba = new DBAccessCreateGrocerylist();
        dba.getCreateGrocerylistobject(cgl);
        try {
            itemlist = dba.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        adapter = new ArrayAdapter<String>(this, R.layout.item_list, R.id.itemname, itemlist.name);
        listView.setAdapter(adapter);



        final Button next = (Button) findViewById(R.id.Next);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb;
                ArrayList<Integer> id = new ArrayList<Integer>();
                for (int x = 0; x<listView.getChildCount();x++){
                    cb = (CheckBox)listView.getChildAt(x).findViewById(R.id.itemname);
                    if(cb.isChecked()){
                        //Log.e("Data",String.valueOf(x));
                        //Log.e("Data",cb.getText().toString());
                        id.add(itemlist.iid.get(x));
                    }
                }
                getRackID(id);
                startActivity(new Intent(getBaseContext(),Test.class));
            }
        });
    }

    void getRackID(ArrayList<Integer> id)
    {
        ArrayList<Integer> racks = new ArrayList<Integer>();
        for (int i:id)
        {
            boolean status=true;
            if(racks.size()==0)
            {
                racks.add(i);
            }
            else
            {
                for (int j:racks)
                {
                    if(i==j)
                    {
                        status=false;
                        break;
                    }
                }
                if(status)
                {
                    racks.add(i);
                }
            }
        }
    }
}

class DBAccessCreateGrocerylist extends AsyncTask<Void,Void,Items> {

    CreateGrocerylist cgl;
    void getCreateGrocerylistobject(CreateGrocerylist cgl)
    {
        this.cgl=cgl;
    }
    @Override
    protected Items doInBackground(Void... params) {

        InputStream is = null;
        Items items = new Items();
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
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject= jsonArray.getJSONObject(i);
                items.name.add(jsonObject.getString("name"));
                items.iid.add(jsonObject.getInt("iid"));
            }
            return items;
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

class Items
{
    ArrayList<String> name;
    ArrayList<Integer> iid;

    public Items()
    {
        name = new ArrayList<String>();
        iid = new ArrayList<Integer>();
    }
}