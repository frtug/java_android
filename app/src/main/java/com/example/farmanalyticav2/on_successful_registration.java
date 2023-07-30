package com.example.farmanalyticav2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class on_successful_registration extends AppCompatActivity {

    Button nextLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_successful_registration);

        nextLogin = findViewById(R.id.nextToLogin);
        nextLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(on_successful_registration.this, Login.class);
                startActivity(intent);
            }
        });
    }
}