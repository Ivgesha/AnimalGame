package com.example.animalgame.view;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.animalgame.QuizDBHelper;
import com.example.animalgame.R;
import com.example.animalgame.model.Question;

import java.util.Collections;
import java.util.List;
import java.util.Locale;


import static com.example.animalgame.view.MainActivity.EXTRA_USERNAME;

public class GameActivity2 extends AppCompatActivity {


    private MediaPlayer buttonClickMediaPlayer,themeMusicMediaPlayer;

    public static final String EXTRA_SCORE = "com.example.animalgame.view.EXTRA_SCORE";
    private static final long COUNTDOWN_IN_MILLIS = 11000;
    public static int max=(11000 / 1000) % 60;
    public static int sec=(11000 / 1000) % 60;

    private TextView textViewScore;
    private TextView textViewCountDown;
    private TextView textViewquestionCounter;
    private ImageView imageViewAnimals;
    private Button buttonAnswer1;
    private Button buttonAnswer2;
    private Button buttonAnswer3;
    private Button buttonAnswer4;
    private Button buttonNext;
    private ColorStateList colorStateList;
    private ColorStateList countdownColorDefault;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;


    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;
    ProgressBar progressBar;
    private boolean btn1Pressed = false;
    private boolean btn2Pressed = false;
    private boolean btn3Pressed = false;
    private boolean btn4Pressed = false;

    private Bundle extras;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        extras = getIntent().getExtras();
        userName = extras.getString(EXTRA_USERNAME);
        Log.d("extrasTest", userName);

        buttonClickMediaPlayer = MediaPlayer.create(this,R.raw.button_click2);
        themeMusicMediaPlayer = MediaPlayer.create(this,R.raw.theme_music2);
        themeMusicMediaPlayer.start();

        textViewScore = findViewById(R.id.textViewScore);
        textViewCountDown = findViewById(R.id.textViewCountDown);
        textViewquestionCounter = findViewById(R.id.questionCounter);
        imageViewAnimals = findViewById(R.id.imageViewAnimals);
        buttonAnswer1 = findViewById(R.id.btnAnswer_1);
        buttonAnswer2 = findViewById(R.id.btnAnswer_2);
        buttonAnswer3 = findViewById(R.id.btnAnswer_3);
        buttonAnswer4 = findViewById(R.id.btnAnswer_4);
        buttonNext = findViewById(R.id.btnNext);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        colorStateList = buttonAnswer1.getTextColors();
        countdownColorDefault = textViewCountDown.getTextColors();
        new ProgressBarAsyncTask().execute();

