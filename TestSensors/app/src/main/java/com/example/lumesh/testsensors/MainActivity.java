package com.example.lumesh.testsensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    private TextView count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        count = (TextView) findViewById(R.id.count);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        count.setText("Ready");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Sensor countsensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countsensor !=null)
        {
            sensorManager.registerListener(this,countsensor,SensorManager.SENSOR_DELAY_UI);
        }
        else
        {
            Toast.makeText(this,"Step counter sensor not available",Toast.LENGTH_LONG);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        count.setText(String.valueOf(event.values[0]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
