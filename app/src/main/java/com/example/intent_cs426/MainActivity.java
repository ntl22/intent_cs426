package com.example.intent_cs426;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFunctionForButtons();
        Log.d("Main onCreate", "created");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Main onStart", "started");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Main onResume", "resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Main onPause", "paused");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Main onStop", "stopped");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Main onRestart", "restarted");
    }

    private void setFunctionForButtons() {
        Button btn;
        // Set alarm
        btn = findViewById(R.id.button_set_alarm);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AlarmIntentActivity.class);
                startActivity(intent);
            }
        });
        // Send email
        btn = findViewById(R.id.button_send_email);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EmailIntentActivity.class);
                startActivity(intent);
            }
        });
        // Play music video
        btn = findViewById(R.id.button_play_music_video);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MusicVideoIntentActivity.class);
                startActivity(intent);
            }
        });
    }
}