        QuizDBHelper dbHelper = new QuizDBHelper(this);
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList); // shuffling the collection (the list of questions )
        showNextQuestion();

    }



 class ProgressBarAsyncTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setMax(max);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            for (int i = max; i >0; i=sec) {
                publishProgress(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return "Finished!";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            //progressBar.setProgress(0);
            //progressBar.setVisibility(View.INVISIBLE);
        }
    }









    private void showNextQuestion() {
        //
        buttonAnswer1.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_1));
        buttonAnswer2.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_1));
        buttonAnswer3.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_1));
        buttonAnswer4.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_1));

        //
        buttonAnswer1.setTextColor(colorStateList);
        buttonAnswer2.setTextColor(colorStateList);
        buttonAnswer3.setTextColor(colorStateList);
        buttonAnswer4.setTextColor(colorStateList);
        // here you need to clear the clicked button

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);
            imageViewAnimals.setImageResource(currentQuestion.getAnimalImage());
            buttonAnswer1.setText(currentQuestion.getOption1());
            buttonAnswer2.setText(currentQuestion.getOption2());
            buttonAnswer3.setText(currentQuestion.getOption3());
            buttonAnswer4.setText(currentQuestion.getOption4());
            questionCounter++;
            textViewquestionCounter.setText(getString(R.string.Question) + " " + questionCounter + "/" + questionCountTotal);
            answered = false;
            btn1Pressed = false;
            btn2Pressed = false;
            btn3Pressed = false;
            btn4Pressed = false;
            buttonNext.setText(getString(R.string.Confirm));

            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
        } else {
            finishQuiz();
        }

    }

    private void finishQuiz() {

        // passing on the name and opening the List
        Intent intent = new Intent(this, RecordActivity.class);
        intent.putExtra(EXTRA_SCORE, score);
        intent.putExtra( EXTRA_USERNAME, userName);
        themeMusicMediaPlayer.stop();
        finish();
        this.startActivity(intent);
    }

    public void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {

            // on tick called every 1000 millisec (countDownInterval)
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                // displaying 0 when we finished the count down
                updateCountDownText();
                checkAnswer();
            }
        }.start();

    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        sec =seconds;
        textViewCountDown.setText(timeFormatted);
        new ProgressBarAsyncTask().execute();

        // when the count down hit 3 sec it will turn red
        if (timeLeftInMillis < 3000) {
            textViewCountDown.setTextColor(Color.RED);
        } else {
            textViewCountDown.setTextColor(countdownColorDefault);
        }
    }

    // on click of Confirm Next button
    public void btnConfirmNext(View view) {
        buttonClickMediaPlayer.start();
        if (!answered) {  // if question answered;
            Log.d("btnXPresed ", "btn1Presed " + btn1Pressed);
            Log.d("btnXPresed ", "btn2Presed " + btn2Pressed);
            Log.d("btnXPresed ", "btn3Presed " + btn3Pressed);
            Log.d("btnXPresed ", "btn4Presed " + btn4Pressed);
            if (btn1Pressed == true || btn2Pressed == true || btn3Pressed == true || btn4Pressed == true) {
                // check answer
                checkAnswer();
            } else {
                Toast.makeText(this, "Select Answer", Toast.LENGTH_SHORT).show();
            }
        } else
            showNextQuestion();

    }


    public void buttonAnswerClick1(View view) {
        buttonClickMediaPlayer.start();
        if (btn1Pressed == false) {
            btn1Pressed = true;
            buttonAnswer1.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_3));
            btn2Pressed = false;
            buttonAnswer2.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_1));
            btn3Pressed = false;
            buttonAnswer3.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_1));
            btn4Pressed = false;
            buttonAnswer4.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_1));
        } else if (btn1Pressed == true) {
            buttonAnswer1.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_1));
            btn1Pressed = false;
        }
    }

    public void buttonAnswerClick2(View view) {
        buttonClickMediaPlayer.start();
        if (btn2Pressed == false) {
            btn1Pressed = false;
            buttonAnswer1.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_1));
            btn2Pressed = true;
            buttonAnswer2.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_3));
            btn3Pressed = false;
            buttonAnswer3.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_1));
            btn4Pressed = false;
            buttonAnswer4.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_1));
        } else if (btn2Pressed == true) {
            buttonAnswer2.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_1));
            btn2Pressed = false;
        }
    }

    public void buttonAnswerClick3(View view) {
        buttonClickMediaPlayer.start();
        if (btn3Pressed == false) {
            btn1Pressed = false;
            buttonAnswer1.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_1));
            btn2Pressed = false;
            buttonAnswer2.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_1));
            btn3Pressed = true;
            buttonAnswer3.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_3));
            btn4Pressed = false;
            buttonAnswer4.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_1));
        } else if (btn3Pressed == true) {
            buttonAnswer3.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_1));
            btn3Pressed = false;
        }
    }

    //
    public void buttonAnswerClick4(View view) {
        buttonClickMediaPlayer.start();
        if (btn4Pressed == false) {
            btn1Pressed = false;
            buttonAnswer1.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_1));
            btn2Pressed = false;
            buttonAnswer2.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_1));
            btn3Pressed = false;
            buttonAnswer3.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_1));
            btn4Pressed = true;
            buttonAnswer4.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_3));
        } else if (btn4Pressed == true) {
            buttonAnswer4.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_1));
            btn3Pressed = false;
        }
    }


    public void checkAnswer() {
        answered = true;

        countDownTimer.cancel();
        Button btnSelected;
        int answerNumber = 0;
        if (btn1Pressed == true) {
            btnSelected = findViewById(R.id.btnAnswer_1);
            answerNumber = 1;
        }
        if (btn2Pressed == true) {
            btnSelected = findViewById(R.id.btnAnswer_2);
            answerNumber = 2;
        }
        if (btn3Pressed == true) {
            btnSelected = findViewById(R.id.btnAnswer_3);
            answerNumber = 3;
        }
        if (btn4Pressed == true) {
            btnSelected = findViewById(R.id.btnAnswer_4);
            answerNumber = 4;
        }
        if (answerNumber == currentQuestion.getAnswerNumber()) {
            score++;
            textViewScore.setText(getString(R.string.score)+ " " + score);
        }
        showSolution();
    }

    public void showSolution() {
        buttonAnswer1.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.background_6));
        buttonAnswer2.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.background_6));
        buttonAnswer3.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.background_6));
        buttonAnswer4.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.background_6));
        switch (currentQuestion.getAnswerNumber()) {
            case 1:
                buttonAnswer1.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.background_5));
                break;
            case 2:
                buttonAnswer2.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.background_5));
                break;

            case 3:
                buttonAnswer3.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.background_5));
                break;

            case 4:
                buttonAnswer4.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.background_5));
                break;
        }

        if (questionCounter < questionCountTotal) {
            buttonNext.setText(getString(R.string.Next));
        } else
            buttonNext.setText(getString(R.string.Finish));
    }


    // when the activity is finished, the timer will still work, so we will make sure it cancled
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        themeMusicMediaPlayer.stop();
        finish();
    }


    //
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
