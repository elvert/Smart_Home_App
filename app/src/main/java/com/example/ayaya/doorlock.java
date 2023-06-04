package com.example.ayaya;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class doorlock extends AppCompatActivity {
    private Button btnlog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doorlock);
        btnlog = findViewById(R.id.check_log);

        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDoorlog();
            }
        });
    }

    public void openDoorlog() {
        Intent intentlog = new Intent(this, doorlock_log.class);
            startActivity(intentlog);
    }
}
