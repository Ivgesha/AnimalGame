package com.example.animalgame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.animalgame.QuizDBHelper;
import com.example.animalgame.R;
import com.example.animalgame.model.Question;

import java.util.List;

public class GameActivity2 extends AppCompatActivity {

    private TextView textViewScore;
    private TextView textViewCountDown;
    private ImageView imageViewAnimals;
    private Button buttonAnswer1;
    private Button buttonAnswer2;
    private Button buttonAnswer3;
    private Button buttonAnswer4;
    private Button buttonNext;

    private List<Question> questionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        textViewScore = findViewById(R.id.textViewScore);
        textViewCountDown = findViewById(R.id.textViewCountDown);
        imageViewAnimals = findViewById(R.id.imageViewAnimals);
        buttonAnswer1 = findViewById(R.id.btnAnswer_1);
        buttonAnswer2 = findViewById(R.id.btnAnswer_2);
        buttonAnswer3 = findViewById(R.id.btnAnswer_3);
        buttonAnswer4 = findViewById(R.id.btnAnswer_4);
        buttonNext = findViewById(R.id.btnNext);

        QuizDBHelper dbHelper = new QuizDBHelper(this);
        questionList = dbHelper.getAllQuestions();


    }
}
