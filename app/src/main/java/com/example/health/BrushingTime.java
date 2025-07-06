package com.example.health;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class BrushingTime extends AppCompatActivity {

    private TextView textView;
    private final String TWO_MINUTES = "02 : 00";
    private final String KEEP_BRUSHING_MSG = "Keep brushing your teeth";
    private final String ONE_MINUTE = "01 : 00";
    private final String ALMOST_DONE_MSG = "You are almost done";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brushing_time);

        textView = findViewById(R.id.text_view);

        long duration = TimeUnit.MINUTES.toMillis(2); // 2 minutes duration

        new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String time = formatTime(millisUntilFinished);
                textView.setText(time);
                checkTimeForMessage(time);
            }

            @Override
            public void onFinish() {
                textView.setText("00 : 00");
                showToast("Well done!");
            }

            private String formatTime(long millis) {
                return String.format(Locale.getDefault(), "%02d : %02d",
                        TimeUnit.MILLISECONDS.toMinutes(millis),
                        TimeUnit.MILLISECONDS.toSeconds(millis) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            }

            private void checkTimeForMessage(String currentTime) {
                if (currentTime.equals(TWO_MINUTES)) {
                    showToast(KEEP_BRUSHING_MSG);
                } else if (currentTime.equals(ONE_MINUTE)) {
                    showToast(ALMOST_DONE_MSG);
                }
            }

            private void showToast(String message) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        }.start();
    }
}
