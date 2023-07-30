package com.example.farmanalyticav2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class on_successful_login extends AppCompatActivity {

    Button nextLanding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_successful_login);

        nextLanding = findViewById(R.id.nextToHome);
        nextLanding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(on_successful_login.this, landing.class);
                startActivity(intent);
            }
        });
    }
}