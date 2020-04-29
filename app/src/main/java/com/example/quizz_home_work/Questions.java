package com.example.quizz_home_work;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quizz_home_work.data.QuizQuestion;
import com.example.quizz_home_work.data.QuizStorage;
import com.example.quizz_home_work.data.Storage;
import com.example.quizz_home_work.data.StorageImplementation;

import java.util.ArrayList;
import java.util.List;

public class Questions extends Activity {
    ListView questions;
    QuizStorage quizStorage;
    ArrayList<QuizQuestion> questionsForDisplay;
    QuestionArrayAdapter questionArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        questions=findViewById(R.id.questions);
        questions.setLongClickable(true);
        questions.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                removeQuestion(pos);
                updateListView();
                return true;
            }
        });
        updateListView();
    }

    private void removeQuestion(int pos){
        Storage storage = new StorageImplementation();
        Object storageAsObject = storage
                .getObject(this, StorageImplementation.QUESTIONS, QuizStorage.class);

        QuizStorage quizStorage;
        if (storageAsObject != null) {
            quizStorage = (QuizStorage) storageAsObject;
        } else {
            quizStorage = new QuizStorage();
        }

        quizStorage.getQuestions().remove(pos);
        storage.add(this, StorageImplementation.QUESTIONS, quizStorage);
    }

    private void updateListView(){
        questionArrayAdapter = new QuestionArrayAdapter(this, 0, new ArrayList<QuizQuestion>());
        questions.setAdapter(questionArrayAdapter);
        Storage storageQuestion = new StorageImplementation();
        Object storageAsObject = storageQuestion
                .getObject(this, StorageImplementation.QUESTIONS, QuizStorage.class);
        if (storageAsObject != null) {
            quizStorage = (QuizStorage) storageAsObject;
        } else {
            quizStorage = new QuizStorage();
        }
        questionsForDisplay=quizStorage.getQuestions();
        questionArrayAdapter.addAll(questionsForDisplay);
    }


    public void addQuestions(View view){
        Intent transfer=new Intent(Questions.this ,AddQuestions.class);
        startActivityForResult(transfer,1);

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, getIntent());
        if(resultCode==RESULT_OK && requestCode==1){
            updateListView();
        }
    }

    class QuestionArrayAdapter extends ArrayAdapter<QuizQuestion> {

        private Context mContext;

        private QuestionArrayAdapter(@NonNull Context context,
                                    int resource,
                                    @NonNull List<QuizQuestion> objects) {
            super(context, resource, objects);
            mContext = context;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            QuizQuestion current = getItem(position);
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.view_lines, parent, false);
            TextView textView = view.findViewById(R.id.row_model);
            textView.setText(current.getQuestion());

            return view;
        }
        }

}
