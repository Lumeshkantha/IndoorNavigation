package com.indoornavigator.indoornavigator;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ImageView imageView=(ImageView) findViewById(R.id.imageView);
        Bitmap workingBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);
        Bitmap bitmap = Bitmap.createBitmap(mutableBitmap);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        canvas.drawLine(0,0,200,0,paint);
        canvas.drawLine(200,0,200,200,paint);
        imageView.setImageBitmap(bitmap);
    }
}
