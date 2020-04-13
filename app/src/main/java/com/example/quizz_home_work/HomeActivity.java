package com.example.quizz_home_work;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.quizz_home_work.data.Storage;
import com.example.quizz_home_work.data.StorageSharePreferenceImpl;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Storage storage = new StorageSharePreferenceImpl();
        String top_score=storage.get(this,"top_score");
        if (top_score!=null) {
            TextView score = findViewById(R.id.top_score);
            score.setText(top_score);
        }
    }
    public void StartQuiz(){
        Intent transfer=new Intent(this,QuizzActivity.class);
        startActivity(transfer);
    }
}
