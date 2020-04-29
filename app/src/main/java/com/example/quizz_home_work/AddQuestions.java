package com.example.quizz_home_work;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.quizz_home_work.data.QuizQuestion;
import com.example.quizz_home_work.data.QuizStorage;
import com.example.quizz_home_work.data.Storage;
import com.example.quizz_home_work.data.StorageImplementation;

import java.util.ArrayList;

public class AddQuestions extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
    }


    public void addQuestion(View view){
        EditText question=findViewById(R.id.newQuestion);
        ArrayList<String> answers=new ArrayList<>();
        answers.add(((EditText)findViewById(R.id.answer1)).getText().toString());
        answers.add(((EditText)findViewById(R.id.answer2)).getText().toString());
        answers.add(((EditText)findViewById(R.id.answer3)).getText().toString());
        if(question.getText().toString().equals("")){
            finish();
        }

        if (answers.contains("")){
            return;
        }
        QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setQuestion(question.getText().toString());
        quizQuestion.setAnswer(answers);

        Storage storage = new StorageImplementation();
        Object storageAsObject = storage
                .getObject(this, StorageImplementation.QUESTIONS, QuizStorage.class);

        QuizStorage quizStorage;
        if (storageAsObject != null) {
            quizStorage = (QuizStorage) storageAsObject;
        } else {
            quizStorage = new QuizStorage();
        }

        quizStorage.getQuestions().add(quizQuestion);
        storage.add(this, StorageImplementation.QUESTIONS, quizStorage);
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}
