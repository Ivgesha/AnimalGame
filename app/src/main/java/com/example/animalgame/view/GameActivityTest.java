package com.example.animalgame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.animalgame.R;

import java.util.ArrayList;
import java.util.Random;

public class GameActivityTest extends AppCompatActivity {

    public ImageView imageViewAnimals;


    int[] imageArray = new int[] {R.drawable.alligator,R.drawable.cat};
    String[] answerArray = {"alligator","car"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_test);

        imageViewAnimals = findViewById(R.id.imageViewAnimas);



    }

    public void onClickBtn0(View view) {
        Random random = new Random();
        int rand = random.nextInt(2);

        imageViewAnimals.setImageResource(imageArray[rand]);

        Log.d("resourceTest " , "--> " + imageViewAnimals.getResources());


    }
}
