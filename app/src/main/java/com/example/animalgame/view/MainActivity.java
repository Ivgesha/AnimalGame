package com.example.animalgame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.animalgame.R;

public class MainActivity extends AppCompatActivity {





    public static final String EXTRA_USERNAME = "com.example.animalgame.view.EXTRA_USRENAME";
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";

    private String sharedPrefrencesLoadString;
    private Handler mHandler = new Handler();
    private Intent intent;
    private TextView titleTextView;
    private EditText enterNameEditText;
    private Button startGameButton, howToPlayButton;
    private String username;
    private MediaPlayer buttonClickMediaPlayer, themeMusicMediaPlayer;


    Animation rotateAnimation, zoomInAnimation, zoomOutAnimation, blinkAnim, bounceAnim, zoomInFadeAnim;

    //DatabaseHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);
        // myDb = new DatabaseHelper(this);
        titleTextView = findViewById(R.id.AnimalKingdomLogoEditText);
        enterNameEditText = findViewById(R.id.EnterNameEditText);
        startGameButton = findViewById(R.id.startGameButton);
      //  howToPlayButton = findViewById(R.id.howToPlayButton);
        buttonClickMediaPlayer = MediaPlayer.create(this, R.raw.button_click2);
        themeMusicMediaPlayer = MediaPlayer.create(this, R.raw.theme_music);


        themeMusicMediaPlayer.start();
        runAnimations.run();

        loadData();
        updateViews();

    }




    public void startGameButtonClick(View view) {                 // on click startGame Button
        buttonClickMediaPlayer.start();
        intent = new Intent(this, CountDownActivity.class);
        if (enterNameEditText.getText().toString().equals("")) {
            Toast.makeText(this, "name is empty", Toast.LENGTH_LONG).show();
        } else {
            saveData();     // saving shared prefrences data
            username = enterNameEditText.getText().toString();
            intent.putExtra(EXTRA_USERNAME, username);
            themeMusicMediaPlayer.stop();
            this.startActivity(intent);
        }

    }

    public void howToPlayClick(View view) {                      // on click howToPlay Button
        buttonClickMediaPlayer.start();
        intent = new Intent(this, HowToPlayActivity.class);
        startActivity(intent);
    }


    // running the Animations every 10 seconds
    private Runnable runAnimations = new Runnable() {
        @Override
        public void run() {
            startAnimations();
            mHandler.postDelayed(this, 10000);
        }
    };



    public void startAnimations() {
        //blinkAnim = AnimationUtils.loadAnimation(this,R.anim.blink_anim);
        //howToPlayButton.startAnimation(blinkAnim);
        //zoomInAnimation = AnimationUtils.loadAnimation(this, R.anim.zoomin);
        //titleTextView.startAnimation(zoomInAnimation);
//        zoomOutAnimation = AnimationUtils.loadAnimation(this,R.anim.zoomout);
//      titleTextView.startAnimation(zoomOutAnimation);
//        bounceAnim = AnimationUtils.loadAnimation(this,R.anim.bounce);
        //      titleTextView.startAnimation(bounceAnim);
        zoomInFadeAnim = AnimationUtils.loadAnimation(this, R.anim.zoomin_fade);
        titleTextView.startAnimation(zoomInFadeAnim);
        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        startGameButton.startAnimation(rotateAnimation);

    }




// SharedPreferences functions


    public void saveData() {
        // MODE_PRIVATE means that you can change the shered prefrences from out app.
        // and not from other apps
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT, enterNameEditText.getText().toString());
        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        // first argument is what we load.
        // second argument is the default argument
        sharedPrefrencesLoadString = sharedPreferences.getString(TEXT, "");
    }

    public void updateViews(){
        enterNameEditText.setText(sharedPrefrencesLoadString);
    }


    @Override
    protected void onPause() {
        super.onPause();
        themeMusicMediaPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        themeMusicMediaPlayer.start();

    }
}
