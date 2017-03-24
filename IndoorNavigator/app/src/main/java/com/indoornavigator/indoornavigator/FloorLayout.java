package com.indoornavigator.indoornavigator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FloorLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_layout);

        final Button Kollupitiya = (Button) findViewById(R.id.Kollupitiya);
        final Button Bambalapitiya = (Button) findViewById(R.id.Bambalapitiya);
        Kollupitiya.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), CreateGrocerylist.class));
            }
        });
        Bambalapitiya.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), CreateGrocerylist.class));
            }
        });
    }
}
