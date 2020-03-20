package com.example.animalgame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.example.animalgame.R;

import static com.example.animalgame.view.MainActivity.EXTRA_USERNAME;

import java.util.Locale;

public class CountDownActivity extends AppCompatActivity {

    private Bundle extras;
    private String userName;


    private static final long COUNTDOWN_IN_MILLIS = 5000;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    public static int max = (11000 / 1000) % 60;
    public static int sec = (11000 / 1000) % 60;
    private TextView countDownTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);

        extras = getIntent().getExtras();
        userName = extras.getString(EXTRA_USERNAME);


        countDownTextView = findViewById(R.id.countDownTextView);
        timeLeftInMillis = COUNTDOWN_IN_MILLIS;
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                finish();
            }
        }.start();
    }

    public void updateCountDownText() {
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        int timeFormattedInteger = seconds - 1;
        String timeFormatted = String.format(Locale.getDefault(), "%01d", timeFormattedInteger);
        countDownTextView.setText(timeFormatted);

        if (seconds <= 1) {
            countDownTextView.setText(getString(R.string.GO));
        } if (seconds == 0) {
            Intent intent = new Intent(this, GameActivity2.class);
            intent.putExtra(EXTRA_USERNAME, userName);
            this.startActivity(intent);

        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        countDownTimer.cancel();
        finish();
    }
}
