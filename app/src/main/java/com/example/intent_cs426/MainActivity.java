package com.example.intent_cs426;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.cameraBtn).setOnClickListener(this);
        findViewById(R.id.phoneBtn).setOnClickListener(this);
        findViewById(R.id.urlBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.phoneBtn) {
            String phoneNumber = ((EditText) findViewById(R.id.phoneNumber)).getText().toString();

            if (!phoneNumber.isEmpty()) {
                Intent intent = new Intent(Intent.ACTION_DIAL);

                Uri uri = Uri.parse("tel:" + phoneNumber);
                intent.setData(uri);

                startActivity(intent);
            }
        } else if (v.getId() == R.id.urlBtn) {
            String website = ((EditText) findViewById(R.id.urlTxt)).getText().toString();

            if (!website.isEmpty()) {
                Intent intent = new Intent(Intent.ACTION_VIEW);

                if (!website.startsWith("https://") || !website.startsWith("http://")) {
                    website = "http://" + website;
                }

                Uri uri = Uri.parse(website);
                intent.setData(uri);

                startActivity(intent);
            }
        } else if (v.getId() == R.id.cameraBtn) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            cameraLauncher.launch(intent);
        }
    }

    private ActivityResultLauncher<Intent> cameraLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    ((ImageView) findViewById(R.id.cameraImg)).setImageBitmap(photo);
                }
            }
    );
}