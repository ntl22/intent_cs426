package com.example.commonintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/*
TODO:
- Calendar: https://developer.android.com/guide/components/intents-common#Calendar
- Maps:     https://developer.android.com/guide/components/intents-common#Maps
- Settings: https://developer.android.com/guide/components/intents-common#Settings
 */

public class MainActivity extends AppCompatActivity {

    Button btnCalendar;
    Button btnMaps;
    Button btnSettings;

    View.OnClickListener onChangeActivityClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v == btnCalendar) launchCalendarActivity();
            else if(v == btnMaps) launchMapsActivity();
            else if(v == btnSettings) launchSettingsActivity();
        }

        private void launchSettingsActivity() {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        }

        private void launchMapsActivity() {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);
        }

        private void launchCalendarActivity() {
            Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalendar = findViewById(R.id.btnCalendar);
        btnMaps = findViewById(R.id.btnMap);
        btnSettings = findViewById(R.id.btnSettings);

        btnCalendar.setOnClickListener(onChangeActivityClick);
        btnMaps.setOnClickListener(onChangeActivityClick);
        btnSettings.setOnClickListener(onChangeActivityClick);
    }
}