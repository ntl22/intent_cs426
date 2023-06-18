package com.example.commonintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalendarActivity extends AppCompatActivity {

    Button btnBack;
    Button btnGo;
    EditText editTitle;
    EditText editLocation;
    EditText editStartDate;
    EditText editStartTime;
    EditText editEndDate;
    EditText editEndTime;

    View.OnClickListener backActivity = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    View.OnClickListener setCalendarEvent = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String title = editTitle.getText().toString();
            String location = editLocation.getText().toString();
            long begin = 0L;
            long end = 0L;

            String startTime = editStartDate.getText().toString();
            startTime += " " + editStartTime.getText().toString();

            String endTime = editEndDate.getText().toString();
            endTime += " " + editEndTime.getText().toString();

            DateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm");
            try {
                begin = df.parse(startTime).getTime();
            } catch (ParseException e) {}
            try {
                end = df.parse(endTime).getTime();
            } catch (ParseException e) {}

            addEvent(title, location, begin, end);
        }

        public void addEvent(String title, String location, long begin, long end) {
            Intent intent = new Intent(Intent.ACTION_INSERT)
                    .setData(CalendarContract.Events.CONTENT_URI)
                    .putExtra(CalendarContract.Events.TITLE, title)
                    .putExtra(CalendarContract.Events.EVENT_LOCATION, location)
                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin)
                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    };

    View.OnClickListener pickTime = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DialogFragment newFragment = new DatePickerFragment();
            newFragment.show(getSupportFragmentManager(), "datePicker");
        }
    };

    TextWatcher dateQualifier = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Pattern datetimePattern = Pattern.compile("\\d\\d\\d\\d-(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[0-2])");
            Matcher matcher = datetimePattern.matcher(s);

            if(!matcher.find()) {
                editStartDate.setError("YYYY-(M)M-(D)D");
            }
            else {
                editStartDate.setError(null);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        editTitle = findViewById(R.id.editTitle);
        editTitle.setText("Testing");

        editLocation = findViewById(R.id.editLocation);
        editLocation.setText("HCMUS");

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(backActivity);
        btnBack.setBackgroundColor(getColor(R.color.black));

        btnGo = findViewById(R.id.btnGo);
        btnGo.setOnClickListener(setCalendarEvent);

        editStartDate = findViewById(R.id.editStartDate);
//        editStartDate.setOnClickListener(pickTime);
//        editStartDate.addTextChangedListener(dateQualifier);
        editStartDate.setText("2023/06/18");

        editStartTime = findViewById(R.id.editStartTime);
//        editStartTime.setOnClickListener(pickTime);
//        editStartTime.addTextChangedListener(dateQualifier);
        editStartTime.setText("10:00");

        editEndDate = findViewById(R.id.editEndDate);
//        editEndDate.setOnClickListener(pickTime);
        editEndDate.setText("2023/06/18");

        editEndTime = findViewById(R.id.editEndTime);
//        editEndTime.setOnClickListener(pickTime);
        editEndTime.setText("11:00");

        fixInitialSize(editStartDate);
        fixInitialSize(editStartTime);
        fixInitialSize(editEndDate);
        fixInitialSize(editEndTime);
    }

    private void fixInitialSize(EditText obj) {
        obj.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                obj.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                obj.setWidth(obj.getWidth());
                obj.setHeight(obj.getHeight());
            }
        });
    }

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
}