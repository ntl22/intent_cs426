package com.example.commonintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    Button btnBack;
    Button btnWifi;
    Button btnAirplane;

    View.OnClickListener backActivity = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    View.OnClickListener onSettingsClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v == btnWifi) {
                openSettings(Settings.ACTION_WIFI_SETTINGS);
            } else if(v == btnAirplane) {
                openSettings(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
            }
        }

        public void openSettings(String action) {
            Intent intent = new Intent(action);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(backActivity);
        btnBack.setBackgroundColor(getColor(R.color.black));

        btnWifi = findViewById(R.id.btnWifi);
        btnWifi.setOnClickListener(onSettingsClick);

        btnAirplane = findViewById(R.id.btnAirplane);
        btnAirplane.setOnClickListener(onSettingsClick);
    }
}