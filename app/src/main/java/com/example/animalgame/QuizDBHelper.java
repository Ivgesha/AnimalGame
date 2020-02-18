package com.example.animalgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.animalgame.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuizDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DataBase.db";
    public static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;


    public QuizDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        // there is a problem to save an image so we need to save int or string of it/
        final String SQL_CREATE_IMAGES_TABLE = "CREATE TABLE " +
                QuizContract.ImageTable.TABLE_NAME + " ( " +
                QuizContract.ImageTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.ImageTable.COLUMN_IMAGE + " TEXT, " +
                QuizContract.ImageTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.ImageTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.ImageTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract.ImageTable.COLUMN_OPTION4 + " TEXT, " +
                QuizContract.ImageTable.COLUMN_ANSWER_NUMBER + " INTEGER " + ")";


        db.execSQL(SQL_CREATE_IMAGES_TABLE);
        fillImageTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.ImageTable.TABLE_NAME);
        onCreate(db);
    }


    private void fillImageTable() {
        Question q1 = new Question("drawable://" + R.drawable.alligator, "A", "B", "C", "D", 1);
        addQuestion(q1);
    }

    private void addQuestion(Question question) {

        ContentValues cv = new ContentValues();
        cv.put(QuizContract.ImageTable.COLUMN_IMAGE, question.getAnimalImagel());
        cv.put(QuizContract.ImageTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.ImageTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.ImageTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.ImageTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuizContract.ImageTable.COLUMN_ANSWER_NUMBER, question.getAnswerNumber());
        db.insert(QuizContract.ImageTable.TABLE_NAME, null, cv);
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.ImageTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setAnimalImagel(c.getString(c.getColumnIndex(QuizContract.ImageTable.COLUMN_IMAGE)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.ImageTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.ImageTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.ImageTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuizContract.ImageTable.COLUMN_OPTION4)));
                question.setAnswerNumber(c.getInt(c.getColumnIndex(QuizContract.ImageTable.COLUMN_ANSWER_NUMBER)));
                questionList.add(question);
            } while (c.moveToNext());

        }

        c.close();
        return questionList;
    }

}
