package com.example.animalgame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.animalgame.R;

import java.util.ArrayList;

public class RecordActivity extends AppCompatActivity {
String fullname="0";
int score=0;
DatabaseHelper db;
public   ListView listView;
boolean IsInserted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        db = new DatabaseHelper(this);
        listView = (ListView) findViewById(R.id.listView);
        fullname = getIntent().getStringExtra("EXTRA_USRENAME");
        score = getIntent().getIntExtra("EXTRA_SCORE", -1);
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
                    showRecords("Error","Nothing found");
                   return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Name :" + res.getString(0) + "\n");
                    buffer.append("Score :" + res.getString(1) + "\n");
                }

                // Show all data
                showRecords("Score Records", buffer.toString());
            }

        }
            public void showRecords(String title,String Message){

                //populate an ArrayList<String> from the database and then view it
                ArrayList<String> theList = new ArrayList<>();
                Cursor data = db.getAllData();
                if(data.getCount() == 0){
                    Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
                }else{
                    while(data.moveToNext()){
                        theList.add(data.getString(0)+"                                                           "+data.getString(1));
                        ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                        listView.setAdapter(listAdapter);
                    }
                }
        }








    }

