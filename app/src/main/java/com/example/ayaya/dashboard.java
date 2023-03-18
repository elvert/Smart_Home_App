package com.example.ayaya;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ayaya.ui.kitchen.kitchen;

public class dashboard extends AppCompatActivity {
    private CardView cardView1;
    private CardView cardView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_main);


        // Get a reference to the CardView
        cardView1 = (CardView) findViewById(R.id.bedroom_card);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openBedroom();}
        });

        cardView2 = (CardView) findViewById(R.id.kitchencard);
        cardView2.setOnClickListener(new View.OnClickListener() {
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