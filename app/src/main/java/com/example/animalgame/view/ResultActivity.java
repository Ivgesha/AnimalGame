package com.example.animalgame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.animalgame.R;

import static com.example.animalgame.view.GameActivity2.EXTRA_SCORE;
import static com.example.animalgame.view.MainActivity.EXTRA_USERNAME;

public class ResultActivity extends AppCompatActivity {


    private Bundle extras;
    private int score;
    private String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        extras = getIntent().getExtras();
        userName = extras.getString(EXTRA_USERNAME);
        score = extras.getInt(EXTRA_SCORE,0);

        Log.d("extrasTest","userName - " + userName + "score + " + score  );


    }
}
