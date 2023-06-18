package com.example.commonintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.net.URI;

public class MapsActivity extends AppCompatActivity {

    Button btnBack;
    Button btnGo;
    EditText editDestination;

    View.OnClickListener backActivity = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    View.OnClickListener searchMap = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Uri uri = Uri.parse("geo:0,0?q=" + editDestination.getText().toString());
            showMap(uri);
        }

        public void showMap(Uri geoLocation) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(geoLocation);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    };

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(backActivity);
        btnBack.setBackgroundColor(getColor(R.color.black));

        btnGo = findViewById(R.id.btnGo);
        btnGo.setOnClickListener(searchMap);

        editDestination = findViewById(R.id.editDestination);
        editDestination.setText("HCMUS");
    }
}