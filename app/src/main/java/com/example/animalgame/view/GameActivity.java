package com.example.animalgame.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.animalgame.R;

import org.w3c.dom.Text;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView countDownTimerTextView;
    public ImageView img;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    public int myRandomInt;
    public int index;
    int i = 0;
    public int points = 0;
    public String rightanswer;
    TextView pointsTextView;
    public int str_images_name_index = 0;
    public Images images[] = new Images[35];
    Intent intent;
    String fullname;

    // setting array of answers
    String answers[] = {"Alligator", "Cat", "Chicken", "Deer",
            "Dog", "Donkey", "Elaphent", "Horse", "Gorilla", "Goat", "Mice", "Monkey",
            "Lion", "Puma", "Rabbit", "Snake", "Tiger", "Wolf", "Zebra"};

    // setting array of image names
    String str_images_name[] = {"alligator", "cat", "cat", "chicken", "chicken", "deer", "deer",
            "dog", "dog", "donkey", "eagle", "eagle", "elaphent", "elaphent",
            "horse", "horse", "gorilla", "gorilla", "goat", "goat", "mice", "mice", "monkey",
            "lion", "lion", "puma", "puma", "rabbit", "rabbit", "snake", "tiger", "tiger", "wolf", "wolf", "zebra"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // holding the buttons and the images of the activity context
        setContentView(R.layout.activity_game);
        btn1 = (Button) findViewById(R.id.answer1);
        btn2 = (Button) findViewById(R.id.answer2);
        btn3 = (Button) findViewById(R.id.answer3);
        btn4 = (Button) findViewById(R.id.answer4);
        img = (ImageView) findViewById(R.id.images);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);

        // array of the pictures with the drawable connection
        final Images[] images = {new Images(str_images_name[0].toString().toLowerCase(), R.drawable.alligator), new Images(str_images_name[1].toString().toLowerCase(), R.drawable.cat), new Images(str_images_name[2].toString().toLowerCase(), R.drawable.car_2),
                new Images(str_images_name[3].toString().toLowerCase(), R.drawable.chicken), new Images(str_images_name[4].toString().toLowerCase(), R.drawable.chicken_2), new Images(str_images_name[5].toString().toLowerCase(), R.drawable.deer), new Images(str_images_name[6].toString().toLowerCase(), R.drawable.deer_2),
                new Images(str_images_name[7].toString().toLowerCase(), R.drawable.dog), new Images(str_images_name[8].toString().toLowerCase(), R.drawable.dog_2), new Images(str_images_name[9].toString().toLowerCase(), R.drawable.donkey),
                new Images(str_images_name[10].toString().toLowerCase(), R.drawable.eagle), new Images(str_images_name[11].toString().toLowerCase(), R.drawable.eagle_2), new Images(str_images_name[12].toString().toLowerCase(), R.drawable.elaphent),
                new Images(str_images_name[13].toString().toLowerCase(), R.drawable.elaphent_2), new Images(str_images_name[14].toString().toLowerCase(), R.drawable.horse), new Images(str_images_name[15].toString().toLowerCase(), R.drawable.horse_2),
                new Images(str_images_name[16].toString().toLowerCase(), R.drawable.gorilla), new Images(str_images_name[17].toString().toLowerCase(), R.drawable.gorilla_2), new Images(str_images_name[18].toString().toLowerCase(), R.drawable.goat),
                new Images(str_images_name[19].toString().toLowerCase(), R.drawable.goat_2), new Images(str_images_name[20].toString().toLowerCase(), R.drawable.mice), new Images(str_images_name[21].toString().toLowerCase(), R.drawable.mice_2),
                new Images(str_images_name[22].toString().toLowerCase(), R.drawable.monkey), new Images(str_images_name[23].toString().toLowerCase(), R.drawable.lion), new Images(str_images_name[24].toString().toLowerCase(), R.drawable.lion_2),
                new Images(str_images_name[25].toString().toLowerCase(), R.drawable.puma), new Images(str_images_name[26].toString().toLowerCase(), R.drawable.puma_2), new Images(str_images_name[27].toString().toLowerCase(), R.drawable.rabbit),
                new Images(str_images_name[28].toString().toLowerCase(), R.drawable.rabbit_2), new Images(str_images_name[29].toString().toLowerCase(), R.drawable.snake), new Images(str_images_name[30].toString().toLowerCase(), R.drawable.tiger),
                new Images(str_images_name[31].toString().toLowerCase(), R.drawable.tiger_2), new Images(str_images_name[32].toString().toLowerCase(), R.drawable.wolf), new Images(str_images_name[33].toString().toLowerCase(), R.drawable.wolf_2),
                new Images(str_images_name[34].toString().toLowerCase(), R.drawable.zebra)};

        Log.d("animasl" ,"images[0]"+ images[0]);

        progressBar = findViewById(R.id.progressBar);
        countDownTimerTextView = findViewById(R.id.countDownTimerTextView);
        countDownTimerTextView.setText("Timer Time");
        img = (ImageView) findViewById(R.id.images);

        index = myRandomInt = new Random().nextInt(28 - 0) + 0;
        str_images_name_index = new Random().nextInt(34 - 0) + 0;
        img.setImageResource(images[str_images_name_index].img_path);
        rightanswer = images[str_images_name_index].key.toString();
        random_answer();

        fullname = getIntent().getStringExtra("full_name");




        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn1.getText().toString().toLowerCase() == images[str_images_name_index].key.toString().toLowerCase()) {
                    Toast.makeText(getApplicationContext(), "Right answer!", Toast.LENGTH_SHORT);
                    btn1.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    points++;
                    pointsTextView.setText("Points:" + points);
                    progressBar.setProgress(progressBar.getProgress() + 1);
                    countDownTimerTextView.setText("Timer: " + progressBar.getProgress());




                }
                else {
                    Toast.makeText(getApplicationContext(), "Wrong answer!", Toast.LENGTH_SHORT).show();
                    btn1.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));

                }
                index = myRandomInt = new Random().nextInt(28 - 0) + 0;
                str_images_name_index = new Random().nextInt(34 - 0) + 0;
                img.setImageResource(images[str_images_name_index].img_path);
                btn1.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                rightanswer = images[str_images_name_index].key.toString();
                random_answer();

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn2.getText().toString().toLowerCase() == images[str_images_name_index].key.toString().toLowerCase()) {
                    Toast.makeText(getApplicationContext(), "Right answer!", Toast.LENGTH_SHORT);
                    btn2.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    points++;
                    pointsTextView.setText("Points:" + points);
                    progressBar.setProgress(progressBar.getProgress() + 1);
                    countDownTimerTextView.setText("Timer: " + progressBar.getProgress());


                } else {
                    Toast.makeText(getApplicationContext(), "Wrong answer!", Toast.LENGTH_SHORT).show();

                    btn2.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                }
                index = myRandomInt = new Random().nextInt(28 - 0) + 0;
                str_images_name_index = new Random().nextInt(34 - 0) + 0;
                img.setImageResource(images[str_images_name_index].img_path);
                rightanswer = images[str_images_name_index].key.toString();
                random_answer();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn3.getText().toString().toLowerCase() == images[str_images_name_index].key.toString().toLowerCase()) {
                    Toast.makeText(getApplicationContext(), "Right answer!", Toast.LENGTH_SHORT);
                    btn3.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    points++;
                    pointsTextView.setText("Points:" + points);
                    progressBar.setProgress(progressBar.getProgress() + 1);
                    countDownTimerTextView.setText("Timer: " + progressBar.getProgress());


                } else {
                    Toast.makeText(getApplicationContext(), "Wrong answer!", Toast.LENGTH_SHORT).show();

                    btn3.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                }
                index = myRandomInt = new Random().nextInt(28 - 0) + 0;
                str_images_name_index = new Random().nextInt(34 - 0) + 0;
                img.setImageResource(images[str_images_name_index].img_path);
                rightanswer = images[str_images_name_index].key.toString();
                random_answer();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Drawable background = btn4.getBackground();
                if (btn4.getText().toString().toLowerCase() == images[str_images_name_index].key.toString().toLowerCase()) {
                    Toast.makeText(getApplicationContext(), "Right answer!", Toast.LENGTH_SHORT);
                    btn4.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    points++;
                    pointsTextView.setText("Points:" + points);
                    progressBar.setProgress(progressBar.getProgress() + 1);
                    countDownTimerTextView.setText("Timer: " + progressBar.getProgress());

                } else {
                    Toast.makeText(getApplicationContext(), "Wrong answer!", Toast.LENGTH_SHORT).show();

                    btn4.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                }
                index = myRandomInt = new Random().nextInt(28 - 0) + 0;
                str_images_name_index = new Random().nextInt(34 - 0) + 0;
                img.setImageResource(images[str_images_name_index].img_path);
                rightanswer = images[str_images_name_index].key.toString();
                random_answer();

            }
        });






        new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setProgress((int) millisUntilFinished / 1000);
                countDownTimerTextView.setText("Timer: "+progressBar.getProgress());               // is there a better way?


           }



            @Override
            public void onFinish() {
                Toast.makeText(GameActivity.this, "DONE!", Toast.LENGTH_SHORT).show();
                 //   intent= new Intent(GameActivity.this, RecordActivity.class);
                    intent.putExtra("FULL_NAME", fullname);
                    intent.putExtra("SCORE", points );

                    startActivity(intent); }

        }.start();

    }


    public int random_answer() {
        String temp_array[] = shuffle_answers();
        int str_images_name_random = new Random().nextInt(5 - 1) + 1;
        switch (str_images_name_random) {

            case 1: {
                btn1.setText(rightanswer.toString());
                btn2.setText(temp_array[1].toString());
                btn3.setText(temp_array[2].toString());
                btn4.setText(temp_array[3].toString());
                break;

            }
            case 2: {
                btn1.setText(temp_array[1].toString());
                btn2.setText(rightanswer.toString());
                btn3.setText(temp_array[2].toString());
                btn4.setText(temp_array[3].toString());
                break;
            }
            case 3: {
                btn1.setText(temp_array[1].toString());
                btn2.setText(temp_array[2].toString());
                btn3.setText(rightanswer.toString());
                btn4.setText(temp_array[3].toString());
                break;
            }
            case 4: {
                btn1.setText(temp_array[1].toString());
                btn2.setText(temp_array[2].toString());
                btn3.setText(temp_array[3].toString());
                btn4.setText(rightanswer.toString());
                break;
            }

        }
        return 0;

    }


    public String[] shuffle_answers()
    {int j=0;
     String temp[] = {rightanswer.toLowerCase(), answers[myRandomInt = new Random().nextInt(19 - 0) + 0].toString().toLowerCase(),answers[myRandomInt = new Random().nextInt(19 - 0) + 0].toString().toLowerCase(),
              answers[myRandomInt = new Random().nextInt(19 - 0) + 0].toString().toLowerCase()};
      while ((temp[0].toString().toLowerCase()).equals((temp[1].toString().toLowerCase()))||(temp[0].toString().toLowerCase()).equals(temp[2].toString().toLowerCase())||(temp[0].toString().toLowerCase()).equals((temp[3].toString().toLowerCase()))||(temp[1].toString().toLowerCase()).equals((temp[2].toString().toLowerCase()))||(temp[1].toString().toLowerCase()).equals((temp[3].toString().toLowerCase()))||(temp[2].toString().toLowerCase()).equals((temp[3].toString().toLowerCase())))
      {
        temp[1]= answers[myRandomInt = new Random().nextInt(19 - 0) + 0].toString();
        temp[2]= answers[myRandomInt = new Random().nextInt(19 - 0) + 0].toString();
        temp[3]= answers[myRandomInt = new Random().nextInt(19 - 0) + 0].toString();
     }
    return temp;
    }

}
