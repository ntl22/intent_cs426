package com.example.intent_cs426;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class AlarmIntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_intent);

        Button btn = findViewById(R.id.button_call_alarm_intent);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePicker timePicker = findViewById(R.id.alarm_timer_picker);
                EditText alarmMessage = findViewById(R.id.input_alarm_message);
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                        .putExtra(AlarmClock.EXTRA_MESSAGE, alarmMessage.getText().toString())
                        .putExtra(AlarmClock.EXTRA_HOUR, timePicker.getHour())
                        .putExtra(AlarmClock.EXTRA_MINUTES, timePicker.getMinute());
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(view.getContext(), "Application not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Alarm onDestroy", "destroy");
    }
}