package com.example.animalgame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.animalgame.R;

public class MainActivity extends AppCompatActivity {


    private Handler mHandler = new Handler();



    Intent intent;
    TextView titleTextView;
    EditText enterNameEditText;
    Button startGameButton,howToPlayButton;


    Animation rotateAnimation,zoomInAnimation,zoomOutAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleTextView = findViewById(R.id.AnimalKingdomLogoEditText);
        enterNameEditText = findViewById(R.id.EnterNameEditText);
        startGameButton = findViewById(R.id.startGameButton);
        howToPlayButton = findViewById(R.id.howToPlayButton);

        // for testing only
        enterNameEditText.setText("Test");

        // make a func that start all the animations
        runAnimations.run();
    }

// running the Animations every 10 seconds
    private Runnable runAnimations = new Runnable() {
        @Override
        public void run() {
            startAnimations();
            mHandler.postDelayed(this,10000);
        }
    };

    public void startGameButtonClick(View view) {                 // on click startGame Button
        intent = new Intent(this, GameActivity2.class);
        Log.d("enterNameEditText", enterNameEditText.getText().toString());
        if (enterNameEditText.getText().toString().equals("")) {
            Toast.makeText(this, "name is empty", Toast.LENGTH_LONG).show();
        } else {
            this.startActivity(intent);
            Toast.makeText(this, "name is full! we can play", Toast.LENGTH_LONG).show();
        }

    }

    public void howToPlayClick(View view) {                      // on click howToPlay Button
        intent = new Intent(this, HowToPlayActivity.class);
        startActivity(intent);
    }


    public void startAnimations(){
        zoomInAnimation = AnimationUtils.loadAnimation(this,R.anim.zoomin);
        titleTextView.startAnimation(zoomInAnimation);
//        zoomOutAnimation = AnimationUtils.loadAnimation(this,R.anim.zoomout);
//        titleTextView.startAnimation(zoomOutAnimation);
        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        startGameButton.startAnimation(rotateAnimation);
    }



}
