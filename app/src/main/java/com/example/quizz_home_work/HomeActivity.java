package com.example.quizz_home_work;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.quizz_home_work.data.Storage;
import com.example.quizz_home_work.data.StorageImplementation;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StorageImplementation storage = new StorageImplementation();
        String top_score= storage.getTopScore(this);
        if (top_score!=null) {
            TextView score = findViewById(R.id.top_score);
            score.setText(top_score);
        }
    }
    public void startQuiz(View view){
        Intent transfer=new Intent(this,QuizzActivity.class);
        startActivity(transfer);
    }


    public void Questions(View view){
        Intent transfer = new Intent(this, Questions.class);
        startActivity(transfer);
    }

    public void History(View view){
        Intent transfer = new Intent(this, History.class);
        startActivity(transfer);
    }
}
