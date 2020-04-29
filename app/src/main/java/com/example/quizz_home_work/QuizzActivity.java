package com.example.quizz_home_work;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.quizz_home_work.data.HistoryRow;
import com.example.quizz_home_work.data.HistoryStorage;
import com.example.quizz_home_work.data.QuizStorage;
import com.example.quizz_home_work.data.Storage;
import com.example.quizz_home_work.data.StorageImplementation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class QuizzActivity extends Activity {
    public TextView question;
    public String correct_answer;
    public TextView current_score;
    public ArrayList<Button> buttons;
    public int questions_limit;
    Object storageAsObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
        question=findViewById(R.id.question);
        current_score=findViewById(R.id.currentScore);
        buttons=new ArrayList<Button>();
        buttons.add((Button)findViewById(R.id.I));
        buttons.add((Button)findViewById(R.id.II));
        buttons.add((Button)findViewById(R.id.III));
        Storage storage = new StorageImplementation();
        storageAsObject = storage
                .getObject(this, StorageImplementation.QUESTIONS, QuizStorage.class);
        if(storageAsObject==null){
            Intent home_page=new Intent(this,HomeActivity.class);
            startActivity(home_page);
            finish();
            return;
        }
        questions_limit=((QuizStorage) storageAsObject).getQuestions().size();
        generateNextQuestion();
    }
    public void generateNextQuestion(){
        if (questions_limit==0) {
            finishWork();
            Intent home_page=new Intent(this,HomeActivity.class);
            startActivity(home_page);
            finish();
            return;
        }
        question.setText(((QuizStorage) storageAsObject).getQuestions().get(questions_limit-1).getQuestion());
        ArrayList<String> shuffled_answers=((QuizStorage) storageAsObject).getQuestions().get(questions_limit-1).getAnswer();
        correct_answer=shuffled_answers.get(0);
        Collections.shuffle(shuffled_answers);
        for(int i =0;i<3;i++){
            buttons.get(i).setText(shuffled_answers.get(i));
        }
        questions_limit-=1;
    }

    private void finishWork(){
        StorageImplementation storage = new StorageImplementation();
        String top_score=storage.getTopScore(this);
        if(top_score!=null){
            top_score=Integer.toString(Math.max(Integer.parseInt(top_score),Integer.parseInt(current_score.getText().toString())));
        }
        else{
            top_score=current_score.getText().toString();
        }
        storage.saveTopScore(this,top_score);


        HistoryRow newHistory = new HistoryRow();

        newHistory.setTime(Calendar.getInstance().getTime().toString());
        newHistory.setScore(current_score.getText().toString());

        Object storageAsObject = storage
                .getObject(this, StorageImplementation.HISTORY, HistoryStorage.class);

        HistoryStorage historyStorage;
        if (storageAsObject != null) {
            historyStorage = (HistoryStorage) storageAsObject;
        } else {
            historyStorage = new HistoryStorage();
        }

        historyStorage.getHistory().add(newHistory);
        storage.add(this, StorageImplementation.HISTORY, historyStorage);
        return;
    }

    public void onClick(View view){
        Button pressed_button=(Button)view;

        if(pressed_button.getText()==correct_answer){
            current_score.setText(Integer.toString(Integer.parseInt(current_score.getText().toString())+10));
        }
        generateNextQuestion();
    }
}
