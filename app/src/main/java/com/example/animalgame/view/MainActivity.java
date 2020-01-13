package com.example.animalgame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.animalgame.R;

public class MainActivity extends AppCompatActivity {


    Intent intent;

    EditText enterNameEditText;
    Button startGameButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterNameEditText= findViewById(R.id.EnterNameEditText);
        startGameButton = findViewById(R.id.startGameButton);

        // for testing only
        enterNameEditText.setText("Test");


    }

   public void startGameButtonClick(View view){                 // on click startGame Button
        intent = new Intent(this,GameActivity.class);
       Log.d("enterNameEditText",enterNameEditText.getText().toString());
        if(enterNameEditText.getText().toString().equals("")){
            Toast.makeText(this,"name is empty",Toast.LENGTH_LONG).show();
        }
        else{
            this.startActivity(intent);
            Toast.makeText(this,"name is full! we can play",Toast.LENGTH_LONG).show();
        }

    }

    public void howToPlayClick(View view){                      // on click howToPlay Button
        intent = new Intent(this,HowToPlayActivity.class);
        startActivity(intent);
    }
}
