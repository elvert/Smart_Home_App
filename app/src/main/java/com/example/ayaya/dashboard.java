package com.example.ayaya;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class dashboard extends AppCompatActivity {
    private ImageView bedroom_db;
    private ImageView kitchen_db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_main);


        // Get a reference to the CardView
        bedroom_db = (ImageView) findViewById(R.id.bedroom_dash);
        bedroom_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openBedroom();}
        });

        kitchen_db = (ImageView) findViewById(R.id.kitchen_dash);
        kitchen_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openKitchen();}
        });
    }
    public void openBedroom() {
        Intent intent = new Intent(this, bedroom.class);
        startActivity(intent);
    }
    public void openKitchen() {
        Intent intent1 = new Intent(this, kitchen.class);
        startActivity(intent1);
    }
}