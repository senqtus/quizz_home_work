package com.example.quizz_home_work;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.quizz_home_work.data.Storage;
import com.example.quizz_home_work.data.StorageSharePreferenceImpl;

import java.util.ArrayList;
import java.util.Random;

public class QuizzActivity extends Activity {
    public TextView question;
    public TextView current_score;
    public ArrayList<Button> buttons;
    public int qustions_limit=4;
    public int current_correct_answer;
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
        generateNextQuestion();
    }
    public void generateNextQuestion(){
        if (qustions_limit==0) {
            Storage storage = new StorageSharePreferenceImpl();
            String top_score=storage.get(this,"top_score");
            if(top_score!=null){
                top_score=Integer.toString(Math.max(Integer.parseInt(top_score),Integer.parseInt(current_score.getText().toString())));
            }
            else{
                top_score=current_score.getText().toString();
            }
            storage.save(this,"top_score",top_score);
            Intent home_page=new Intent(this,HomeActivity.class);
            startActivity(home_page);
            return;
        }
        question.setText("Random Question " + Integer.toString(qustions_limit));
        for(int i =0;i<3;i++){
            buttons.get(i).setText("Random answer "+Integer.toString(i));
        }
        current_correct_answer=new Random().nextInt(3);
        qustions_limit-=1;
    }

    public void onClick(View view){
        if(view.getId()==buttons.get(current_correct_answer).getId()){
            current_score.setText(Integer.toString(Integer.parseInt(current_score.getText().toString())+10));
        }
        generateNextQuestion();
    }
}
