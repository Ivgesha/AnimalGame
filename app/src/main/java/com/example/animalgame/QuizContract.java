package com.example.animalgame;

import android.media.Image;
import android.provider.BaseColumns;

public final class QuizContract {




    private QuizContract(){
        // now we cant make empty QuizContract
    }
    public static class ImageTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_animals";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_OPTION4 = "option4";
        public static final String COLUMN_ANSWER_NUMBER = "answer_number";
    }
}
