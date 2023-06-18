package com.example.intent_cs426;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MediaIntentActivity extends AppCompatActivity {
    static final int REQUEST_MEDIA_GET = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_intent);

        Button btn = findViewById(R.id.button_get_content);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT)
                        .setType("*/*")
                        .addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, REQUEST_MEDIA_GET);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_MEDIA_GET && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();

            Intent intent = new Intent(Intent.ACTION_VIEW)
                    .setData(imageUri)
                    .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, "Application not found", Toast.LENGTH_SHORT).show();
            }

        }
    }
}