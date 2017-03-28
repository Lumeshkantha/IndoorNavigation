package com.example.lumesh.accelerometer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    TextView data;
    Sensor sensor;
    SensorManager sm;
    double resultarray[] = new double[10];
    int i=0;
    static double PI = 3.1415926535897932384626433832795;
    static double gravity = 9806.65;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int j = 0; j < 10; j++){
            resultarray[j]=0;
        }

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        data = (TextView) findViewById(R.id.data);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float result = event.values[0]*event.values[0]+event.values[1]*event.values[1]+event.values[2]*event.values[2];
        resultarray[i]=Math.sqrt(result);
        if(resultarray[i] < 0){
            resultarray[i] = resultarray[i] - (resultarray[i]*2);
        }


        i++;
        if(i==10)
        {
            double total=0;
            for(int j=0;j<10;j++)
            {
                total +=resultarray[j];
            }
            total=total/10;
            data.setText(String.valueOf(Accel2mms(total,1)));
            i=0;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    double Accel2mms(double accel, double freq){
        double result = 0;
        result = (gravity*accel)/(2*PI*freq);
        return result;
    }
}
