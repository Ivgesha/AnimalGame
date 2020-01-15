package com.example.animalgame.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.animalgame.R;

import org.w3c.dom.Text;

public class GameActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView countDownTimerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        progressBar = findViewById(R.id.progressBar);
        countDownTimerTextView = findViewById(R.id.countDownTimerTextView);
        countDownTimerTextView.setText("Timer Time");
//
        new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setProgress((int) millisUntilFinished / 1000);
                countDownTimerTextView.setText("Timer: "+progressBar.getProgress());               // is there a better way?
            }

            @Override
            public void onFinish() {
                Toast.makeText(GameActivity.this, "DONE!", Toast.LENGTH_SHORT).show();
            }
        }.start();

    }
}
