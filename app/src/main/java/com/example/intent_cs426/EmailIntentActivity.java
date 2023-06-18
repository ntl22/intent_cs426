package com.example.intent_cs426;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EmailIntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_intent);

        Button btn = findViewById(R.id.button_call_email_intent);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                composeEmail();
            }
        });
        Log.d("Email onCreate", "onCreate");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("Email onSaveInstanceState", "onSaveInstanceState");
    }

    private String[] splitAddress(String addressesText) {
        String[] addresses = addressesText.trim().split(",");
        for (int i = 0; i < addresses.length; ++i) {
            addresses[i] = addresses[i].trim();
        }
        return addresses;
    }
    private void composeEmail() {
        EditText toEditText = findViewById(R.id.input_email_to);
        EditText ccEditText = findViewById(R.id.input_email_cc);
        EditText bccEditText = findViewById(R.id.input_email_bcc);
        EditText subjectEditText = findViewById(R.id.input_email_subject);
        EditText bodyEditText = findViewById(R.id.input_email_body);

        String[] toAddresses = splitAddress(toEditText.getText().toString());
        String[] ccAddresses = splitAddress(ccEditText.getText().toString());
        String[] bccAddresses = splitAddress(bccEditText.getText().toString());
        String subject = subjectEditText.getText().toString();
        String body = bodyEditText.getText().toString();

        composeEmail(toAddresses, ccAddresses, bccAddresses, subject, body);
    }

    private void composeEmail(String[] toAddresses, String[] ccAddresses, String[] bccAddresses, String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SEND)
                .setType("message/rfc822")
                .putExtra(Intent.EXTRA_EMAIL, toAddresses)
                .putExtra(Intent.EXTRA_CC, ccAddresses)
                .putExtra(Intent.EXTRA_BCC, bccAddresses)
                .putExtra(Intent.EXTRA_SUBJECT, subject)
                .putExtra(Intent.EXTRA_TEXT, body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "Email client not found", Toast.LENGTH_SHORT).show();
        }
    }
}