package com.example.animalgame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.animalgame.R;

import java.util.ArrayList;

import static com.example.animalgame.view.GameActivity2.EXTRA_SCORE;
import static com.example.animalgame.view.MainActivity.EXTRA_USERNAME;

public class RecordActivity extends AppCompatActivity {
    String fullname = "0";
    int score = 0;
    DatabaseHelper db;
    public ListView listView;
    boolean IsInserted;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        db = new DatabaseHelper(this);
        listView = (ListView) findViewById(R.id.listview);
        fullname = getIntent().getStringExtra(EXTRA_USERNAME);
        score = getIntent().getIntExtra(EXTRA_SCORE , -1);
        IsInserted = db.insertData(fullname.toString(), score);
        if (IsInserted)
            Toast.makeText(getApplicationContext(), "Data inserted!", Toast.LENGTH_LONG);

        else
            Toast.makeText(getApplicationContext(), "Data not inserted!", Toast.LENGTH_LONG);

        viewAll();

    }

    public void viewAll() {
        {
            Cursor res = db.getAllData();
            if (res.getCount() == 0) {
                // show message
                showRecords("Error", "Nothing found");
                return;
            }

           StringBuffer buffer = new StringBuffer();
            // Show all data
            showRecords("Score Records", buffer.toString());
        }

    }

    public void showRecords(String title, String Message) {
        //populate an ArrayList<String> from the database and then view it
        ArrayList<Player> theList = new ArrayList<Player>();
        Cursor data = db.getAllData();
        if (data.getCount() == 0) {
            Toast.makeText(this, "There are no contents in this list!", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                theList.add(new Player(data.getString(0) ,Integer.parseInt(data.getString(1))));
            }
            PlayerListAdapter adapter = new PlayerListAdapter(this,R.layout.adapter_view_layout,theList);
            listView.setAdapter(adapter);
        }
    }


}

