package com.example.ayaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ayaya.ui.kitchen.kitchen;

public class bedroom extends AppCompatActivity {
    private TextView tab1, tab2;
    private int SelectedTabNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bedroom_main);

        tab1 = findViewById(R.id.BedroomTab);
        tab2 = findViewById(R.id.KitchenTab);

        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKitchen();

            }
        });
    }

    public void openKitchen() {
        Intent intent1 = new Intent(this, kitchen.class);
        startActivity(intent1);
    }
}